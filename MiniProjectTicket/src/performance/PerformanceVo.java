package performance;

public class PerformanceVo {
   private String id;
   private String h_id;
   private int price;
   private String name;
   private String category;
   private String p_date;
   private int seat;
   
   public PerformanceVo() {}
   public PerformanceVo(String id, String h_id, int price, String name, String category, String p_date, int seat) {      
      this.id = id;
      this.h_id = h_id;
      this.price = price;
      this.name = name;
      this.category = category;
      this.p_date = p_date;
      this.seat = seat;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getH_id() {
      return h_id;
   }
   public void setH_id(String h_id) {
      this.h_id = h_id;
   }
   public int getPrice() {
      return price;
   }
   public void setPrice(int price) {
      this.price = price;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getCategory() {
      return category;
   }
   public void setCategory(String category) {
      this.category = category;
   }
   public String getP_date() {
      return p_date;
   }
   public void setP_date(String p_date) {
      this.p_date = p_date;
   }
   public int getSeat() {
      return seat;
   }
   public void setSeat(int seat) {
      this.seat = seat;
   }
   @Override
   public String toString() {
      return " 공연코드: " + id + " |공연장코드: " + h_id + " |가격: " + price + " |공연명: " + name + " |공연종류: "
            + category + " |공연날짜: " + p_date + " >좌석 수: " + seat + " ";
   }
   

}