package my.garden.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import my.garden.dao.LoginDAO;
import my.garden.dto.CalendarDTO;
import my.garden.dto.MembersDTO;
import my.garden.dto.PrivateGardenDTO;
import my.garden.dto.ShopListDTO;
import my.garden.dto.SubscribeDTO;

@Service
public class LoginServiceImpl {

  @Autowired
  LoginDAO logDao;
  @Autowired
  HttpServletRequest request;

  public int joinSubmit(MembersDTO dto, MultipartFile ex_file) {
    logDao.profile(dto, ex_file);
    return logDao.joinSubmit(dto);
  }

  public boolean emailDupCheck(String key) {
    if (logDao.emailDupCheck(key) == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean phoneDupCheck(String key) {
    if (logDao.phoneDupCheck(key) == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean gardenDupCheck(String key) {
    if (logDao.gardenDupCheck(key) == null) {
      return false;
    } else {
      return true;
    }
  }

  public String isLoginOk(String loginId, String loginPw) {
    String shaPw = logDao.SHA256(loginPw);
    return logDao.isLoginOk(loginId, shaPw);
  }

  public String getName(String loginId) {
    return logDao.getName(loginId);
  }

  public MembersDTO memSelectAll(MembersDTO dto, String id) {
    return logDao.memSelectAll(dto, id);
  }

  public MembersDTO memSelectAll(String email) {
    return logDao.memSelectAll(email);
  }

  public boolean pwDupCheck(String key, String pw) {
    String result = logDao.pwDupCheck(key).toString();
    String pastPw = logDao.SHA256(pw).toString();
    if (result.equals(pastPw)) {
      return true;
    } else {
      return false;
    }
  }

  public int delete(String loginId) {
    return logDao.delete(loginId);
  }

  public int memUpdateAll(MembersDTO dto) {
    if (dto.getM_social().equals("MG")) {
      dto.setM_pw(logDao.SHA256(dto.getM_pw()));
    } else {
      dto.setM_pw(logDao.SHA256(logDao.randomCode()));
    }
    return logDao.memUpdateAll(dto);
  }

  public int mailSender(String m_email) throws Exception {
    String randomCode = logDao.mailSender(m_email);
    Map<String, String> map = new HashMap();
    map.put("col", "m_pw");
    map.put("colVal", logDao.SHA256(randomCode));
    map.put("whereCol", "m_email");
    map.put("value", m_email);
    return logDao.findAccountChange(map);
  }

  public MembersDTO findId(String key) {
    return logDao.findId(key);
  }

  public String findPwGetCode(String key) {
    try {
      return logDao.mailSender(key);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public int updateOne(String m_email, String m_pw) {
    Map<String, String> map = new HashMap();
    map.put("col", "m_pw");
    map.put("colVal", logDao.SHA256(m_pw));
    map.put("whereCol", "m_email");
    map.put("value", m_email);
    return logDao.findAccountChange(map);
  }

  public int changeGardenProfile(MembersDTO dto, MultipartFile ex_file) {
    logDao.profile(dto, ex_file);
    Map<String, String> map = new HashMap();
    map.put("col", "m_profile");
    map.put("colVal", dto.getM_profile());
    map.put("whereCol", "m_email");
    map.put("value", dto.getM_email());
    return logDao.changeGardenStuff(map);
  }

  public int changeGardenName(MembersDTO dto) {
    Map<String, String> map = new HashMap();
    map.put("col", "m_garden");
    map.put("colVal", dto.getM_garden());
    map.put("whereCol", "m_email");
    map.put("value", dto.getM_email());
    return logDao.changeGardenStuff(map);
  }

  public String naverLogin() {
    return logDao.NaverLoginMakeUrl();
  }

  public String NaverLoginCallback() throws Exception {
    return logDao.NaverLoginCallback();
  }

  public String NaverLoginGetInfo(String code) {
    String socialEmail = logDao.NaverLoginGetInfo(code) + "(naver)";
    return socialEmail;
  }

  public int socialJoinSubmit(MembersDTO dto) {
    dto.setM_social(request.getSession().getAttribute("social").toString());
    dto.setM_ipaddress(request.getRemoteAddr());
    if (dto.getM_profile() == null) {
      dto.setM_profile("resources/img/profile.png");
    } else {
      dto.setM_profile(dto.getM_profile().toString());
    }
    return logDao.socialJoinSubmit(dto);
  }

  public Map<String, String> kakaoLoginMakeUrl(String code) {
    JsonNode jsonToken = logDao.kakaoLoginMakeUrl(code);
    JsonNode accessToken = jsonToken.get("access_token");
    JsonNode userInfo = logDao.kakaoLoginGetInfo(accessToken);

    // Get id
    String id = userInfo.path("id").asText();
    String name = null;
    String profile = null;
    String socialEmail = null;
    JsonNode properties = userInfo.path("properties");
    JsonNode kakao_account = userInfo.path("kakao_account");

    name = properties.path("nickname").asText();
    profile = properties.path("profile_image").asText();
    socialEmail = kakao_account.path("email").asText();

    Map<String, String> map = new HashMap();
    map.put("socialEmail", id + "(kakao)");
    map.put("profile", profile);
    return map;
  }

  public Integer[] getCalender(int year) {
    return logDao.gardenCalendar(year);
  }

  public List<CalendarDTO> calendarList(String loginId) {
    List<CalendarDTO> lists = logDao.getCalendarList(loginId);
    List<CalendarDTO> li = new ArrayList();
    for (CalendarDTO list : lists) {
      String date = list.getOrderdate().toString().substring(0, 10);
      int cnt = list.getCount();
      String color = "";
      if (cnt == 1) {
        color = "#a7cdc3";
      } else if (cnt == 2) {
        color = "#4f9c87";
      } else if (cnt == 3) {
        color = "#3e7a6a";
      } else {
        color = "#35695b";
      }
      CalendarDTO rst = new CalendarDTO(date, color);
      li.add(rst);
    }
    return li;
  }

  public List<ShopListDTO> getShoppedList(ShopListDTO dto) {
    return logDao.getShoppedList(dto);
  }

  public List<SubscribeDTO> getShoppedListSub(SubscribeDTO dto) {
    return logDao.getShoppedListSub(dto);
  }

  public SubscribeDTO selectSub(String id) {
    return logDao.selectSub(id);
  }

  public String getGrade(String id) {
    return logDao.getGrade(id);
  }

  public PrivateGardenDTO getPrivate(String g_email) {
    return logDao.getPrivate(g_email);
  }

}