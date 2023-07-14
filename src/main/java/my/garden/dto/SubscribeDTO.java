package my.garden.dto;

import java.sql.Date;

public class SubscribeDTO {
  private int sb_orderno_seq;
  private String sb_email;
  private String sb_period;
  private String sb_category;
  private String sb_component1;
  private String sb_component2;
  private String sb_component3;
  private String sb_paymethod;
  private Date sb_startday;
  private Date sb_orderday;
  private String sb_statement;
  private int sb_price;

  public SubscribeDTO() {
    super();
  }

  public SubscribeDTO(int sb_orderno_seq, String sb_email, String sb_period, String sb_category, String sb_component1,
                      String sb_component2, String sb_component3, String sb_paymethod, Date sb_startday, Date sb_orderday,
                      String sb_statement, int sb_price) {
    super();
    this.sb_orderno_seq = sb_orderno_seq;
    this.sb_email = sb_email;
    this.sb_period = sb_period;
    this.sb_category = sb_category;
    this.sb_component1 = sb_component1;
    this.sb_component2 = sb_component2;
    this.sb_component3 = sb_component3;
    this.sb_paymethod = sb_paymethod;
    this.sb_startday = sb_startday;
    this.sb_orderday = sb_orderday;
    this.sb_statement = sb_statement;
    this.sb_price = sb_price;
  }

  public int getSb_orderno_seq() {
    return sb_orderno_seq;
  }

  public void setSb_orderno_seq(int sb_orderno_seq) {
    this.sb_orderno_seq = sb_orderno_seq;
  }

  public String getSb_email() {
    return sb_email;
  }

  public void setSb_email(String sb_email) {
    this.sb_email = sb_email;
  }

  public String getSb_period() {
    return sb_period;
  }

  public void setSb_period(String sb_period) {
    this.sb_period = sb_period;
  }

  public String getSb_category() {
    return sb_category;
  }

  public void setSb_category(String sb_category) {
    this.sb_category = sb_category;
  }

  public String getSb_component1() {
    return sb_component1;
  }

  public void setSb_component1(String sb_component1) {
    this.sb_component1 = sb_component1;
  }

  public String getSb_component2() {
    return sb_component2;
  }

  public void setSb_component2(String sb_component2) {
    this.sb_component2 = sb_component2;
  }

  public String getSb_component3() {
    return sb_component3;
  }

  public void setSb_component3(String sb_component3) {
    this.sb_component3 = sb_component3;
  }

  public String getSb_paymethod() {
    return sb_paymethod;
  }

  public void setSb_paymethod(String sb_paymethod) {
    this.sb_paymethod = sb_paymethod;
  }

  public Date getSb_startday() {
    return sb_startday;
  }

  public void setSb_startday(Date sb_startday) {
    this.sb_startday = sb_startday;
  }

  public Date getSb_orderday() {
    return sb_orderday;
  }

  public void setSb_orderday(Date sb_orderday) {
    this.sb_orderday = sb_orderday;
  }

  public String getSb_statement() {
    return sb_statement;
  }

  public void setSb_statement(String sb_statement) {
    this.sb_statement = sb_statement;
  }

  public int getSb_price() {
    return sb_price;
  }

  public void setSb_price(int sb_price) {
    this.sb_price = sb_price;
  }


}
