package my.garden.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommentFreeDTO {
  private int cf_bf_no;
  private int cf_no;
  private String cf_name;
  private String cf_email;
  private Timestamp cf_writedate;
  private String cf_comment;
  private String cf_stringdate;
  private String cf_profileImg;

  public CommentFreeDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public CommentFreeDTO(int cf_bf_no, int cf_no, String cf_name, String cf_email, Timestamp cf_writedate,
                        String cf_comment, String cf_stringdate, String cf_profileImg) {
    super();
    this.cf_bf_no = cf_bf_no;
    this.cf_no = cf_no;
    this.cf_name = cf_name;
    this.cf_email = cf_email;
    this.cf_writedate = cf_writedate;
    this.cf_comment = cf_comment;
    this.cf_stringdate = cf_stringdate;
    this.cf_profileImg = cf_profileImg;
  }

  public String getCf_profileImg() {
    return cf_profileImg;
  }

  public void setCf_profileImg(String cf_profileImg) {
    this.cf_profileImg = cf_profileImg;
  }

  public int getCf_bf_no() {
    return cf_bf_no;
  }

  public void setCf_bf_no(int cf_bf_no) {
    this.cf_bf_no = cf_bf_no;
  }

  public int getCf_no() {
    return cf_no;
  }

  public void setCf_no(int cf_no) {
    this.cf_no = cf_no;
  }

  public String getCf_name() {
    return cf_name;
  }

  public void setCf_name(String cf_name) {
    this.cf_name = cf_name;
  }

  public String getCf_email() {
    return cf_email;
  }

  public void setCf_email(String cf_email) {
    this.cf_email = cf_email;
  }

  public Timestamp getCf_writedate() {
    return cf_writedate;
  }

  public void setCf_writedate(Timestamp cf_writedate) {
    this.cf_writedate = cf_writedate;
  }

  public String getCf_comment() {
    return cf_comment.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }

  public void setCf_comment(String cf_comment) {
    this.cf_comment = cf_comment;
  }

  public String getCf_stringdate() {
    return this.formedTime();
  }

  public void setCf_stringdate(String cf_stringdate) {
    this.cf_stringdate = cf_stringdate;
  }

  //time 형식 두개 (메서드)
  public String contentTime() {
    Timestamp date = this.cf_writedate;
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    String writeDate = simpleDate.format(date.getTime());
    return writeDate;
  }

  public String formedTime() {
    long currentTime = System.currentTimeMillis();
    long writeTime = this.cf_writedate.getTime();
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
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm");
      return sdf.format(writeTime);
    }
  }

}
