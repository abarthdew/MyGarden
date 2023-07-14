package my.garden.service;

import java.util.List;

import my.garden.dto.ProductsDTO;

public interface ProductsService {
  public List<ProductsDTO> selectBestProductsService() throws Exception;

  public int insertProductsService(ProductsDTO dto) throws Exception;

  public int insertImageFileService(String title, String imgs) throws Exception;

  public List<ProductsDTO> selectProductsListByCategoryService(int start, int end, String category) throws Exception;

  public List<ProductsDTO> selectProductsListByPageService(int start, int end) throws Exception;

  public ProductsDTO selectOneProductService(int pnumber) throws Exception;

  public int deleteProductService(int pnumber) throws Exception;

  public List<String> selectTitlesByCategoryService(String p_category) throws Exception;

  public int updateProductService(ProductsDTO dto) throws Exception;

  public List<ProductsDTO> selectProductsListByKeywordService(int start, int end, String keyword) throws Exception;
}
