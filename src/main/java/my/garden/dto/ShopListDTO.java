package my.garden.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class ShopListDTO {
  private int s_orderno_seq;
  private Long s_orderno;
  private String s_email;
  private String s_phone;
  private int s_p_no;
  private String s_p_imagepath;
  private String s_p_title;
  private int s_p_count;
  private int s_p_price;
  private String s_m_recipient;
  private String s_m_memo;
  private String s_m_paymethod;
  private String s_m_zipcode;
  private String s_m_address1;
  private String s_m_address2;
  private String s_statement;
  private Timestamp s_orderdate;
  private String s_reviewok;

  public ShopListDTO() {
  }

  public ShopListDTO(int s_orderno_seq, Long s_orderno, String s_email, String s_phone, int s_p_no,
                     String s_p_imagepath, String s_p_title, int s_p_count, int s_p_price, String s_m_recipient, String s_m_memo,
                     String s_m_paymethod, String s_m_zipcode, String s_m_address1, String s_m_address2, String s_statement,
                     Timestamp s_orderdate, String s_reviewok) {
    this.s_orderno_seq = s_orderno_seq;
    this.s_orderno = s_orderno;
    this.s_email = s_email;
    this.s_phone = s_phone;
    this.s_p_no = s_p_no;
    this.s_p_imagepath = s_p_imagepath;
    this.s_p_title = s_p_title;
    this.s_p_count = s_p_count;
    this.s_p_price = s_p_price;
    this.s_m_recipient = s_m_recipient;
    this.s_m_memo = s_m_memo;
    this.s_m_paymethod = s_m_paymethod;
    this.s_m_zipcode = s_m_zipcode;
    this.s_m_address1 = s_m_address1;
    this.s_m_address2 = s_m_address2;
    this.s_statement = s_statement;
    this.s_orderdate = s_orderdate;
    this.s_reviewok = s_reviewok;
  }

  public int getS_orderno_seq() {
    return s_orderno_seq;
  }

  public void setS_orderno_seq(int s_orderno_seq) {
    this.s_orderno_seq = s_orderno_seq;
  }

  public Long getS_orderno() {
    return s_orderno;
  }

  public void setS_orderno(Long s_orderno) {
    this.s_orderno = s_orderno;
  }

  public String getS_email() {
    return s_email;
  }

  public void setS_email(String s_email) {
    this.s_email = s_email;
  }

  public String getS_phone() {
    return s_phone;
  }

  public void setS_phone(String s_phone) {
    this.s_phone = s_phone;
  }

  public int getS_p_no() {
    return s_p_no;
  }

  public void setS_p_no(int s_p_no) {
    this.s_p_no = s_p_no;
  }

  public String getS_p_imagepath() {
    return s_p_imagepath;
  }

  public void setS_p_imagepath(String s_p_imagepath) {
    this.s_p_imagepath = s_p_imagepath;
  }

  public String getS_p_title() {
    return s_p_title;
  }

  public void setS_p_title(String s_p_title) {
    this.s_p_title = s_p_title;
  }

  public int getS_p_count() {
    return s_p_count;
  }

  public void setS_p_count(int s_p_count) {
    this.s_p_count = s_p_count;
  }

  public int getS_p_price() {
    return s_p_price;
  }

  public void setS_p_price(int s_p_price) {
    this.s_p_price = s_p_price;
  }

  public String getS_m_recipient() {
    return s_m_recipient;
  }

  public void setS_m_recipient(String s_m_recipient) {
    this.s_m_recipient = s_m_recipient;
  }

  public String getS_m_memo() {
    return s_m_memo;
  }

  public void setS_m_memo(String s_m_memo) {
    this.s_m_memo = s_m_memo;
  }

  public String getS_m_paymethod() {
    return s_m_paymethod;
  }

  public void setS_m_paymethod(String s_m_paymethod) {
    this.s_m_paymethod = s_m_paymethod;
  }

  public String getS_m_zipcode() {
    return s_m_zipcode;
  }

  public void setS_m_zipcode(String s_m_zipcode) {
    this.s_m_zipcode = s_m_zipcode;
  }

  public String getS_m_address1() {
    return s_m_address1;
  }

  public void setS_m_address1(String s_m_address1) {
    this.s_m_address1 = s_m_address1;
  }

  public String getS_m_address2() {
    return s_m_address2;
  }

  public void setS_m_address2(String s_m_address2) {
    this.s_m_address2 = s_m_address2;
  }

  public String getS_statement() {
    return s_statement;
  }

  public void setS_statement(String s_statement) {
    this.s_statement = s_statement;
  }

  public String getS_orderdate() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    return sdf.format(s_orderdate);
  }

  public void setS_orderdate(Timestamp s_orderdate) {
    this.s_orderdate = s_orderdate;
  }

  public String getS_reviewok() {
    return s_reviewok;
  }

  public void setS_reviewok(String s_reviewok) {
    this.s_reviewok = s_reviewok;
  }


}
