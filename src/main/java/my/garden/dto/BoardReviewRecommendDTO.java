package my.garden.dto;

public class BoardReviewRecommendDTO {
  private String br_email;
  private int br_no;
  private String br_title;


  public BoardReviewRecommendDTO() {
  }

  public BoardReviewRecommendDTO(String br_email, int br_no, String br_title) {
    super();
    this.br_email = br_email;
    this.br_no = br_no;
    this.br_title = br_title;
  }

  public String getBr_email() {
    return br_email;
  }

  public void setBr_email(String br_email) {
    this.br_email = br_email;
  }

  public int getBr_no() {
    return br_no;
  }

  public void setBr_no(int br_no) {
    this.br_no = br_no;
  }

  public String getBr_title() {
    return br_title;
  }

  public void setBr_title(String br_title) {
    this.br_title = br_title;
  }


}
