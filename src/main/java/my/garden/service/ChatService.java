package my.garden.service;

import java.util.List;

import my.garden.dto.ChatDTO;

public interface ChatService {
  public int insertClientSendMessageService(String email, String message, String grade) throws Exception;

  public int insertAdminSendMessageService(String email, String message, String rEmail, String grade) throws Exception;

  public List<ChatDTO> selectClientMessageService(String data) throws Exception;

  public List<ChatDTO> selectAllForClientService(String data) throws Exception;
}
