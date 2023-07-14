package my.garden.daoImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dao.BoardReviewDAO;
import my.garden.dto.BoardReviewDTO;
import my.garden.dto.BoardReviewRecommendDTO;
import my.garden.dto.ShopListDTO;

@Repository
public class BoardReviewDAOImpl implements BoardReviewDAO {
  @Autowired
  private SqlSessionTemplate sst;

  public List<ShopListDTO> dCompletedPNums(String BR_EMAIL) {
    return sst.selectList("boardReviewMB.dCompletedPNums", BR_EMAIL);
  }

  public int writeReview(BoardReviewDTO dto) {  //글작성
    return sst.insert("boardReviewMB.writeReview", dto);
  }

  public List<BoardReviewDTO> reviewList(int br_p_no, int startNum, int endNum) { //상세페이지에서 후기목록
    Map<String, Integer> map = new HashMap<>();
    map.put("br_p_no", br_p_no);
    map.put("startNum", startNum);
    map.put("endNum", endNum);
    return sst.selectList("boardReviewMB.reviewList", map);
  }

  public int reviewCount(int br_p_no) { //상세페이지에서 총 후기 개수(상품별 후기 개수)
    return sst.selectOne("boardReviewMB.reviewCount", br_p_no);
  }


  /*페이지 네비게이터*/
  public String getNavi(int currentPage, int br_p_no) {

    int recordTotalCount = reviewCount(br_p_no);
    int recordCountPerPage = 5; //5개의 글이 보이게 한다.
    int naviCountPerPage = 5; //5개의 네비가 보이게 한다.

    int pageTotalCount = recordTotalCount / recordCountPerPage;
    if (recordTotalCount % recordCountPerPage > 0) {
      pageTotalCount++;
    }

    //현재  페이지 오류 검출 및 정정
    /*보안코드 : 현재페이지가 1보다 작다면 1로, 전체페이지보다 크다면 전체페이지(pageTotalCount)로 표시하겠다*/
    if (currentPage < 1) {
      currentPage = 1;
    } else if (currentPage > pageTotalCount) {
      currentPage = pageTotalCount;
    }
    int startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
    int endNavi = startNavi + (naviCountPerPage - 1);

    if (endNavi > pageTotalCount) {
      endNavi = pageTotalCount;
    }

//		System.out.println("현재 위치 : " + currentPage);
//		System.out.println("네비 시작 : " + startNavi);
//		System.out.println("네비 끝 : " + endNavi);

    boolean needPrev = true;
    boolean needNext = true;

    if (startNavi == 1) {
      needPrev = false;
    }
    if (endNavi == pageTotalCount) {
      needNext = false;
    }

    StringBuilder sb = new StringBuilder();

    if (needPrev) {
      int prevStartNavi = startNavi - 1;
      sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"productsRead?pnumber=" + br_p_no + "&qnaPage=1&revPage=" + prevStartNavi + "\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></a></li>");

    }
    for (int i = startNavi; i <= endNavi; i++) {
      sb.append("<li class=\"page-item\"><a class=\"page-link reviewPageNumber pageNumber\" href=\"productsRead?pnumber=" + br_p_no + "&qnaPage=1&revPage=" + i + "\">" + i + "</a></li>");
    }
    if (needNext) {
      int nextEndNavi = endNavi + 1;
      sb.append("<li class=\"page-item\"><a class=\"page-link\" href=\"productsRead?pnumber=" + br_p_no + "&qnaPage=1&revPage=" + nextEndNavi++ + "\"" +
        "							aria-label=\"Next\"> <span aria-hidden=\"true\">&raquo;</span>" +
        "						</a></li>");
    }

    return sb.toString();
  }

  //--------------------------------------------------------------------

  /*도움돼요 전체 추천수 +1 카운트*/
  public int updateRecommendCount(int br_recommend, int br_no) {
    Map<String, Integer> map = new HashMap<>();
    map.put("br_recommend", br_recommend);
    map.put("br_no", br_no);
    return sst.update("boardReviewMB.updateRecommendCount", map);
  }

  /*동일 후기글 추천(도움돼요) 여부 검색*/
  public int recommendCheck(int br_no, String br_email) {
    Map<String, Object> map = new HashMap<>();
    map.put("br_no", br_no);
    map.put("br_email", br_email);
    return sst.selectOne("boardReviewMB.recommendCheck", map);
  }

  /*추천(도움돼요)*/
  public int recommendUpdate(String br_email, int br_no, String br_title) {
    Map<String, Object> map = new HashMap<>();
    map.put("br_email", br_email);
    map.put("br_no", br_no);
    map.put("br_title", br_title);
    return sst.insert("boardReviewMB.recommendUpdate", map);
  }

  /*추천(도움돼요) 취소*/
  public int recommendDelete(int br_no, String br_email) {
    Map<String, Object> map = new HashMap<>();
    map.put("br_no", br_no);
    map.put("br_email", br_email);
    return sst.delete("boardReviewMB.recommendDelete", map);
  }

  /*추천(도움돼요) 수*/
  public int recommendCount(int br_no) {
    return sst.selectOne("boardReviewMB.recommendCount", br_no);
  }

  /*내가 추천한 글번호*/
  public List<BoardReviewRecommendDTO> myRecommendNo(String BR_EMAIL) {
    return sst.selectList("boardReviewMB.myRecommendNo", BR_EMAIL);
  }

  //---------------------------------------------------------------------

  /*후기 수정 전 정보*/
  public BoardReviewDTO oneReview(int br_no) {
    return sst.selectOne("boardReviewMB.oneReview", br_no);
  }

  /*후기 수정*/
  public int updateReview(Map<String, Object> map) {
    return sst.update("boardReviewMB.updateReview", map);
  }

  /*후기 삭제*/
  public int deleteReview(int br_no) {
    return sst.delete("boardReviewMB.deleteReview", br_no);
  }

  /*후기 글 삭제시 해당 추천수 삭제*/
  public int deleteRecommend(int br_no) {
    return sst.delete("boardReviewMB.deleteRecommend", br_no);
  }

  //-----------------------------------------------------------------------

  /*(메인용)도움돼요수 많은 후기글목록*/
  public List<BoardReviewDTO> topRcmdReviews() {
    return sst.selectList("boardReviewMB.topRcmdReviews");
  }


}
