package my.garden.service;

import java.util.List;

import my.garden.dto.CartDTO;
import my.garden.dto.MembersDTO;
import my.garden.dto.ShopListDTO;
import my.garden.dto.SubscribeDTO;

public interface ShoppingService {

  public List<CartDTO> getCartList(String userId) throws Exception;

  public int delFromCart(String userId, CartDTO dto) throws Exception;

  public MembersDTO getMember(MembersDTO dto, String id) throws Exception;

  public void insertIntoShopList(List<ShopListDTO> list, Long orderNo, String id) throws Exception;

  public List<List<ShopListDTO>> getOrderList(String id) throws Exception;

  public int insertIntoCart(CartDTO dto, String id) throws Exception;

  public List<List<ShopListDTO>> getOrderSearch(String id, String orderDuration, String orderStatus) throws Exception;

  public int insertSubscribe(SubscribeDTO sbdto, String id) throws Exception;

  public List<SubscribeDTO> getSubsList(String id) throws Exception;

  public int subsCancel(String id, SubscribeDTO sbdto) throws Exception;

  public List<SubscribeDTO> getSubsSearch(String id, SubscribeDTO sbdto) throws Exception;

  public List<ShopListDTO> getOrderShipping(Long s_orderno, String id) throws Exception;

}
