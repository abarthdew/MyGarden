package my.garden.dto;

import java.security.Timestamp;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class MembersDTO {
  private String m_email;
  private String m_pw;
  private String m_name;
  private String m_phone;
  private String m_zipcode;
  private String m_address1;
  private String m_address2;
  private Timestamp m_joindate;
  private String m_ipaddress;
  private String m_grade;
  private String m_social; // : MG / kakao / naver
  private int m_point;
  private String m_gender;
  private Date m_birth;
  private String m_garden;
  private String m_profile;
  private String m_realpath;

  public MembersDTO() {
    super();
  }

  public MembersDTO(String m_email, String m_pw, String m_name, String m_phone, String m_zipcode, String m_address1,
                    String m_address2, Timestamp m_joindate, String m_ipaddress, String m_grade, String m_social, int m_point,
                    String m_gender, Date m_birth, String m_garden, String m_profile, String m_realpath) {
    super();
    this.m_email = m_email;
    this.m_pw = m_pw;
    this.m_name = m_name;
    this.m_phone = m_phone;
    this.m_zipcode = m_zipcode;
    this.m_address1 = m_address1;
    this.m_address2 = m_address2;
    this.m_joindate = m_joindate;
    this.m_ipaddress = m_ipaddress;
    this.m_grade = m_grade;
    this.m_social = m_social;
    this.m_point = m_point;
    this.m_gender = m_gender;
    this.m_birth = m_birth;
    this.m_garden = m_garden;
    this.m_profile = m_profile;
    this.m_realpath = m_realpath;
  }

  public String getM_email() {
    return m_email;
  }

  public void setM_email(String m_email) {
    this.m_email = m_email;
  }

  public String getM_pw() {
    return m_pw;
  }

  public void setM_pw(String m_pw) {
    this.m_pw = m_pw;
  }

  public String getM_name() {
    return m_name;
  }

  public void setM_name(String m_name) {
    this.m_name = m_name;
  }

  public String getM_phone() {
    return m_phone;
  }

  public void setM_phone(String m_phone) {
    this.m_phone = m_phone;
  }

  public String getM_zipcode() {
    return m_zipcode;
  }

  public void setM_zipcode(String m_zipcode) {
    this.m_zipcode = m_zipcode;
  }

  public String getM_address1() {
    return m_address1;
  }

  public void setM_address1(String m_address1) {
    this.m_address1 = m_address1;
  }

  public String getM_address2() {
    return m_address2;
  }

  public void setM_address2(String m_address2) {
    this.m_address2 = m_address2;
  }

  public Timestamp getM_joindate() {
    return m_joindate;
  }

  public void setM_joindate(Timestamp m_joindate) {
    this.m_joindate = m_joindate;
  }

  public String getM_ipaddress() {
    return m_ipaddress;
  }

  public void setM_ipaddress(String m_ipaddress) {
    this.m_ipaddress = m_ipaddress;
  }

  public String getM_grade() {
    return m_grade;
  }

  public void setM_grade(String m_grade) {
    this.m_grade = m_grade;
  }

  public String getM_social() {
    return m_social;
  }

  public void setM_social(String m_social) {
    this.m_social = m_social;
  }

  public int getM_point() {
    return m_point;
  }

  public void setM_point(int m_point) {
    this.m_point = m_point;
  }

  public String getM_gender() {
    return m_gender;
  }

  public void setM_gender(String m_gender) {
    this.m_gender = m_gender;
  }

  public Date getM_birth() {
    return m_birth;
  }

  public void setM_birth(Date m_birth) {
    this.m_birth = m_birth;
  }

  public String getM_garden() {
    return m_garden;
  }

  public void setM_garden(String m_garden) {
    this.m_garden = m_garden;
  }

  public String getM_profile() {
    return m_profile;
  }

  public void setM_profile(String m_profile) {
    this.m_profile = m_profile;
  }

  public String getM_realpath() {
    return m_realpath;
  }

  public void setM_realpath(String m_realpath) {
    this.m_realpath = m_realpath;
  }
}