package my.garden.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import my.garden.dto.BoardFreeDTO;
import my.garden.dto.ProductsDTO;
import my.garden.service.BoardFreeService;
import my.garden.service.BoardReviewService;
import my.garden.service.ProductsService;

@Controller
public class HomeController {

  @Autowired
  HttpSession session;
  @Autowired
  private BoardReviewService brService;
  @Autowired
  private BoardFreeService bfs;
  @Autowired
  private ProductsService pservice;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(HttpServletRequest request) {
    //레시피 보여주기
    try {
      BoardFreeDTO recipe = bfs.serviceMostViewed();
      request.setAttribute("recipe", recipe);
      //탑리뷰 보여주기
      request.setAttribute("topReviews", brService.topRcmdReviews());
      // 베스트상품 보여주기
      try {
        List<ProductsDTO> best = pservice.selectBestProductsService();
        session.setAttribute("best", best);
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "home";
  }

  @RequestMapping("/aboutMyGarden")
  public String aboutMyGarden() {
    return "login/aboutMyGarden";
  }

  @RequestMapping("productsAdd")
  public String toProductsAdd() {
    return "products/productsAdd";
  }

  @RequestMapping("toChat")
  public String toChat() {
    return "chat/chat";
  }

  @ResponseBody
  @RequestMapping("/test")
  public String test() {
    return "";
  }
}
