package review;

import java.sql.Date;

public class ReviewVo {
   private int r_id;
   private String id;
   private String name;
   private Date r_date;
   private String content;
   private int score;

   public ReviewVo() {

   }

   public ReviewVo(int r_id, String id, String name, Date r_date, String content, int score) {
      super();
      this.r_id = r_id;
      this.id = id;
      this.name = name;
      this.r_date = r_date;
      this.content = content;
      this.score = score;
   }

   public int getR_id() {
      return r_id;
   }

   public void setR_id(int r_id) {
      this.r_id = r_id;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Date getR_date() {
      return r_date;
   }

   public void setR_date(Date r_date) {
      this.r_date = r_date;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public int getScore() {
      return score;
   }

   public void setScore(int score) {
      this.score = score;
   }

   @Override
   public String toString() {
		return "리뷰번호: " + r_id + "|작성자: " + id + "|공연명: " + name + "|작성일: " + r_date + "|코멘트: "
				+ content + "|평점: " + score;
	}

}