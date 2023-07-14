package my.garden.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import my.garden.dto.AdminMemDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;

public interface AdminService {
  public List<AdminMemDTO> serviceAllMembers() throws Exception;

  public int serviceTotalSale() throws Exception;

  public int serviceTotalCancel() throws Exception;

  public List<ShopListDTO> servicePopularProduct() throws Exception;

  public int serviceTotalSaleCount() throws Exception;

  public int serviceStatCheck(String stat) throws Exception;

  public List<ShopListDTO> serviceOrderCheckList(String stat) throws Exception;

  public List<ShopListDTO> serviceSubscribeCheckList(String stat) throws Exception;

  public int serviceUpdateSubscribe(String orderNo, String stat) throws Exception;

  public List<PrivateGardenDTO> servicePrivateList() throws Exception;

  public int serviceUpdateOrder(String orderNo, String stat) throws Exception;

  public List<PrivateGardenDTO> servicePopularHerb() throws Exception;
}
