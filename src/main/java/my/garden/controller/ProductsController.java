package my.garden.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dto.ProductsDTO;
import my.garden.service.ProductsService;

@Controller
public class ProductsController {

  @Autowired
  private HttpSession session;

  @Autowired
  private ProductsService pservice;

  @RequestMapping("insertProducts")
  public String insertProducts(ProductsDTO dto) {
    String rootPath = session.getServletContext().getRealPath("/resources");
    String title = dto.getP_title();
    try {
      File newFile = new File(rootPath + "/products/" + title);
      if (!newFile.exists()) {
        newFile.mkdir();
      }
      File realFile = new File(rootPath + "/products/" + title + "/" + System.currentTimeMillis() + "_" + dto.getImage().getOriginalFilename());
      dto.getImage().transferTo(realFile);
      dto.setP_imagepath("/resources/products/" + title + "/" + realFile.getName());
      int result = pservice.insertProductsService(dto);
      int result2 = pservice.insertImageFileService(title, dto.getP_imagepath());
      return "products/productsAdd";
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping(value = "uploadImage", produces = "text/html;charset=utf8")
  public String uploadImage(HttpServletRequest request, MultipartFile file, String title) {
    try {
      title = new String(title.getBytes(), "utf8");
    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    }
    String rootPath = session.getServletContext().getRealPath("/resources");
    try {
      File newFile = new File(rootPath + "/products/" + title);
      if (!newFile.exists()) {
        newFile.mkdir();
      }

      File realFile = new File(newFile + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename());
      file.transferTo(realFile);
      String imgs = "/resources/products/" + title + "/" + realFile.getName();
      try {
        int result = pservice.insertImageFileService(title, imgs);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return imgs;
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping("deleteImage")
  public boolean deleteImage(HttpServletRequest request) {
    String rootPath = session.getServletContext().getRealPath("/");
    String fileUrl = request.getParameter("src");
    String filePath = null;
    if (fileUrl.startsWith("http")) {
      filePath = fileUrl.replaceAll("http://.+?/", "");
    } else {
      filePath = fileUrl;
    }
    boolean deleteFile = new File(rootPath + filePath).delete();
    return deleteFile;
  }

  @RequestMapping(value = {"fruit", "vegetable", "egg", "grain", "source"})
  public String selectFruitsList(Model model, String category) {
    int start = 1;
    int end = start + 5;
    try {
      List<ProductsDTO> result = pservice.selectProductsListByCategoryService(start, end, category);
      model.addAttribute("result", result);
      model.addAttribute("category", category);
      model.addAttribute("keyword", "all");
      return "products/productsList";
    } catch (Exception e) {
      e.printStackTrace();
      return "products/productsList";
    }
  }

  @RequestMapping("productsList")
  public String selectFirstList(Model model) {
    int start = 1;
    int end = start + 5;
    try {
      List<ProductsDTO> result = pservice.selectProductsListByPageService(start, end);
      model.addAttribute("result", result);
      model.addAttribute("category", "all");
      model.addAttribute("keyword", "all");
      return "products/productsList";
    } catch (Exception e) {
      e.printStackTrace();
      return "products/productsList";
    }
  }

  @ResponseBody
  @RequestMapping("selectProductsByPage")
  public List<ProductsDTO> selectProductsListByPage(int page, String category, String keyword) {
    int end = page * 6;
    int start = end - 5;
    try {
      if (category.equals("all") && keyword.equals("all")) {
        return pservice.selectProductsListByPageService(start, end);
      } else if (!category.equals("all") && keyword.equals("all")) {
        return pservice.selectProductsListByCategoryService(start, end, category);
      } else {
        return pservice.selectProductsListByKeywordService(start, end, keyword);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping("productsDelete")
  public String deleteProducts(String[] arr, Model model) {
    int result = 0;
    try {
      for (int i = 0; i < arr.length; i++) {
        result = pservice.deleteProductService(Integer.parseInt(arr[i]));
        if (result > 0) {
          continue;
        } else {
          break;
        }
      }
      model.addAttribute("result", result);
      return "redirect:/deleteCheck";
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @RequestMapping("deleteCheck")
  public String deleteCheck(Model model, int result) {
    model.addAttribute("result", result);
    return "products/deletecheck";
  }

  @RequestMapping("productsUpdate")
  public String selectProduct(Model model, int pnumber) {
    try {
      ProductsDTO dto = pservice.selectOneProductService(pnumber);
      model.addAttribute("result", dto);
      return "products/productsEdit";
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping("productEditFin")
  public String updateProduct(Model model, ProductsDTO dto) {
    String resourcePath = session.getServletContext().getRealPath("/resources");
    String title = dto.getP_title();
    try {
      File newFile = new File(resourcePath + "/products/" + title + "/" + System.currentTimeMillis() + "_" + dto.getImage().getOriginalFilename());
      dto.getImage().transferTo(newFile);
      dto.setP_imagepath("/resources/products/" + title + "/" + newFile.getName());
      int result = pservice.updateProductService(dto);
      model.addAttribute("pno", dto.getP_no());
      model.addAttribute("result", result);
      if (result > 0) {
        return "redirect:/updatecheck";
      } else {
        return "redirect:/updatecheck";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @RequestMapping("updatecheck")
  public String updatecheck(Model model, int result, int pno) {
    model.addAttribute("result", result);
    model.addAttribute("pno", pno);
    return "products/updateCheck";
  }

  @RequestMapping("searchKeyword")
  public String selectProductsListByKeyword(Model model, String keyword) {
    int start = 1;
    int end = start + 5;
    try {
      List<ProductsDTO> result = pservice.selectProductsListByKeywordService(start, end, keyword);
      model.addAttribute("result", result);
      model.addAttribute("category", "all");
      model.addAttribute("keyword", keyword);
      return "products/productsList";
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}
