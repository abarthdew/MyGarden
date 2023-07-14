package my.garden.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.garden.dao.ChatDAO;
import my.garden.dto.ChatDTO;
import my.garden.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

  @Autowired
  private ChatDAO cdao;

  public int insertClientSendMessageService(String email, String message, String grade) throws Exception {
    return cdao.insertClientSendMessage(email, message, grade);
  }

  public int insertAdminSendMessageService(String email, String message, String rEmail, String grade) throws Exception {
    int result = cdao.updateAnswerState(rEmail, "y");
    return cdao.insertAdminSendMessage(email, message, rEmail, grade);
  }

  public List<ChatDTO> selectClientMessageService(String data) throws Exception {
    return cdao.selectClientMessage(data);
  }

  public List<ChatDTO> selectAllForClientService(String data) throws Exception {
    return cdao.selectAllForClient(data);
  }

}
