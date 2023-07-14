package my.garden.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.garden.dao.ShoppingDAO;
import my.garden.dto.CartDTO;
import my.garden.dto.MembersDTO;
import my.garden.dto.ShopListDTO;
import my.garden.dto.SubscribeDTO;

@Component
public class ShoppingDAOImpl implements ShoppingDAO {

  @Autowired
  private SqlSessionTemplate sst;

  public List<CartDTO> selectCartList(String userId) throws Exception {
    return sst.selectList("ShoppingDAO.selectCartList", userId);
  }

  public int delFromCart(CartDTO dto) throws Exception {
    return sst.delete("ShoppingDAO.delFromCart", dto);
  }

  public int delExpiredCart() throws Exception {
    return sst.delete("ShoppingDAO.delExpiredCart");
  }

  public MembersDTO selectMember(MembersDTO dto, String id) throws Exception {
    dto.setM_email(id);
    return sst.selectOne("LoginDAO.memSelectAll", dto);
  }

  public int insertIntoShopList(ShopListDTO dto) throws Exception {
    return sst.insert("ShoppingDAO.insertIntoShopList", dto);
  }

  public void delCartOrderd(String id, String title) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("id", id);
    map.put("title", title);
    sst.delete("ShoppingDAO.delCartOrderd", map);
  }

  public List<ShopListDTO> selectOrderList(ShopListDTO dto) throws Exception {
    return sst.selectList("ShoppingDAO.selectOrderList", dto);
  }

  public List<Long> selectOrderNo(String id) throws Exception {
    return sst.selectList("ShoppingDAO.selectOrderNo", id);
  }

  public int insertIntoCart(CartDTO dto) throws Exception {
    return sst.insert("ShoppingDAO.insertIntoCart", dto);
  }

  public int isCartExist(CartDTO dto) throws Exception {
    return sst.selectOne("ShoppingDAO.isCartExist", dto);
  }

  public int updateCart(CartDTO dto) throws Exception {
    return sst.update("ShoppingDAO.updateCart", dto);
  }

  public List<Long> searchOrderNo(String id, String orderStatus) throws Exception {
    Map<String, String> map = new HashMap<>();
    map.put("id", id);
    map.put("orderStatus", orderStatus);
    return sst.selectList("ShoppingDAO.searchOrderNo", map);
  }

  public List<Long> searchOrderNoAll(ShopListDTO dto) throws Exception {
    return sst.selectList("ShoppingDAO.searchOrderNoAll", dto);
  }

  public List<Long> searchOrderNoDuration(ShopListDTO dto) throws Exception {
    return sst.selectList("ShoppingDAO.searchOrderNoDuration", dto);
  }

  public int insertSubscribe(SubscribeDTO dto) throws Exception {
    return sst.insert("ShoppingDAO.insertSubscribe", dto);
  }

  public int updateShoplist() throws Exception {
    return sst.update("ShoppingDAO.updateShopList");
  }

  public int updateSubslist() throws Exception {
    return sst.update("ShoppingDAO.updateSubsList");
  }

  public List<SubscribeDTO> selectSubsList(String id) throws Exception {
    return sst.selectList("ShoppingDAO.selectSubsList", id);
  }

  public int subsCancel(SubscribeDTO sbdto) throws Exception {
    return sst.update("ShoppingDAO.subsCancel", sbdto);
  }

  public List<SubscribeDTO> selectSubsSearch(SubscribeDTO dto) throws Exception {
    return sst.selectList("ShoppingDAO.selectSubsSearch", dto);
  }

  public int completeShipping(Long s_orderno) throws Exception {
    return sst.update("ShoppingDAO.completeShipping", s_orderno);
  }

}
