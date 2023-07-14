package my.garden.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.garden.dao.ProductsDAO;
import my.garden.dto.ProductsDTO;

@Repository
public class ProductsDAOImpl implements ProductsDAO {

  @Autowired
  private SqlSessionTemplate sst;

  public List<ProductsDTO> selectBestProducts() {
    try {
      return sst.selectList("ProductsDAO.selectBestProducts");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int insertProducts(ProductsDTO dto) {
    try {
      return sst.insert("ProductsDAO.insertProducts", dto);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int insertImageFile(String title, String imgs) {
    String[] info = new String[]{title, imgs};
    try {
      return sst.insert("ProductsDAO.insertImageFile", info);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public List<ProductsDTO> selectProductsListByCategory(int start, int end, String category) {
    Map<String, Object> map = new HashMap<>();
    map.put("start", start);
    map.put("end", end);
    map.put("category", category);
    try {
      return sst.selectList("ProductsDAO.selectProductsListByCategory", map);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<ProductsDTO> selectProductsListByPage(int start, int end) {
    int[] arr = {start, end};
    try {
      return sst.selectList("ProductsDAO.selectProductsListByPage", arr);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public ProductsDTO selectOneProduct(int p_no) {
    try {
      return sst.selectOne("ProductsDAO.selectOneProduct", p_no);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int deleteProduct(int p_no) {
    try {
      return sst.delete("ProductsDAO.deleteProduct", p_no);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int deleteImagePath(String img_p_title) {
    try {
      return sst.delete("ProductsDAO.deleteImagePath", img_p_title);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateProduct(ProductsDTO dto) {
    try {
      return sst.update("ProductsDAO.updateProduct", dto);
    } catch (Exception e) {
      e.printStackTrace();
      return -1;
    }
  }

  public List<ProductsDTO> selectProductsListByKeyword(int start, int end, String keyword) {
    Map<String, Object> map = new HashMap<>();
    map.put("start", start);
    map.put("end", end);
    map.put("keyword", keyword);
    try {
      return sst.selectList("ProductsDAO.selectProductsListByKeyword", map);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public List<String> selectTitlesByCategory(String p_category) throws Exception {
    return sst.selectList("ProductsDAO.selectTitlesByCategory", p_category);
  }

  public int updateSales(int p_no, int count) throws Exception {
    Map<String, Integer> map = new HashMap<>();
    map.put("p_no", p_no);
    map.put("count", count);
    return sst.update("ProductsDAO.updateSales", map);
  }

}
