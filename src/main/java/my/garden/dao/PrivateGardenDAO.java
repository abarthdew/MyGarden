package my.garden.dao;

import my.garden.dto.PrivateGardenDTO;

public interface PrivateGardenDAO {
  public PrivateGardenDTO selectPrivateGardenInfo(String G_email);

  public int updateGrade(String g_email);

  public int insertPrivateGarden(PrivateGardenDTO dto);

  public int updatePrivateGarden(String id, String light, String humid, int temp);
}
