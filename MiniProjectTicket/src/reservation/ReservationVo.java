package reservation;

import java.sql.Date;

import member.MemberVo;
import performance.PerformanceVo;

public class ReservationVo {
   private int r_num;
   //private String p_num;
   private PerformanceVo p_num;
   //private String id;
   private MemberVo id;
   private Date r_date;
   
   public ReservationVo() {}

   public ReservationVo(int r_num, PerformanceVo p_num, MemberVo id, Date r_date) {
      super();
      this.r_num = r_num;
      this.p_num = p_num;
      this.id = id;
      this.r_date = r_date;
   }

   public int getR_num() {
      return r_num;
   }

   public void setR_num(int r_num) {
      this.r_num = r_num;
   }

   public PerformanceVo getP_num() {
      return p_num;
   }

   public void setP_num(PerformanceVo p_num) {
      this.p_num = p_num;
   }

   public MemberVo getId() {
      return id;
   }

   public void setId(MemberVo id) {
      this.id = id;
   }

   public Date getR_date() {
      return r_date;
   }

   public void setR_date(Date r_date) {
      this.r_date = r_date;
   }

   @Override
   public String toString() {
      return "ReservationVo [r_num=" + r_num + ", p_num=" + p_num + ", id=" + id + ", r_date=" + r_date + "]";
   }
   
}
   