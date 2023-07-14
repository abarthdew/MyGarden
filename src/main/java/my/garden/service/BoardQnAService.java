package my.garden.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dto.BoardQnADTO;
import my.garden.dto.CommentFreeDTO;
import my.garden.dto.CommentQnADTO;

public interface BoardQnAService {
  public int writeQnA(BoardQnADTO dto) throws Exception;

  public List<BoardQnADTO> qnaList(int bq_p_no, int startNum2, int endNum2) throws Exception;

  public List<BoardQnADTO> qnaListforAdmin() throws Exception;

  public int qnaCount(int bq_p_no) throws Exception;

  public String getNavi(int qnaPage, int bq_p_no) throws Exception;

  public BoardQnADTO readQnA(int bq_no) throws Exception;

  public int updateQnA(BoardQnADTO dto, int bq_no) throws Exception;

  public int updateImg1(int bq_no, String bq_imagepath) throws Exception;

  public int updateImg2(int bq_no, String bq_imagepath) throws Exception;

  public int updateImg3(int bq_no, String bq_imagepath) throws Exception;

  public int deleteQnA(int bq_no) throws Exception;

  //-----------------------------------관리자----------------------------------------------
  public int writeComment(int cq_n, CommentQnADTO dto) throws Exception;

  public CommentQnADTO commentList(int cq_no) throws Exception;

  public int updateComment(int cq_no, String cq_comment) throws Exception;

  public int deleteComment(int cq_no) throws Exception;
}
