package my.garden.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dao.BoardReviewDAO;
import my.garden.dto.BoardReviewDTO;
import my.garden.dto.BoardReviewRecommendDTO;
import my.garden.dto.ShopListDTO;
import my.garden.service.BoardReviewService;

@Service
public class BoardReviewServiceImpl implements BoardReviewService {


  @Autowired
  private BoardReviewDAO brDao;

  public List<ShopListDTO> dCompletedPNums(String BR_EMAIL) throws Exception {
    return brDao.dCompletedPNums(BR_EMAIL);
  }

  public int writeReview(BoardReviewDTO dto) throws Exception {
    return brDao.writeReview(dto);
  }

  public List<BoardReviewDTO> reviewList(int br_p_no, int startNum, int endNum) throws Exception {
    return brDao.reviewList(br_p_no, startNum, endNum);
  }

  public String getNavi(int revPage, int br_p_no) throws Exception {
    return brDao.getNavi(revPage, br_p_no);
  }

//	@Transactional("txManager")
//	public int RecommendCountUp(int br_recommend, int br_no) throws Exception{
//		return brDao.RecommendCountUp(br_recommend, br_no);
//	}


  public int recommendCheck(int br_no, String br_email) throws Exception {
    return brDao.recommendCheck(br_no, br_email);
  }

  public int recommendUpdate(String br_email, int br_no, String br_title) throws Exception {
    return brDao.recommendUpdate(br_email, br_no, br_title);
  }

  public int recommendDelete(int br_no, String br_email) throws Exception {
    return brDao.recommendDelete(br_no, br_email);
  }

  @Transactional("txManager")
  public int recommendCount(int br_recommend, int br_no) throws Exception {
    br_recommend = brDao.recommendCount(br_no);
    brDao.updateRecommendCount(br_recommend, br_no);
    return brDao.recommendCount(br_no);
  }

  @Transactional("txManager")
  public int recommend(String br_email, int br_no, String br_title) throws Exception {
    System.out.println("원래 도움돼요 수 : " + brDao.recommendCount(br_no));
    int recommendCheck = brDao.recommendCheck(br_no, br_email);
    if (recommendCheck > 0) { //도움돼요 취소
      brDao.recommendDelete(br_no, br_email);
      System.out.println("도움돼요 취소");
    } else { //도움돼요 (추천하기)
      brDao.recommendUpdate(br_email, br_no, br_title);
      System.out.println("도움돼요");
    }
    System.out.println("도움돼요 수 : " + brDao.recommendCount(br_no));
    return brDao.recommendCount(br_no);
  }

  public List<BoardReviewRecommendDTO> myRecommendNo(String BR_EMAIL) throws Exception {
    return brDao.myRecommendNo(BR_EMAIL);
  }

  //------------------------------------------------------------------------------------

  public BoardReviewDTO oneReview(int br_no) throws Exception {
    return brDao.oneReview(br_no);
  }


  public int updateReview(Map<String, Object> map) throws Exception {
    return brDao.updateReview(map);
  }

  @Transactional("txManager")
  public void deleteReview(int br_no) throws Exception {
    brDao.deleteReview(br_no);
    brDao.deleteRecommend(br_no);
  }

  //------------------------------------------------------------------------------------

  public List<BoardReviewDTO> topRcmdReviews() throws Exception {
    return brDao.topRcmdReviews();
  }


}
