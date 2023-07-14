package my.garden.serviceImpl;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.garden.dao.ProductsDAO;
import my.garden.dto.ProductsDTO;
import my.garden.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

  @Autowired
  private HttpSession session;

  @Autowired
  private ProductsDAO pdao;

  public List<ProductsDTO> selectBestProductsService() throws Exception {
    try {
      return pdao.selectBestProducts();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertProductsService(ProductsDTO dto) throws Exception {
    try {
      int result = pdao.insertProducts(dto);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int insertImageFileService(String title, String imgs) throws Exception {
    try {
      int result = pdao.insertImageFile(title, imgs);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public List<ProductsDTO> selectProductsListByCategoryService(int start, int end, String category) throws Exception {
    try {
      List<ProductsDTO> result = pdao.selectProductsListByCategory(start, end, category);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductsDTO> selectProductsListByPageService(int start, int end) throws Exception {
    try {
      List<ProductsDTO> result = pdao.selectProductsListByPage(start, end);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ProductsDTO selectOneProductService(int pnumber) throws Exception {
    try {
      return pdao.selectOneProduct(pnumber);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Transactional("txManager")
  public int deleteProductService(int pnumber) {
    try {
      ProductsDTO dto = pdao.selectOneProduct(pnumber);
      String imgPath = dto.getP_imagepath();
      int result = pdao.deleteProduct(pnumber);
      if (result > 0) {
        pdao.deleteImagePath(dto.getP_title()); // DB에 있는 이미지 파일 삭제
        String rootPath = session.getServletContext().getRealPath("/resources/products/");
        String oldPath = session.getServletContext().getRealPath("/" + imgPath);
        File folder = new File(rootPath + dto.getP_title());
        if (new File(oldPath).delete()) { // 실제 폴더 안의 이미지가 삭제되면
          folder.delete(); // 폴더를 삭제해
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateProductService(ProductsDTO dto) throws Exception {
    try {
      return pdao.updateProduct(dto);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public List<ProductsDTO> selectProductsListByKeywordService(int start, int end, String keyword) throws Exception {
    try {
      return pdao.selectProductsListByKeyword(start, end, keyword);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Transactional("txManager")
  public List<String> selectTitlesByCategoryService(String p_category) throws Exception {
    return pdao.selectTitlesByCategory(p_category);
  }

}
