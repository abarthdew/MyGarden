package my.garden.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dao.BoardQnADAO;
import my.garden.dto.BoardQnADTO;
import my.garden.dto.CommentFreeDTO;
import my.garden.dto.CommentQnADTO;
import my.garden.service.BoardQnAService;

@Service
public class BoardQnAServiceImpl implements BoardQnAService {

  @Autowired
  private BoardQnADAO qnaDao;

  public int writeQnA(BoardQnADTO dto) throws Exception {
    return qnaDao.writeQnA(dto);
  }

  public List<BoardQnADTO> qnaList(int bq_p_no, int startNum2, int endNum2) throws Exception {
    return qnaDao.qnaList(bq_p_no, startNum2, endNum2);
  }

  public List<BoardQnADTO> qnaListforAdmin() throws Exception {
    return qnaDao.qnaListforAdmin();
  }

  public int qnaCount(int bq_p_no) throws Exception {
    return qnaDao.qnaCount(bq_p_no);
  }

  public String getNavi(int qnaPage, int bq_p_no) throws Exception {
    return qnaDao.getNavi(qnaPage, bq_p_no);
  }

  public BoardQnADTO readQnA(int bq_no) throws Exception {
    return qnaDao.readQnA(bq_no);
  }

  @Transactional("txManager")
  public int updateQnA(BoardQnADTO dto, int bq_no) throws Exception {
    qnaDao.readQnA(bq_no);
    return qnaDao.updateQnA(dto, bq_no);
  }

  public int updateImg1(int bq_no, String bq_imagepath) throws Exception {
    return qnaDao.updateQnAImg1(bq_no, bq_imagepath);
  }

  public int updateImg2(int bq_no, String bq_imagepath) throws Exception {
    return qnaDao.updateQnAImg2(bq_no, bq_imagepath);
  }

  public int updateImg3(int bq_no, String bq_imagepath) throws Exception {
    return qnaDao.updateQnAImg3(bq_no, bq_imagepath);
  }

  @Transactional("txManager")
  public int deleteQnA(int bq_no) throws Exception {
    int result1 = qnaDao.deleteQnA(bq_no);
    int result2 = qnaDao.deleteComment(bq_no);
    return result1 + result2;
  }

  //------------------------------------관리자----------------------------------------
  @Transactional("txManager")
  public int writeComment(int cq_no, CommentQnADTO dto) throws Exception {
    int result1 = qnaDao.writeComment(dto);
    int result2 = qnaDao.setAnsY(cq_no);
    return result1 + result2;
  }

  public CommentQnADTO commentList(int cq_no) throws Exception {
    return qnaDao.commentList(cq_no);
  }

  public int updateComment(int cq_no, String cq_comment) throws Exception {
    return qnaDao.updateComment(cq_no, cq_comment);
  }

  @Transactional("txManager")
  public int deleteComment(int cq_no) throws Exception {
    int result1 = qnaDao.deleteComment(cq_no);
    int result2 = qnaDao.setAnsN(cq_no);
    return result1 + result2;
  }

}
