package my.garden.service;

import my.garden.dto.PrivateGardenDTO;

public interface PrivateGardenService {
  public PrivateGardenDTO selectPrivateGardenInfoService(String id) throws Exception;

  public int insertPrivateGardenService(String id, String hurb) throws Exception;

  public int updatePrivateGardenService(String id, String light, String humid, int temp) throws Exception;
}
