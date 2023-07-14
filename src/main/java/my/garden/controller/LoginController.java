package my.garden.controller;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;

import my.garden.dto.BoardFreeDTO;
import my.garden.dto.MembersDTO;
import my.garden.dto.ProductsDTO;
import my.garden.dto.ShopListDTO;
import my.garden.dto.SubscribeDTO;
import my.garden.service.BoardFreeService;
import my.garden.service.BoardReviewService;
import my.garden.service.ProductsService;
import my.garden.serviceImpl.LoginServiceImpl;

@Controller
public class LoginController {

  @Autowired
  private ProductsService pservice;
  @Autowired
  private BoardReviewService brService;
  @Autowired
  private BoardFreeService bfs;
  @Autowired
  LoginServiceImpl loginserv;
  @Autowired
  HttpServletResponse response;
  @Autowired
  HttpSession session;

  @RequestMapping("/login")
  public String Login() {
    if (session.getAttribute("loginName") != null) {
      return "home";
    } else {
      return "login/login";
    }
  }

  @RequestMapping("/join")
  public String Join() {
    if (session.getAttribute("loginName") != null) {
      return "home";
    } else {
      return "login/join";
    }
  }

  @RequestMapping("/joinSubmit")
  public String JoinSubmit(MembersDTO dto, MultipartFile ex_file) {
    int result = loginserv.joinSubmit(dto, ex_file);
    if (result > 0) {
      return "login/joinThrough";
    } else {
      return "error";
    }
  }

  @ResponseBody
  @RequestMapping("/emailCheck")
  public boolean eamilCheck(String key) {
    return loginserv.emailDupCheck(key);
  }

  @ResponseBody
  @RequestMapping("/phoneCheck")
  public boolean phoneCheck(String key) {
    return loginserv.phoneDupCheck(key);
  }

  @ResponseBody
  @RequestMapping("/gardenCheck")
  public boolean gardenCheck(String key) {
    return loginserv.gardenDupCheck(key);
  }

  @RequestMapping("/isLoginOk")
  public String isLoginOk(HttpServletRequest request, String loginId, String loginPw) {
    loginserv.isLoginOk(loginId, loginPw);
    if (loginserv.isLoginOk(loginId, loginPw) == null) {
      return "login/loginThrough";
    } else {
      session.setAttribute("loginId", loginId);
      String loginName = loginserv.getName(loginId);
      session.setAttribute("loginName", loginName);
      String grade = loginserv.getGrade(loginId);
      session.setAttribute("grade", grade);
      try {
        // 레시피 보여주기
        BoardFreeDTO recipe = bfs.serviceMostViewed();
        request.setAttribute("recipe", recipe);
        // 탑리뷰 보여주기
        request.setAttribute("topReviews", brService.topRcmdReviews());
        // 베스트 상품 보여주기
        List<ProductsDTO> best = pservice.selectBestProductsService();
        session.setAttribute("best", best);
      } catch (Exception e) {
        e.printStackTrace();
        return "error";
      }
      return "home";
    }
  }

  @RequestMapping("/logout")
  public String logout() {
    session.invalidate();
    return "login/homeThrough";
  }

  @RequestMapping("/mypageDelete")
  public String mypageDelete() {
    return "login/mypageDelete";
  }

  @RequestMapping("/delete")
  public String delete() {
    loginserv.delete((String) session.getAttribute("loginId"));
    session.invalidate();
    return "login/homeThrough";
  }

  @RequestMapping("/reLogin")
  public String reLogin() {
    session.invalidate();
    return "login/login";
  }

  @ResponseBody
  @RequestMapping("/pwCheck")
  public boolean pwCheck(String key, String pw) {
    return loginserv.pwDupCheck(key, pw);
  }

  @RequestMapping("/mailSender")
  public String mailSender() throws Exception {
    String m_email = (String) session.getAttribute("loginId");
    loginserv.mailSender(m_email);
    return "login/findAccountAfterLogin";
  }

  @RequestMapping("/findAccountAfterLogin")
  public String findAccountAfterLogin() {
    return "login/findAccountAfterLogin";
  }

  @ResponseBody
  @RequestMapping("/findId")
  public MembersDTO findId(String key) {
    return loginserv.findId(key);
  }

  @ResponseBody
  @RequestMapping("/findPwGetCode")
  public String findPwGetCode(String key) {
    String pwCode = loginserv.findPwGetCode(key);
    return pwCode;
  }

  @ResponseBody
  @RequestMapping("/findPwChange")
  public String findPwChange(String email, String pw) {
    loginserv.updateOne(email, pw);
    return null;
  }

  @RequestMapping("/changeGardenProfile")
  public String changeGardenProfile(MembersDTO dto, MultipartFile ex_file) {
    dto.setM_email((String) session.getAttribute("loginId"));
    loginserv.changeGardenProfile(dto, ex_file);
    return "login/mypageFirstThrough";
  }

  @RequestMapping("/changeGardenName")
  public String changeGardenName(MembersDTO dto) {
    dto.setM_email((String) session.getAttribute("loginId"));
    loginserv.changeGardenName(dto);
    return "login/mypageFirstThrough";
  }

  @ResponseBody
  @RequestMapping("/naverLogin")
  public String naverLogin() {
    return loginserv.naverLogin();
  }

  @RequestMapping("/callbackNaver")
  public String naverCallback() throws Exception {
    String code = loginserv.NaverLoginCallback();
    String socialEmail = loginserv.NaverLoginGetInfo(code);
    boolean result = loginserv.emailDupCheck(socialEmail);
    if (result == true) { //그 외 - 홈으로 이동
      session.setAttribute("loginName", loginserv.getName(socialEmail));
      session.setAttribute("loginId", socialEmail);
      return "login/homeThrough";
    } else { //최초 로그인 - 정보입력 페이지로 이동
      session.setAttribute("loginName", loginserv.getName(socialEmail));
      session.setAttribute("loginId", socialEmail);
      session.setAttribute("social", "naver");
      session.setAttribute("profile", "");
      return "login/socialLoginThrough";
    }
  }

  @RequestMapping("/socialJoin") //모든 소셜 로그인 후 페이지 이동
  public String socialJoin(MembersDTO dto) {
    return "login/socialJoin";
  }

  @RequestMapping("/socialJoinSubmit")
  public String socialJoinSubmit(MembersDTO dto) {
    loginserv.socialJoinSubmit(dto);
    return "login/findAccountAfterLogin";
  }

  @ResponseBody
  @RequestMapping("/kakaoLogin")
  public String kakaoLogin() {
    String url = "https://kauth.kakao.com/oauth/authorize?client_id=5a8617254e6227196ff9c31a66275c78&redirect_uri=http://192.168.60.22/kakaoCallback&response_type=code";
    return url;
  }

  @RequestMapping("/kakaoCallback")
  public String kakaoLoginMakeUrl(String code) {
    Map<String, String> map = loginserv.kakaoLoginMakeUrl(code);
    String socialEmail = map.get("socialEmail");
    String profile = map.get("profile");

    boolean result = loginserv.emailDupCheck(socialEmail);
    if (result == true) { //그 외 - 홈으로 이동
      session.setAttribute("loginName", loginserv.getName(socialEmail));
      session.setAttribute("loginId", socialEmail);
      return "login/homeThrough";
    } else { //최초 로그인 - 정보입력 페이지로 이동
      session.setAttribute("loginName", loginserv.getName(socialEmail));
      session.setAttribute("loginId", socialEmail);
      session.setAttribute("profile", profile);
      session.setAttribute("social", "kakao");
      return "login/socialLoginThrough";
    }
  }

  @ResponseBody
  @RequestMapping(value = "/getShoppedList", produces = "text/html;charset=utf8")
  public String getShoppedList(ShopListDTO dto, SubscribeDTO dto2, Timestamp date) {
    dto.setS_email((String) session.getAttribute("loginId"));
    dto.setS_orderdate(date);
    dto2.setSb_email((String) session.getAttribute("loginId"));
    dto2.setSb_startday(new java.sql.Date(date.getTime()));
    List<ShopListDTO> shop = loginserv.getShoppedList(dto);
    List<SubscribeDTO> sub = loginserv.getShoppedListSub(dto2);
    Object[] arr = new Object[]{sub, shop};
    return new Gson().toJson(arr);
  }

}
