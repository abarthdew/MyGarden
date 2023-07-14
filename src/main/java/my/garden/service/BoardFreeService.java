package my.garden.service;

import java.sql.Timestamp;
import java.util.List;

import my.garden.dto.BoardFreeDTO;
import my.garden.dto.CommentFreeDTO;

public interface BoardFreeService {
  public int serviceWrite(BoardFreeDTO dto) throws Exception;

  public int serviceDelete(int bf_no) throws Exception;

  public int serviceModify(int bf_no, String column, String value) throws Exception;

  public List<BoardFreeDTO> serviceList(int start, int end) throws Exception;

  public List<BoardFreeDTO> serviceSearchList(int start, int end, String searchVal) throws Exception;

  public BoardFreeDTO serviceRead(int bf_no) throws Exception;

  public List<String> serviceGetBoardNavi(int currentPage, int recordTotalCount) throws Exception;

  public int serviceBoardCountAll() throws Exception;

  public int serviceBFmodify(int bf_no, String title, String content) throws Exception;

  public int serviceSearchCountAll(String searchVal) throws Exception;

  public BoardFreeDTO serviceMostViewed() throws Exception;

  //댓글
  public int serviceCmtWrite(CommentFreeDTO dto) throws Exception;

  public int serviceCmtModify(String cf_comment, int cf_no) throws Exception;

  public int serviceCmtDelete(String column, int value) throws Exception;

  public List<CommentFreeDTO> serviceCmtList(int cf_bf_no, int start, int end) throws Exception;

  public int serviceCmtCountAll(int cf_bf_no) throws Exception;

  public List<String> serviceGetCmtNavi(int currentPage, int cf_bf_no) throws Exception;
}
