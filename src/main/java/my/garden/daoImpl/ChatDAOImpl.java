package my.garden.daoImpl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.garden.dao.ChatDAO;
import my.garden.dto.ChatDTO;

@Repository
public class ChatDAOImpl implements ChatDAO {

  @Autowired
  private SqlSessionTemplate sst;

  public int insertClientSendMessage(String email, String message, String grade) {
    String[] msg = new String[]{email, message, grade, "n"};
    try {
      return sst.insert("ChatDAO.insertClientSendMessage", msg);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int insertAdminSendMessage(String email, String message, String rEmail, String grade) {
    String[] msg = new String[]{email, message, rEmail, grade, "y"};
    try {
      return sst.insert("ChatDAO.insertAdminSendMessage", msg);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateAnswerState(String email, String c_answer) {
    String[] answer = new String[]{email, c_answer};
    try {
      return sst.update("ChatDAO.updateAnswerState", answer);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public List<ChatDTO> selectClientMessage(String c_grade) {
    try {
      return sst.selectList("ChatDAO.selectClientMessage", c_grade);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ChatDTO> selectAllForClient(String data) {
    String[] arr = new String[]{data, data};
    try {
      return sst.selectList("ChatDAO.selectAllForClient", arr);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}
