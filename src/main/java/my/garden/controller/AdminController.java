package my.garden.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.garden.dao.AdminDAO;
import my.garden.dao.LoginDAO;
import my.garden.dto.AdminMemDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;
import my.garden.service.AdminService;
import my.garden.service.BoardQnAService;

@Controller
public class AdminController {

  @Autowired
  private AdminService dao;
  @Autowired
  private LoginDAO logdao;
  @Autowired
  private BoardQnAService qnaService;

  @RequestMapping("adminIndex")
  public String adminIndex(HttpServletRequest request) {
    List<AdminMemDTO> member;
    try {
      member = dao.serviceAllMembers();
      request.setAttribute("member", member);
      String admin = (String) request.getSession().getAttribute("loginId");
      String profileImg = logdao.memSelectAll(admin).getM_profile();

      request.setAttribute("profileImg", profileImg);
      request.setAttribute("totalSale", dao.serviceTotalSale());
      request.setAttribute("totalCancel", dao.serviceTotalCancel());
      request.setAttribute("realSale", dao.serviceTotalSale() - dao.serviceTotalCancel());

      List<ShopListDTO> popular = dao.servicePopularProduct();
      List<String> popularProduct = new ArrayList<>();
      List<Long> count = new ArrayList<>();
      if (popular.size() > 0) {
        int totalCount = dao.serviceTotalSaleCount();
        int tmp = 0;
        if (popular.size() > 5) {
          tmp = 5;
        } else {
          tmp = popular.size();
        }
        for (int i = 0; i < tmp; i++) {
          count.add(Math.round((popular.get(i).getS_p_count() / (double) totalCount) * 100));
        }
        for (int i = 0; i < tmp; i++) {
          popularProduct.add("'" + popular.get(i).getS_p_title() + " (" + count.get(i) + "%)'");
        }
      }
      request.setAttribute("popularProduct", popularProduct);
      request.setAttribute("popular", popular);
      request.setAttribute("count", count);
      request.setAttribute("depositWait", dao.serviceStatCheck("입금 대기"));
      request.setAttribute("subsWait", dao.serviceSubscribeCheckList("입금 대기").size());
      request.setAttribute("shippingWait", dao.serviceStatCheck("결제 완료"));
      request.setAttribute("depositCheckList", dao.serviceOrderCheckList("입금 대기"));
      request.setAttribute("subsCheckList", dao.serviceSubscribeCheckList("입금 대기"));
      request.setAttribute("shippingCheckList", dao.serviceOrderCheckList("결제 완료"));

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "forAdmin/adminIndex";
  }


  @RequestMapping("adminPrivateGarden")
  public String adminPrivateGarden(HttpServletRequest request) {
    List<PrivateGardenDTO> list;
    try {
      String admin = (String) request.getSession().getAttribute("loginId");
      String profileImg = logdao.memSelectAll(admin).getM_profile();
      request.setAttribute("profileImg", profileImg);
      list = dao.servicePrivateList();
      request.setAttribute("list", list);

      List<PrivateGardenDTO> tmp = dao.servicePopularHerb();
      List<String> herbList = new ArrayList<>();
      List<Integer> herbCount = new ArrayList<>();
      for (int i = 0; i < tmp.size(); i++) {
        herbList.add("'" + tmp.get(i).getG_herb() + "'");
        herbCount.add(tmp.get(i).getG_temper());
      }
      System.out.println(herbList + ":" + herbCount);
      request.setAttribute("herbList", herbList);
      request.setAttribute("herbCount", herbCount);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "forAdmin/adminPrivateGarden";
  }

  @ResponseBody
  @RequestMapping("moneyStatChange")
  public Map<String, Object> moneyStatChange(String no) {
    Map<String, Object> map = new HashMap<>();
    try {
      int result = dao.serviceUpdateOrder(no, "결제 완료");
      map.put("result", result);
      map.put("orderNo", no);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @ResponseBody
  @RequestMapping("shippingStatChange")
  public Map<String, Object> shippingStatChange(String no) {
    Map<String, Object> map = new HashMap<>();
    try {
      int result = dao.serviceUpdateOrder(no, "배송중");
      map.put("result", result);
      map.put("orderNo", no);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @ResponseBody
  @RequestMapping("subscribeStatChange")
  public Map<String, Object> subscribeStatChange(String no) {
    Map<String, Object> map = new HashMap<>();
    try {
      int result = dao.serviceUpdateSubscribe(no, "구독중");
      map.put("result", result);
      map.put("orderNo", no);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @RequestMapping("adminQnACheck")
  public String adminQnACheck(HttpServletRequest request) {
    List<PrivateGardenDTO> list;
    try {
      String admin = (String) request.getSession().getAttribute("loginId");
      String profileImg = logdao.memSelectAll(admin).getM_profile();
      request.setAttribute("profileImg", profileImg);
      list = dao.servicePrivateList();
      request.setAttribute("list", list);

      request.setAttribute("qnaList", qnaService.qnaListforAdmin()); //문의글 리스트
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "forAdmin/adminQnACheck";
  }
}
