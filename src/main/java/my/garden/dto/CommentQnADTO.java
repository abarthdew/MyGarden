package my.garden.dto;

import java.sql.Timestamp;

public class CommentQnADTO {
  private int cq_p_no; //상품글번호
  private int cq_no; //문의글번호
  private String cq_name;
  private String cq_email;
  private Timestamp cq_writedate;
  private String cq_comment;


  public CommentQnADTO() {
  }

  public CommentQnADTO(int cq_p_no, int cq_no, String cq_name, String cq_email, Timestamp cq_writedate,
                       String cq_comment) {
    super();
    this.cq_p_no = cq_p_no;
    this.cq_no = cq_no;
    this.cq_name = cq_name;
    this.cq_email = cq_email;
    this.cq_writedate = cq_writedate;
    this.cq_comment = cq_comment;
  }

  public int getCq_p_no() {
    return cq_p_no;
  }

  public void setCq_p_no(int cq_p_no) {
    this.cq_p_no = cq_p_no;
  }

  public int getCq_no() {
    return cq_no;
  }

  public void setCq_no(int cq_no) {
    this.cq_no = cq_no;
  }

  public String getCq_name() {
    return cq_name;
  }

  public void setCq_name(String cq_name) {
    this.cq_name = cq_name;
  }

  public String getCq_email() {
    return cq_email;
  }

  public void setCq_email(String cq_email) {
    this.cq_email = cq_email;
  }

  public Timestamp getCq_writedate() {
    return cq_writedate;
  }

  public void setCq_writedate(Timestamp cq_writedate) {
    this.cq_writedate = cq_writedate;
  }

  public String getCq_comment() {
    return cq_comment.replaceAll("(?i)<(/?script[^>]*)>", "&lt;$1&gt;");
  }

  public void setCq_comment(String cq_comment) {
    this.cq_comment = cq_comment;
  }


}
