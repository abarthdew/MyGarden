package my.garden.daoImpl;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.garden.dao.PrivateGardenDAO;
import my.garden.dto.PrivateGardenDTO;

@Repository
public class PrivateGardenDAOImpl implements PrivateGardenDAO {

  @Autowired
  private SqlSessionTemplate sst;

  public PrivateGardenDTO selectPrivateGardenInfo(String g_email) {
    try {
      return sst.selectOne("PrivateGardenDAO.selectPrivateGarden", g_email);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int updateGrade(String id) {
    String grade = "private";
    String[] arr = new String[]{grade, id};
    try {
      return sst.update("PrivateGardenDAO.updateGrade", arr);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int insertPrivateGarden(PrivateGardenDTO dto) {
    try {
      return sst.insert("PrivateGardenDAO.insertPrivateGarden", dto);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updatePrivateGarden(String id, String light, String humid, int temp) {
    Map<String, Object> map = new HashMap<>();
    map.put("id", id);
    map.put("light", light);
    map.put("humid", humid);
    map.put("temper", temp);
    try {
      return sst.update("PrivateGardenDAO.updatePrivateGarden", map);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

}
