package my.garden.dto;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

public class ProductsDTO {
  private int p_no;
  private MultipartFile image;
  private String p_imagepath;
  private String p_title;
  private String p_subtitle;
  private String p_category;
  private int p_inventory;
  private String p_unit;
  private String p_seller;
  private String p_origin;
  private int p_price;
  private String p_content;
  private int p_sales;
  private Timestamp p_writedate;

  public ProductsDTO() {
  }

  public ProductsDTO(int p_no, MultipartFile image, String p_imagepath, String p_title,
                     String p_subtitle, String p_category, int p_inventory, String p_unit, String p_seller, String p_origin,
                     int p_price, String p_content, int p_sales, Timestamp p_writedate) {
    super();
    this.p_no = p_no;
    this.image = image;
    this.p_imagepath = p_imagepath;
    this.p_title = p_title;
    this.p_subtitle = p_subtitle;
    this.p_category = p_category;
    this.p_inventory = p_inventory;
    this.p_unit = p_unit;
    this.p_seller = p_seller;
    this.p_origin = p_origin;
    this.p_price = p_price;
    this.p_content = p_content;
    this.p_sales = p_sales;
    this.p_writedate = p_writedate;
  }


  public int getP_no() {
    return p_no;
  }

  public void setP_no(int p_no) {
    this.p_no = p_no;
  }

  public MultipartFile getImage() {
    return image;
  }

  public void setImage(MultipartFile image) {
    this.image = image;
  }

  public String getP_imagepath() {
    return p_imagepath;
  }

  public void setP_imagepath(String p_imagepath) {
    this.p_imagepath = p_imagepath;
  }

  public String getP_title() {
    return p_title;
  }

  public void setP_title(String p_title) {
    this.p_title = p_title;
  }

  public String getP_subtitle() {
    return p_subtitle;
  }

  public void setP_subtitle(String p_subtitle) {
    this.p_subtitle = p_subtitle;
  }

  public String getP_category() {
    return p_category;
  }

  public void setP_category(String p_category) {
    this.p_category = p_category;
  }

  public int getP_inventory() {
    return p_inventory;
  }

  public void setP_inventory(int p_inventory) {
    this.p_inventory = p_inventory;
  }

  public String getP_unit() {
    return p_unit;
  }

  public void setP_unit(String p_unit) {
    this.p_unit = p_unit;
  }

  public String getP_seller() {
    return p_seller;
  }

  public void setP_seller(String p_seller) {
    this.p_seller = p_seller;
  }

  public String getP_origin() {
    return p_origin;
  }

  public void setP_origin(String p_origin) {
    this.p_origin = p_origin;
  }

  public int getP_price() {
    return p_price;
  }

  public void setP_price(int p_price) {
    this.p_price = p_price;
  }

  public String getP_content() {
    return p_content;
  }

  public void setP_content(String p_content) {
    this.p_content = p_content;
  }

  public int getP_sales() {
    return p_sales;
  }

  public void setP_sales(int p_sales) {
    this.p_sales = p_sales;
  }

  public Timestamp getP_writedate() {
    return p_writedate;
  }

  public void setP_writedate(Timestamp p_writedate) {
    this.p_writedate = p_writedate;
  }
}
