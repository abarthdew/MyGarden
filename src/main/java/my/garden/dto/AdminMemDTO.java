package my.garden.dto;

import java.sql.Timestamp;

public class AdminMemDTO {
  private String m_email;
  private String m_name;
  private String m_phone;
  private Timestamp m_joindate;
  private String m_ipaddress;
  private String m_grade;
  private int m_point;
  private String m_gender;
  private String m_garden;

  public AdminMemDTO() {
  }

  public AdminMemDTO(String m_email, String m_name, String m_phone, Timestamp m_joindate, String m_ipaddress,
                     String m_grade, int m_point, String m_gender, String m_garden) {
    super();
    this.m_email = m_email;
    this.m_name = m_name;
    this.m_phone = m_phone;
    this.m_joindate = m_joindate;
    this.m_ipaddress = m_ipaddress;
    this.m_grade = m_grade;
    this.m_point = m_point;
    this.m_gender = m_gender;
    this.m_garden = m_garden;
  }

  public String getM_email() {
    return m_email;
  }

  public void setM_email(String m_email) {
    this.m_email = m_email;
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

  public String getM_garden() {
    return m_garden;
  }

  public void setM_garden(String m_garden) {
    this.m_garden = m_garden;
  }


}
