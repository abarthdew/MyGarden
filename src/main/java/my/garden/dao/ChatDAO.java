package my.garden.dao;

import java.util.List;

import my.garden.dto.ChatDTO;

public interface ChatDAO {
  public int insertClientSendMessage(String email, String message, String grade);

  public int insertAdminSendMessage(String email, String message, String rEmail, String grade);

  public int updateAnswerState(String email, String c_answer);

  public List<ChatDTO> selectClientMessage(String c_grade);

  public List<ChatDTO> selectAllForClient(String data);
}
