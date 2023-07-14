package my.garden.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

@Component
public class BoardFreeDTO {
  private int bf_no;
  private String bf_title;
  private String bf_writer;
  private String bf_email;
  private String bf_content;
  private String bf_thumbnail;
  private int bf_viewcount;
  private Timestamp bf_writedate;
  private int bf_recommend;
  private String bf_stringdate;
  private int bf_cmtcount;
  private String bf_writerImg;

  public BoardFreeDTO() {
    super();
    // TODO Auto-generated constructor stub
  }

  public BoardFreeDTO(int bf_no, String bf_title, String bf_writer, String bf_email, String bf_content,
                      String bf_thumbnail, int bf_viewcount, Timestamp bf_writedate, int bf_recommend, String bf_stringdate,
                      int bf_cmtcount, String bf_writerImg) {
    super();
    this.bf_no = bf_no;
    this.bf_title = bf_title;
    this.bf_writer = bf_writer;
    this.bf_email = bf_email;
    this.bf_content = bf_content;
    this.bf_thumbnail = bf_thumbnail;
    this.bf_viewcount = bf_viewcount;
    this.bf_writedate = bf_writedate;
    this.bf_recommend = bf_recommend;
    this.bf_stringdate = bf_stringdate;
    this.bf_cmtcount = bf_cmtcount;
    this.bf_writerImg = bf_writerImg;
  }

  public int getBf_no() {
    return bf_no;
  }

  public void setBf_no(int bf_no) {
    this.bf_no = bf_no;
  }

  public String getBf_title() {
    return bf_title.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }

  public void setBf_title(String bf_title) {
    this.bf_title = bf_title;
  }

  public String getBf_writer() {
    return bf_writer;
  }

  public void setBf_writer(String bf_writer) {
    this.bf_writer = bf_writer;
  }

  public String getBf_email() {
    return bf_email;
  }

  public void setBf_email(String bf_email) {
    this.bf_email = bf_email;
  }

  public String getBf_content() {
    return bf_content.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }

  public void setBf_content(String bf_content) {
    this.bf_content = bf_content;
  }

  public String getBf_thumbnail() {
    return bf_thumbnail;
  }

  public void setBf_thumbnail(String bf_thumbnail) {
    this.bf_thumbnail = bf_thumbnail;
  }

  public int getBf_viewcount() {
    return bf_viewcount;
  }

  public void setBf_viewcount(int bf_viewcount) {
    this.bf_viewcount = bf_viewcount;
  }

  public Timestamp getBf_writedate() {
    return bf_writedate;
  }

  public void setBf_writedate(Timestamp bf_writedate) {
    this.bf_writedate = bf_writedate;
  }

  public int getBf_recommend() {
    return bf_recommend;
  }

  public void setBf_recommend(int bf_recommend) {
    this.bf_recommend = bf_recommend;
  }

  public String getBf_stringdate() {
    return this.formedTime();
  }

  public void setBf_stringdate(String bf_stringdate) {
    this.bf_stringdate = bf_stringdate;
  }

  public int getBf_cmtcount() {
    return bf_cmtcount;
  }

  public void setBf_cmtcount(int bf_cmtcount) {
    this.bf_cmtcount = bf_cmtcount;
  }

  public String getBf_writerImg() {
    return bf_writerImg;
  }

  public void setBf_writerImg(String bf_writerImg) {
    this.bf_writerImg = bf_writerImg;
  }

  //time 형식 두개 (메서드)
  public String contentTime() {
    Timestamp date = this.bf_writedate;
    SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy/MM/dd hh:mm");
    String writeDate = simpleDate.format(date.getTime());
    return writeDate;
  }

  public String formedTime() {
    long currentTime = System.currentTimeMillis();
    long writeTime = this.bf_writedate.getTime();
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
