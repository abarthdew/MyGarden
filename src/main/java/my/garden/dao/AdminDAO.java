package my.garden.dao;

import java.util.List;

import my.garden.dto.AdminMemDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;

public interface AdminDAO {
  public List<AdminMemDTO> allMembers();

  public int totalSale();

  public int totalCancel();

  public List<ShopListDTO> popularProduct();

  public int totalSaleCount();

  public int statCheck(String stat);

  public List<ShopListDTO> orderCheckList(String stat);

  public List<ShopListDTO> subscribeCheckList(String stat);

  public int updateOrder(String orderNo, String stat);

  public int updateSubscribe(String orderNo, String stat);

  public List<PrivateGardenDTO> privateList();

  public List<PrivateGardenDTO> popularHerb();
}
