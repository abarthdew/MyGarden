package my.garden.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.garden.dao.AdminDAO;
import my.garden.dto.AdminMemDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;
import my.garden.schedule.ScheduleTask;
import my.garden.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  AdminDAO dao;

  @Autowired
  ScheduleTask schedule;

  public List<AdminMemDTO> serviceAllMembers() throws Exception {
    return dao.allMembers();
  }

  public int serviceTotalSale() throws Exception {
    return dao.totalSale();
  }

  public int serviceTotalCancel() throws Exception {
    return dao.totalCancel();
  }

  public List<ShopListDTO> servicePopularProduct() throws Exception {
    return dao.popularProduct();
  }

  public int serviceTotalSaleCount() throws Exception {
    return dao.totalSaleCount();
  }

  public int serviceStatCheck(String stat) throws Exception {
    return dao.statCheck(stat);
  }

  public List<ShopListDTO> serviceOrderCheckList(String stat) throws Exception {
    return dao.orderCheckList(stat);
  }

  public List<PrivateGardenDTO> servicePopularHerb() throws Exception {
    return dao.popularHerb();
  }


  @Transactional("txManager")
  public int serviceUpdateOrder(String orderNo, String stat) throws Exception {
    if (stat.equals("배송중")) {
      schedule.setCount(0);
      schedule.startScheduler(orderNo);
    }
    return dao.updateOrder(orderNo, stat);
  }

  public int serviceUpdateSubscribe(String orderNo, String stat) throws Exception {
    return dao.updateSubscribe(orderNo, stat);
  }

  public List<PrivateGardenDTO> servicePrivateList() throws Exception {
    return dao.privateList();
  }

  @Override
  public List<ShopListDTO> serviceSubscribeCheckList(String stat) throws Exception {
    return dao.subscribeCheckList(stat);
  }

}
