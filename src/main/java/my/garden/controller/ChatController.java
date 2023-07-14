package my.garden.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.garden.dto.ChatDTO;
import my.garden.service.ChatService;

@Controller
public class ChatController {

  @Autowired
  private ChatService cservice;

  @ResponseBody
  @RequestMapping(value = "clientMsg", produces = "text/html;charset=utf8")
  public String clientMsg(String client, String msg) {
    try {
      int result = cservice.insertClientSendMessageService(client, msg, "client");
      if (result > 0) {
        return "성공했음";
      } else {
        return "실패했음";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping(value = "adminMsg", produces = "text/html;charset=utf8")
  public String adminMsg(String admin, String msg, String client) {
    try {
      int result = cservice.insertAdminSendMessageService(admin, msg, client, "admin");
      if (result > 0) {
        return "성공했음";
      } else {
        return "실패했음";
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping(value = "selectClientMsg", produces = "application/json")
  public List<ChatDTO> selectClientMsg(String grade) {
    try {
      List<ChatDTO> result = cservice.selectClientMessageService(grade);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @ResponseBody
  @RequestMapping(value = "selectAllForClient", produces = "application/json")
  public List<ChatDTO> selectAllForClient(String grade) {
    try {
      List<ChatDTO> result = cservice.selectAllForClientService(grade);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
