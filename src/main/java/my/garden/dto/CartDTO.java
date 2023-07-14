package my.garden.dto;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class CartDTO {
  private int c_p_no;
  private String c_p_imagepath;
  private String c_p_title;
  private int c_p_price;
  private String c_m_email;
  private int c_count;
  private Timestamp c_selectdate;

  public CartDTO() {
    super();
  }

  public CartDTO(String c_p_imagePath, String c_p_title, int c_p_price, int c_count) {
    super();
    this.c_p_imagepath = c_p_imagePath;
    this.c_p_title = c_p_title;
    this.c_p_price = c_p_price;
    this.c_count = c_count;
  }

  public CartDTO(int c_p_no, String c_p_imagepath, String c_p_title, int c_p_price, String c_m_email, int c_count,
                 Timestamp c_selectdate) {
    super();
    this.c_p_no = c_p_no;
    this.c_p_imagepath = c_p_imagepath;
    this.c_p_title = c_p_title;
    this.c_p_price = c_p_price;
    this.c_m_email = c_m_email;
    this.c_count = c_count;
    this.c_selectdate = c_selectdate;
  }

  public int getC_p_no() {
    return c_p_no;
  }

  public void setC_p_no(int c_p_no) {
    this.c_p_no = c_p_no;
  }

  public String getC_p_imagepath() {
    return c_p_imagepath;
  }

  public void setC_p_imagepath(String c_p_imagepath) {
    this.c_p_imagepath = c_p_imagepath;
  }

  public String getC_p_title() {
    return c_p_title;
  }

  public void setC_p_title(String c_p_title) {
    this.c_p_title = c_p_title;
  }

  public int getC_p_price() {
    return c_p_price;
  }

  public void setC_p_price(int c_p_price) {
    this.c_p_price = c_p_price;
  }

  public String getC_m_email() {
    return c_m_email;
  }

  public void setC_m_email(String c_m_email) {
    this.c_m_email = c_m_email;
  }

  public int getC_count() {
    return c_count;
  }

  public void setC_count(int c_count) {
    this.c_count = c_count;
  }

  public Timestamp getC_selectdate() {
    return c_selectdate;
  }

  public void setC_selectdate(Timestamp c_selectdate) {
    this.c_selectdate = c_selectdate;
  }


}
