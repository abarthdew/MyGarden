package my.garden.dao;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import my.garden.dto.BoardReviewDTO;
import my.garden.dto.BoardReviewRecommendDTO;
import my.garden.dto.ShopListDTO;


public interface BoardReviewDAO {
  public List<ShopListDTO> dCompletedPNums(String BR_EMAIL) throws Exception;

  public int writeReview(BoardReviewDTO dto) throws Exception;

  public List<BoardReviewDTO> reviewList(int br_p_no, int startNum, int endNum) throws Exception;

  public String getNavi(int currentPage, int br_p_no) throws Exception;

  public int updateRecommendCount(int br_recommend, int br_no) throws Exception;

  public int recommendCheck(int br_no, String br_email) throws Exception;

  public int recommendUpdate(String br_email, int br_no, String br_title) throws Exception;

  public int recommendDelete(int br_no, String br_email) throws Exception;

  public int recommendCount(int br_no) throws Exception;

  public List<BoardReviewRecommendDTO> myRecommendNo(String BR_EMAIL) throws Exception;


  public BoardReviewDTO oneReview(int br_no) throws Exception;

  public int updateReview(Map<String, Object> map) throws Exception;

  public int deleteReview(int br_no) throws Exception;

  public int deleteRecommend(int br_no) throws Exception;

  public List<BoardReviewDTO> topRcmdReviews() throws Exception;
}
