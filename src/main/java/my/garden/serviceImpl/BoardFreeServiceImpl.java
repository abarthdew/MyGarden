package my.garden.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import my.garden.dao.BoardFreeDAO;
import my.garden.dto.BoardFreeDTO;
import my.garden.dto.CommentFreeDTO;
import my.garden.service.BoardFreeService;

@Service
public class BoardFreeServiceImpl implements BoardFreeService {

  @Autowired
  private BoardFreeDAO dao;


  public int serviceWrite(BoardFreeDTO dto) throws Exception {
    return dao.write(dto);
  }


  public int serviceDelete(int bf_no) throws Exception {
    return dao.delete(bf_no);
  }

  public int serviceModify(int bf_no, String column, String value) throws Exception {
    return dao.modify(bf_no, column, value);
  }

  @Transactional("txManager")
  public int serviceBFmodify(int bf_no, String title, String content) throws Exception {
    int tmp1 = dao.modify(bf_no, "bf_title", title);
    int tmp2 = dao.modify(bf_no, "bf_content", content);
    return tmp1 + tmp2;
  }


  public List<BoardFreeDTO> serviceList(int start, int end) throws Exception {
    return dao.list(start, end);

  }

  public List<BoardFreeDTO> serviceSearchList(int start, int end, String searchVal) throws Exception {
    return dao.searchList(start, end, searchVal);
  }

  public BoardFreeDTO serviceRead(int bf_no) throws Exception {
    return dao.read(bf_no);
  }


  public int serviceBoardCountAll() throws Exception {
    return dao.boardCountAll();
  }

  public int serviceSearchCountAll(String searchVal) throws Exception {
    return dao.searchCountAll(searchVal);
  }

  public List<String> serviceGetBoardNavi(int currentPage, int recordTotalCount) throws Exception {
    return dao.getBoardNavi(currentPage, recordTotalCount);
  }

  public BoardFreeDTO serviceMostViewed() throws Exception {
    return dao.mostViewed();
  }

  //댓글
  public int serviceCmtWrite(CommentFreeDTO dto) throws Exception {
    return dao.cmtWrite(dto);
  }

  public int serviceCmtModify(String cf_comment, int cf_no) throws Exception {
    return dao.cmtModify(cf_comment, cf_no);
  }

  public int serviceCmtDelete(String column, int value) throws Exception {
    return dao.cmtDelete(column, value);
  }

  public List<CommentFreeDTO> serviceCmtList(int bf_no, int start, int end) throws Exception {
    return dao.cmtList(bf_no, start, end);
  }

  public int serviceCmtCountAll(int cf_bf_no) throws Exception {
    return dao.cmtCountAll(cf_bf_no);
  }

  public List<String> serviceGetCmtNavi(int currentPage, int cf_bf_no) throws Exception {
    return dao.getCmtNavi(currentPage, cf_bf_no);
  }

}

