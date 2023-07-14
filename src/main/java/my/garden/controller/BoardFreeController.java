package my.garden.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import my.garden.dto.BoardFreeDTO;
import my.garden.dto.CommentFreeDTO;
import my.garden.dto.MembersDTO;
import my.garden.service.BoardFreeService;
import my.garden.serviceImpl.LoginServiceImpl;

@Controller
public class BoardFreeController {


  @Autowired
  private BoardFreeService dao;

  @Autowired
  HttpSession session;

  @Autowired
  private LoginServiceImpl logDao;

  @RequestMapping("boardFreeList")
  public String boardFreeList(String page, HttpServletRequest request) {
    int nowPage = 0;
    if (page == null) {
      nowPage = 1;
    } else {
      nowPage = Integer.parseInt(page);
    }
    request.setAttribute("page", nowPage);

    int start = (nowPage * 4) - 3;
    int end = nowPage * 4;
    List<BoardFreeDTO> list;
    try {
      list = dao.serviceList(start, end);
      int count = dao.serviceBoardCountAll();
      List<String> navi = dao.serviceGetBoardNavi(nowPage, count);
      request.setAttribute("navi", navi);

      //댓글 갯수 관련
      int size = list.size();
      if (size == 0 || size < 4) {
        for (int i = start; i <= end; i++) {
          for (int j = 0; j < size; j++) {
            int bf_no = list.get(j).getBf_no();
            int tmp = dao.serviceCmtCountAll(bf_no);
            list.get(j).setBf_cmtcount(tmp);
            String img = logDao.memSelectAll(list.get(j).getBf_email()).getM_profile();
            list.get(j).setBf_writerImg(img);
          }
        }
      } else {
        for (int i = start; i <= end; i++) {
          for (int j = 0; j < 4; j++) {
            int bf_no = list.get(j).getBf_no();
            int tmp = dao.serviceCmtCountAll(bf_no);
            list.get(j).setBf_cmtcount(tmp);
            String img = logDao.memSelectAll(list.get(j).getBf_email()).getM_profile();
            list.get(j).setBf_writerImg(img);
          }
        }
      }
      request.setAttribute("list", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "boardFree/boardFreeList";
  }

  @ResponseBody
  @RequestMapping("searchForFree")
  public Map<String, Object> searchForFree(String value, String page) {
    int nowPage = 0;
    if (value != null) {
      session.setAttribute("searchVal", value);
    }
    //System.out.println(session.getAttribute("searchVal")+"현재페이지"+page);
    String ssVal = (String) session.getAttribute("searchVal");
    if (page == null) {
      nowPage = 1;
    } else {
      nowPage = Integer.parseInt(page);
    }
    Map<String, Object> map = new HashMap<>();
    map.put("searchPage", nowPage);
    map.put("searchVal", ssVal);
    int start = (nowPage * 4) - 3;
    int end = nowPage * 4;
    List<BoardFreeDTO> list;
    try {
      String searchVal = "%" + ssVal + "%";
      list = dao.serviceSearchList(start, end, searchVal);
      //System.out.println("검색결과수:"+list.size()+":"+dao.serviceSearchCountAll(searchVal));
      List<String> navi = dao.serviceGetBoardNavi(nowPage, dao.serviceSearchCountAll(searchVal));
      map.put("searchNavi", navi);
      //댓글 갯수 관련
      int count = list.size();
      if (count == 0 || count < 4) {
        for (int i = start; i <= end; i++) {
          for (int j = 0; j < count; j++) {
            int bf_no = list.get(j).getBf_no();
            int tmp = dao.serviceCmtCountAll(bf_no);
            list.get(j).setBf_cmtcount(tmp);
          }
        }
      } else {
        for (int i = start; i <= end; i++) {
          for (int j = 0; j < 4; j++) {
            int bf_no = list.get(j).getBf_no();
            int tmp = dao.serviceCmtCountAll(bf_no);
            list.get(j).setBf_cmtcount(tmp);
          }
        }
      }
      map.put("searchList", list);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @RequestMapping("boardFreeWrite")
  public String boardFreeWrite() {
    return "boardFree/boardFreeWrite";
  }

  @RequestMapping("boardFreeModify")
  public String boardFreeModify(int no, HttpServletRequest request) {
    try {
      BoardFreeDTO dto = dao.serviceRead(no);
      request.setAttribute("dto", dto);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "boardFree/boardFreeModify";
  }

  @RequestMapping("boardFreeModifyProc")
  public String boardFreeModifyProc(int no, String bf_title, String bf_content, HttpServletRequest request) {
    try {
      int result = dao.serviceBFmodify(no, bf_title, bf_content);
      request.setAttribute("result", result);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:boardFreeRead?no=" + no;
  }

  @RequestMapping("boardFreeDelete")
  public String boardFreeDelete(int no, HttpServletRequest request) {
    try {
      int result = dao.serviceDelete(no);
      request.setAttribute("result", result);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "boardFree/boardFreeDeleteProc";
  }

  @RequestMapping("boardFreeWriteProc")
  public String boardFreeWriteProc(BoardFreeDTO dto, HttpServletRequest request) {
    try {
      MembersDTO memDto = logDao.memSelectAll((String) session.getAttribute("loginId"));
      String tmpImg = memDto.getM_profile();
      int result = dao.serviceWrite(new BoardFreeDTO(0, dto.getBf_title(), (String) session.getAttribute("loginName"), (String) session.getAttribute("loginId"), dto.getBf_content(), null, 0, null, 0, null, 0, tmpImg));
      request.setAttribute("result", result);
    } catch (Exception e) {
      e.printStackTrace();

    }
    return "boardFree/boardFreeWriteView";
  }

  @RequestMapping("boardFreeWriteView")
  public String boardFreeWriteView() {
    return "boardFree/boardFreeWriteView";
  }

  //파일업로드
  @ResponseBody
  @RequestMapping("boardFreeFileUpload")
  public String boardFreeFileUpload(MultipartHttpServletRequest request) {
    MultipartFile image = request.getFile("image");
    String resourcesPath = session.getServletContext().getRealPath("/resources/");
    String loginId = (String) session.getAttribute("loginId");
    File dir = new File(resourcesPath + "write/" + loginId);
    if (!dir.exists()) { // 폴더가 있는지 확인.
      System.out.println("폴더생성");
      dir.mkdirs(); // 없으면 생성
    }
    System.out.println(resourcesPath);
    String imgName = System.currentTimeMillis() + "_write.png"; //앞에 아이디붙이기 또는 아이디별 폴더 만들기
    try {
      image.transferTo(new File(resourcesPath + "write/" + loginId + "/" + imgName));
    } catch (Exception e) {
      e.printStackTrace();
    }
    String location = "/resources/write/" + loginId + "/" + imgName;
    return location;
  }


  @RequestMapping("boardFreeRead")
  public String boardFreeRead(int no, String cmtPage, HttpServletRequest request) {
    try {
      BoardFreeDTO page = dao.serviceRead(no);
      String img = logDao.memSelectAll(page.getBf_email()).getM_profile();
      page.setBf_writerImg(img);
      request.setAttribute("page", page);
      //댓글관련
      session.removeAttribute("now"); //새로 글을 읽을때는 제일 최신 댓글페이지 필요-->세션제거
      int lastPage = 0;
      if (cmtPage == null) {
        if (dao.serviceCmtCountAll(no) % 10 == 0) {
          lastPage = (dao.serviceCmtCountAll(no) / 10);
        } else {
          lastPage = (dao.serviceCmtCountAll(no) / 10) + 1;
        }
        //System.out.println("마지막 댓글페이지:"+lastPage);
        request.setAttribute("now", lastPage);
      } else {
        lastPage = Integer.parseInt(cmtPage);
      }
      int start = (lastPage * 10) - 9;
      int end = lastPage * 10;
      List<String> navi = dao.serviceGetCmtNavi(lastPage, no);
      request.setAttribute("navi", navi);
      List<CommentFreeDTO> list = dao.serviceCmtList(no, start, end);
      for (int i = 0; i < list.size(); i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        String stringdate = sdf.format(list.get(i).getCf_writedate());
        list.get(i).setCf_stringdate(stringdate);
        MembersDTO memDto = logDao.memSelectAll(list.get(i).getCf_email());
        list.get(i).setCf_profileImg(memDto.getM_profile());
      }

      request.setAttribute("list", list);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
    return "boardFree/boardFreeRead";
  }

  @ResponseBody
  @RequestMapping("freeCmtWrite")
  public Map<String, Object> freeCmtWrite(int no, String content, HttpServletRequest request) {
    CommentFreeDTO dto = new CommentFreeDTO(no, 0, (String) session.getAttribute("loginName"), (String) session.getAttribute("loginId"), null, content, null, null);
    Map<String, Object> map = new HashMap<>();
    try {
      //1. 댓글 등록
      int result = dao.serviceCmtWrite(dto);
      map.put("result", result);

      //2. 댓글 페이지구하기 (now가 필요 없다! 무조건 마지막 페이지)
      int now = 0;
      if (dao.serviceCmtCountAll(no) % 10 == 0) {
        now = (dao.serviceCmtCountAll(no) / 10);
      } else {
        now = (dao.serviceCmtCountAll(no) / 10) + 1;
      }
      //3. 댓글리스트
      int start = (now * 10) - 9;
      int end = now * 10;
      List<CommentFreeDTO> list;
      list = dao.serviceCmtList(no, start, end);
      for (int i = 0; i < list.size(); i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        String stringdate = sdf.format(list.get(i).getCf_writedate());
        list.get(i).setCf_stringdate(stringdate);

        MembersDTO memDto = logDao.memSelectAll(list.get(i).getCf_email());
        list.get(i).setCf_profileImg(memDto.getM_profile());

        map.put("list", list);
        List<String> navi = dao.serviceGetCmtNavi(now, no);
        map.put("navi", navi);
        map.put("page", now);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @ResponseBody
  @RequestMapping("freeCmtNaviProc")
  public Map<String, Object> freeCmtNaviProc(int no, int page) {
    session.setAttribute("now", page); //네비 클릭시 세션에 저장!
    int start = (page * 10) - 9;
    int end = page * 10;
    Map<String, Object> map = new HashMap<>();
    List<CommentFreeDTO> list;
    try {
      list = dao.serviceCmtList(no, start, end);
      for (int i = 0; i < list.size(); i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        String stringdate = sdf.format(list.get(i).getCf_writedate());
        list.get(i).setCf_stringdate(stringdate);
        MembersDTO memDto = logDao.memSelectAll(list.get(i).getCf_email());
        list.get(i).setCf_profileImg(memDto.getM_profile());
      }
      map.put("list", list);
      List<String> navi = dao.serviceGetCmtNavi(page, no);
      map.put("navi", navi);
      map.put("page", page);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @ResponseBody
  @RequestMapping("freeCmtModify")
  public Map<String, Object> freeCmtModify(int no, int seq, String cmt) {
    Map<String, Object> map = new HashMap<>();
    try {
      //1. 댓글 수정
      int result = dao.serviceCmtModify(cmt, seq);
      map.put("result", result);

      //2.현재페이지구하기
      int now = 0;
      if (session.getAttribute("now") == null) {
        if (dao.serviceCmtCountAll(no) % 10 == 0) {
          now = (dao.serviceCmtCountAll(no) / 10);
        } else {
          now = (dao.serviceCmtCountAll(no) / 10) + 1;
        }
      } else {
        now = (Integer) session.getAttribute("now");
      }
      //System.out.println("현재 댓글페이지:"+now);

      //3. 댓글리스트
      int start = (now * 10) - 9;
      int end = now * 10;
      List<CommentFreeDTO> list;
      list = dao.serviceCmtList(no, start, end);
      for (int i = 0; i < list.size(); i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        String stringdate = sdf.format(list.get(i).getCf_writedate());
        list.get(i).setCf_stringdate(stringdate);
        MembersDTO memDto = logDao.memSelectAll(list.get(i).getCf_email());
        list.get(i).setCf_profileImg(memDto.getM_profile());
        map.put("list", list);
        List<String> navi = dao.serviceGetCmtNavi(now, no);
        map.put("navi", navi);
        map.put("page", now);

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @ResponseBody
  @RequestMapping("freeCmtDelete")
  public Map<String, Object> freeCmtDelete(int seq, int no) {
    Map<String, Object> map = new HashMap<>();
    try {
      //1. 댓글 삭제
      int result = dao.serviceCmtDelete("cf_no", seq);
      map.put("result", result);

      //2.현재페이지구하기
      int now = 0;
      if (session.getAttribute("now") == null) {
        if (dao.serviceCmtCountAll(no) % 10 == 0) {
          now = (dao.serviceCmtCountAll(no) / 10);
        } else {
          now = (dao.serviceCmtCountAll(no) / 10) + 1;
        }
      } else {
        now = (Integer) session.getAttribute("now");
      }
      //System.out.println("현재 댓글페이지:"+now);

      //3. 댓글리스트
      int start = (now * 10) - 9;
      int end = now * 10;
      List<CommentFreeDTO> list;
      list = dao.serviceCmtList(no, start, end);
      for (int i = 0; i < list.size(); i++) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm");
        String stringdate = sdf.format(list.get(i).getCf_writedate());
        list.get(i).setCf_stringdate(stringdate);
        MembersDTO memDto = logDao.memSelectAll(list.get(i).getCf_email());
        list.get(i).setCf_profileImg(memDto.getM_profile());
        map.put("list", list);
        List<String> navi = dao.serviceGetCmtNavi(now, no);
        map.put("navi", navi);
        map.put("page", now);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }


}
