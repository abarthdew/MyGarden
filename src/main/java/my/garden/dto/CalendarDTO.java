package my.garden.dto;

import java.sql.Timestamp;

public class CalendarDTO {
  private Timestamp orderdate;
  private int count;

  private String date;
  private String color;

  public CalendarDTO(String date, String color) {
    super();
    this.date = date;
    this.color = color;
  }

  public CalendarDTO(Timestamp orderdate, int count) {
    super();
    this.orderdate = orderdate;
    this.count = count;
  }

  public CalendarDTO() {
    super();
  }

  public Timestamp getOrderdate() {
    return orderdate;
  }

  public void setOrderdate(Timestamp orderdate) {
    this.orderdate = orderdate;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

}
