package my.garden.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.garden.dao.BoardFreeDAO;
import my.garden.dto.BoardFreeDTO;
import my.garden.dto.CommentFreeDTO;

@Repository
public class BoardFreeDAOImpl implements BoardFreeDAO {

  @Autowired
  private SqlSessionTemplate sst;

  public int write(BoardFreeDTO dto) {
    return sst.insert("BoardFreeDAO.write", dto);
  }

  public List<BoardFreeDTO> list(int start, int end) {
    Map<String, Integer> map = new HashMap<>();
    map.put("start", start);
    map.put("end", end);
    return sst.selectList("BoardFreeDAO.list", map);
  }

  public List<BoardFreeDTO> searchList(int start, int end, String searchVal) {
    Map<String, Object> map = new HashMap<>();
    map.put("start", start);
    map.put("end", end);
    map.put("searchVal", searchVal);
    return sst.selectList("BoardFreeDAO.searchList", map);
  }

  public int searchCountAll(String searchVal) {
    return sst.selectOne("BoardFreeDAO.searchCountAll", searchVal);
  }

  public BoardFreeDTO read(int bf_no) {
    sst.selectOne("BoardFreeDAO.viewCount", bf_no);
    return sst.selectOne("BoardFreeDAO.read", bf_no);
  }

  public int delete(int bf_no) {
    Map<String, Object> map = new HashMap<>();
    map.put("column", "cf_bf_no");
    map.put("value", bf_no);
    sst.delete("BoardFreeDAO.cmtDelete", map);//글삭제시 관련 댓글도 다 삭제
    return sst.delete("BoardFreeDAO.delete", bf_no);
  }

  public int modify(int bf_no, String column, String value) {
    Map<String, Object> map = new HashMap<>();
    map.put("bf_no", bf_no);
    map.put("column", column);
    map.put("value", value);
    return sst.delete("BoardFreeDAO.modify", map);
  }

  public int boardCountAll() {
    return sst.selectOne("BoardFreeDAO.boardCountAll");
  }

  public BoardFreeDTO mostViewed() {
    return sst.selectOne("BoardFreeDAO.mostViewed");
  }

  //댓글 관련 DAO
  public int cmtCountAll(int cf_bf_no) {
    return sst.selectOne("BoardFreeDAO.cmtCountAll", cf_bf_no);
  }

  public int cmtWrite(CommentFreeDTO dto) {
    return sst.insert("BoardFreeDAO.cmtWrite", dto);
  }

  public List<CommentFreeDTO> cmtList(int bf_no, int start, int end) {
    Map<String, Integer> map = new HashMap<>();
    map.put("bf_no", bf_no);
    map.put("start", start);
    map.put("end", end);
    return sst.selectList("BoardFreeDAO.cmtList", map);
  }

  public int cmtModify(String cf_comment, int cf_no) {
    Map<String, Object> map = new HashMap<>();
    map.put("cf_comment", cf_comment);
    map.put("cf_no", cf_no);
    return sst.update("BoardFreeDAO.cmtModify", map);
  }

  public int cmtDelete(String column, int value) {
    Map<String, Object> map = new HashMap<>();
    map.put("column", column);
    map.put("value", value);
    return sst.delete("BoardFreeDAO.cmtDelete", map);
  }


  //게시판 네비
  public List<String> getBoardNavi(int currentPage, int recordTotalCount) throws Exception {

    int recordCountPerPage = 4;
    int naviCountPerPage = 3;
    int pageTotalCount = 0; // 변수만드는 용
    if (recordTotalCount % recordCountPerPage == 0) {
      pageTotalCount = recordTotalCount / recordCountPerPage;
    } else {
      pageTotalCount = recordTotalCount / recordCountPerPage + 1;
    }

    // 현재페이지 오류 검출 및 정정 (공격을 막기 위해 작성하는 일종의 보안코드)
    if (currentPage < 1) {
      currentPage = 1;
    } else if (currentPage > pageTotalCount) {
      currentPage = pageTotalCount;
    }
    // 최소페이지 == currntPage / 최대페이지 == pageTotalCount
    // 내가 위치한 기준으로 네비게이터의 위치가 1~10 이라는것을 알아야함
    int startNavi;
    int endNavi;

    startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
    // 10의자리는 그대로, 1의자리는 1
    // 현재페이지에서 1을 빼야 한다. --> 30페이지가 21~30 에 있어야 하기 때문!! 31은 31~40에 있어야하는데, 계산하면 31이 된다.
    //(currentPage-1)/10*10+1;

    endNavi = startNavi + (naviCountPerPage - 1);

    if (endNavi > pageTotalCount) {
      endNavi = pageTotalCount;
    }
    //총페이지가 15인데, endNavi가  20이라면 말이 안되기 때문에.
//		System.out.println("현재위치 : " + currentPage);
//		System.out.println("네비시작 : " + startNavi);
//		System.out.println("네비 끝 : " + endNavi);

    boolean needPrev = true;
    boolean needNext = true;

    if (startNavi == 1) {
      needPrev = false;
    }

    if (endNavi == pageTotalCount) {
      needNext = false;
    }

    //StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<String>();
    //지금까지 만든걸 String에 담아서 보낸다.
    if (needPrev) {
      list.add("<");
    }
    for (int i = startNavi; i <= endNavi; i++) {
      //sb.append("<a href='list.board?currentPage="+i+"'>"+i + "</a>");
      //sb.append(i+"");
      list.add(i + "");
    }
    if (needNext) {
      //sb.append("다음>");
      list.add(">");
    }
    //return sb.toString();
    return list;
  }

  public List<String> getCmtNavi(int currentPage, int bf_no) throws Exception {
    int recordTotalCount = this.cmtCountAll(bf_no);
    //System.out.println("댓글 레코드토탈카운트:"+recordTotalCount);

    int recordCountPerPage = 10;
    int naviCountPerPage = 5;
    int pageTotalCount = 0; // 변수만드는 용
    if (recordTotalCount % recordCountPerPage == 0) {
      pageTotalCount = recordTotalCount / recordCountPerPage;
    } else {
      pageTotalCount = recordTotalCount / recordCountPerPage + 1;
    }

    // 현재페이지 오류 검출 및 정정 (공격을 막기 위해 작성하는 일종의 보안코드)
    if (currentPage < 1) {
      currentPage = 1;
    } else if (currentPage > pageTotalCount) {
      currentPage = pageTotalCount;
    }
    // 최소페이지 == currntPage / 최대페이지 == pageTotalCount
    // 내가 위치한 기준으로 네비게이터의 위치가 1~10 이라는것을 알아야함
    int startNavi;
    int endNavi;

    startNavi = (currentPage - 1) / naviCountPerPage * naviCountPerPage + 1;
    // 10의자리는 그대로, 1의자리는 1
    // 현재페이지에서 1을 빼야 한다. --> 30페이지가 21~30 에 있어야 하기 때문!! 31은 31~40에 있어야하는데, 계산하면 31이 된다.
    //(currentPage-1)/10*10+1;

    endNavi = startNavi + (naviCountPerPage - 1);

    if (endNavi > pageTotalCount) {
      endNavi = pageTotalCount;
    }
    //총페이지가 15인데, endNavi가  20이라면 말이 안되기 때문에.
    //System.out.println("현재위치 : " + currentPage);
    //System.out.println("네비시작 : " + startNavi);
    //System.out.println("네비 끝 : " + endNavi);

    boolean needPrev = true;
    boolean needNext = true;

    if (startNavi == 1) {
      needPrev = false;
    }

    if (endNavi == pageTotalCount) {
      needNext = false;
    }

    //StringBuilder sb = new StringBuilder();
    List<String> list = new ArrayList<String>();
    //지금까지 만든걸 String에 담아서 보낸다.
    if (needPrev) {
      list.add("<");
    }
    for (int i = startNavi; i <= endNavi; i++) {
      //sb.append("<a href='list.board?currentPage="+i+"'>"+i + "</a>");
      //sb.append(i+"");
      list.add(i + "");
    }
    if (needNext) {
      //sb.append("다음>");
      list.add(">");
    }
    //return sb.toString();
    return list;
  }

}
