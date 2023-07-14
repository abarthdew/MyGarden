package my.garden.dto;

import java.sql.Timestamp;

public class ChatDTO {

  private String c_sendemail;
  private String c_message;
  private String c_recieve_email;
  private String c_grade;
  private Timestamp c_querydate;
  private String c_answer;

  public ChatDTO() {
  }

  public ChatDTO(String c_sendemail, String c_message, String c_recieve_email, String c_grade, Timestamp c_querydate,
                 String c_answer) {
    super();
    this.c_sendemail = c_sendemail;
    this.c_message = c_message;
    this.c_recieve_email = c_recieve_email;
    this.c_grade = c_grade;
    this.c_querydate = c_querydate;
    this.c_answer = c_answer;
  }

  public String getC_sendemail() {
    return c_sendemail;
  }

  public void setC_sendemail(String c_sendemail) {
    this.c_sendemail = c_sendemail;
  }

  public String getC_message() {
    return c_message;
  }

  public void setC_message(String c_message) {
    this.c_message = c_message;
  }

  public String getC_recieve_email() {
    return c_recieve_email;
  }

  public void setC_recieve_email(String c_recieve_email) {
    this.c_recieve_email = c_recieve_email;
  }

  public String getC_grade() {
    return c_grade;
  }

  public void setC_grade(String c_grade) {
    this.c_grade = c_grade;
  }

  public Timestamp getC_querydate() {
    return c_querydate;
  }

  public void setC_querydate(Timestamp c_querydate) {
    this.c_querydate = c_querydate;
  }

  public String getC_answer() {
    return c_answer;
  }

  public void setC_answer(String c_answer) {
    this.c_answer = c_answer;
  }

}
