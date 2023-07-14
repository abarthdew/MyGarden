package my.garden.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.garden.dao.AdminDAO;
import my.garden.dto.AdminMemDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

  @Autowired
  SqlSessionTemplate sst;

  public List<AdminMemDTO> allMembers() {
    return sst.selectList("AdminDAO.allMembers");
  }

  public int totalSale() {
    if (sst.selectOne("AdminDAO.totalSale") == null) {
      return 0;
    } else {
      return sst.selectOne("AdminDAO.totalSale");
    }
  }

  public int totalCancel() {
    if (sst.selectOne("AdminDAO.totalCancel") == null) {
      return 0;
    } else {
      return sst.selectOne("AdminDAO.totalCancel");
    }
  }

  public List<ShopListDTO> popularProduct() {
    return sst.selectList("AdminDAO.popularProducts");

  }

  public int totalSaleCount() {
    return sst.selectOne("AdminDAO.totalSaleCount");
  }

  public int statCheck(String stat) {
    return sst.selectOne("AdminDAO.statCheck", stat);
  }

  public List<ShopListDTO> orderCheckList(String stat) {
    return sst.selectList("AdminDAO.orderCheckList", stat);
  }

  public List<ShopListDTO> subscribeCheckList(String stat) {
    return sst.selectList("AdminDAO.subscribeCheckList", stat);
  }

  public int updateOrder(String orderNo, String stat) {
    Map<String, Object> map = new HashMap<>();
    map.put("no", orderNo);
    map.put("stat", stat);
    return sst.update("AdminDAO.updateOrder", map);
  }

  public int updateSubscribe(String orderNo, String stat) {
    Map<String, String> map = new HashMap<>();
    map.put("no", orderNo);
    map.put("stat", stat);
    return sst.update("AdminDAO.updateSubscribe", map);
  }

  public List<PrivateGardenDTO> privateList() {
    return sst.selectList("AdminDAO.privateList");
  }

  public List<PrivateGardenDTO> popularHerb() {
    return sst.selectList("AdminDAO.popularHerb");
  }

}
