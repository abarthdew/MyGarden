package my.garden.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BoardQnADTO {
  private int bq_p_no;
  private int bq_no;
  private String bq_checkedAns;
  private String bq_checkedSecret;
  private String bq_title;
  private String bq_email;
  private String bq_name;
  private String bq_content;
  private Timestamp bq_writedate;
  private String bq_imagepath1;
  private String bq_imagepath2;
  private String bq_imagepath3;


  public BoardQnADTO() {
    super();
  }


  public int getBq_p_no() {
    return bq_p_no;
  }


  public void setBq_p_no(int bq_p_no) {
    this.bq_p_no = bq_p_no;
  }


  public int getBq_no() {
    return bq_no;
  }


  public void setBq_no(int bq_no) {
    this.bq_no = bq_no;
  }


  public String getBq_checkedAns() {
    return bq_checkedAns;
  }


  public void setBq_checkedAns(String bq_checkedAns) {
    this.bq_checkedAns = bq_checkedAns;
  }


  public String getBq_checkedSecret() {
    return bq_checkedSecret;
  }


  public void setBq_checkedSecret(String bq_checkedSecret) {
    this.bq_checkedSecret = bq_checkedSecret;
  }


  public String getBq_title() {
    return bq_title.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }


  public void setBq_title(String bq_title) {
    this.bq_title = bq_title;
  }


  public String getBq_email() {
    return bq_email;
  }


  public void setBq_email(String bq_email) {
    this.bq_email = bq_email;
  }


  public String getBq_name() {
    return bq_name;
  }


  public void setBq_name(String bq_name) {
    this.bq_name = bq_name;
  }


  public String getBq_content() {
    return bq_content.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }


  public void setBq_content(String bq_content) {
    this.bq_content = bq_content;
  }


  public String getBq_writedate() {
    return this.formedTime();
  }

  public Timestamp getBq_date() {
    return this.bq_writedate;
  }

  public void setBq_writedate(Timestamp bq_writedate) {
    this.bq_writedate = bq_writedate;
  }


  public String getBq_imagepath1() {
    return bq_imagepath1;
  }


  public void setBq_imagepath1(String bq_imagepath1) {
    this.bq_imagepath1 = bq_imagepath1;
  }


  public String getBq_imagepath2() {
    return bq_imagepath2;
  }


  public void setBq_imagepath2(String bq_imagepath2) {
    this.bq_imagepath2 = bq_imagepath2;
  }


  public String getBq_imagepath3() {
    return bq_imagepath3;
  }


  public void setBq_imagepath3(String bq_imagepath3) {
    this.bq_imagepath3 = bq_imagepath3;
  }


  public BoardQnADTO(int bq_p_no, int bq_no, String bq_checkedAns, String bq_checkedSecret, String bq_title,
                     String bq_email, String bq_name, String bq_content, Timestamp bq_writedate, String bq_imagepath1,
                     String bq_imagepath2, String bq_imagepath3) {
    this.bq_p_no = bq_p_no;
    this.bq_no = bq_no;
    this.bq_checkedAns = bq_checkedAns;
    this.bq_checkedSecret = bq_checkedSecret;
    this.bq_title = bq_title;
    this.bq_email = bq_email;
    this.bq_name = bq_name;
    this.bq_content = bq_content;
    this.bq_writedate = bq_writedate;
    this.bq_imagepath1 = bq_imagepath1;
    this.bq_imagepath2 = bq_imagepath2;
    this.bq_imagepath3 = bq_imagepath3;
  }

  //time 형식 두개 (메서드)
  public String contentTime() {
    Timestamp date = this.bq_writedate;
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd");
    String writeDate = simpleDate.format(date.getTime());
    return writeDate;
  }

  public String formedTime() {
    long currentTime = System.currentTimeMillis();
    long writeTime = this.bq_writedate.getTime();
    if (currentTime - writeTime < (1000 * 60)) {
      long time = currentTime - writeTime;
      return time / 1000 + "초 전";
    } else if (currentTime - writeTime < (1000 * 60 * 60)) {
      long time = currentTime - writeTime;
      return time / 1000 / 60 + "분 전";
    } else if (currentTime - writeTime < (1000 * 60 * 60 * 24)) {
      long time = currentTime - writeTime;
      return time / 1000 / 60 / 60 + "시간 전";
    } else {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
      return sdf.format(writeTime);
    }
  }

}
