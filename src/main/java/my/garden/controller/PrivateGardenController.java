package my.garden.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.garden.dto.MembersDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.service.PrivateGardenService;
import my.garden.service.ShoppingService;

@Controller
public class PrivateGardenController {

  @Autowired
  HttpSession session;

  @Autowired
  private PrivateGardenService gservice;

  @Autowired
  ShoppingService shsvc;

  @RequestMapping("privateGarden")
  public String privateGarden(Model model) {
    String id = (String) session.getAttribute("loginId");
    if (id == null) {
      return "alerts/privateIdCheck";
    } else {
      try {
        PrivateGardenDTO dto = gservice.selectPrivateGardenInfoService(id);
        if (dto == null) {
          return "privategarden/firstGuide";
        } else {
          model.addAttribute("result", dto);
          return "privategarden/privateGardenManage";
        }
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
    }
  }

  @RequestMapping("toSelectHerb")
  public String toSelectherb(MembersDTO dto, HttpServletRequest request) {
    String id = (String) session.getAttribute("loginId");
    try {
      request.setAttribute("loginDTO", shsvc.getMember(dto, id));
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
    return "privategarden/selectHerb";
  }

  @RequestMapping("toConfirmherb")
  public String insertPrivateGarden(String herb) {
    String id = (String) session.getAttribute("loginId");
    try {
      int result = gservice.insertPrivateGardenService(id, herb);
      if (result > 0) {
        return "redirect:/privateGarden";
      } else {
        return "error";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping(value = "updateState", produces = "text/html;charset=utf8")
  public String updateState(String light, String humid, String temper) {
    String id = (String) session.getAttribute("loginId");
    int temp = Integer.parseInt(temper);
    try {
      int result = gservice.updatePrivateGardenService(id, light, humid, temp);
      if (result > 0) {
        return "잘 다녀옴";
      } else {
        return "문제가 발생함";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @RequestMapping("cloud")
  public String cloud() {
    return "module/cloud";
  }

  @RequestMapping("sun")
  public String sun() {
    return "module/sun";
  }
}
