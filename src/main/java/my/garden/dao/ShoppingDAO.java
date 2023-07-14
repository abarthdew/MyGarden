package my.garden.dao;

import java.util.List;

import my.garden.dto.CartDTO;
import my.garden.dto.MembersDTO;
import my.garden.dto.ShopListDTO;
import my.garden.dto.SubscribeDTO;

public interface ShoppingDAO {
  public List<CartDTO> selectCartList(String userId) throws Exception;

  public int delFromCart(CartDTO dto) throws Exception;

  public int delExpiredCart() throws Exception;

  public MembersDTO selectMember(MembersDTO dto, String id) throws Exception;

  public int insertIntoShopList(ShopListDTO dto) throws Exception;

  public void delCartOrderd(String id, String title) throws Exception;

  public List<ShopListDTO> selectOrderList(ShopListDTO dto) throws Exception;

  public List<Long> selectOrderNo(String id) throws Exception;

  public int insertIntoCart(CartDTO dto) throws Exception;

  public int isCartExist(CartDTO dto) throws Exception;

  public int updateCart(CartDTO dto) throws Exception;

  public List<Long> searchOrderNo(String id, String orderStatus) throws Exception;

  public List<Long> searchOrderNoAll(ShopListDTO dto) throws Exception;

  public List<Long> searchOrderNoDuration(ShopListDTO dto) throws Exception;

  public int insertSubscribe(SubscribeDTO dto) throws Exception;

  public int updateShoplist() throws Exception;

  public int updateSubslist() throws Exception;

  public List<SubscribeDTO> selectSubsList(String id) throws Exception;

  public int subsCancel(SubscribeDTO sbdto) throws Exception;

  public List<SubscribeDTO> selectSubsSearch(SubscribeDTO dto) throws Exception;

  public int completeShipping(Long s_orderno) throws Exception;
}
