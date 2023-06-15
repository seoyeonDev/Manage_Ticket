package reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;
import member.MemberVo;
import performance.PerformanceVo;

public class ReservationDao {
   private DBConnect dbconn;
   
   public ReservationDao() {
      dbconn = DBConnect.getInstance();
   }
   
   public ReservationVo selectByNum(int n) {//예매 번호로 예매 내역 확인
      Connection conn = dbconn.conn();
      String sql = "select p.category, p.id, r.r_num, m.id, m.phone, p.name, p.price, p.p_date from T_member m join reservation r on m.id = r.id join performance p on p.id=r.p_num where r.r_num=?";
      ReservationVo vo = null;
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, n);
         ResultSet rs = pstmt.executeQuery();
         if(rs.next()) {
            //public PerformanceVo(String id, String h_id, int price, String name, String category, Date p_date)
        	 PerformanceVo v1 = new PerformanceVo(rs.getString(2), null, rs.getInt(7), rs.getString(6), rs.getString(1), rs.getString(8),0);
             MemberVo v2 = new MemberVo(rs.getString(4), null, null, rs.getString(5));
             vo = (new ReservationVo(rs.getInt(3), v1, v2, null));
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return vo;
   }
   
   public ArrayList<ReservationVo> selectAll(String id){
	   ArrayList<ReservationVo> list = new ArrayList<>();
	   Connection conn = dbconn.conn();
	   String sql = "select p.category, p.id, r.r_num, m.id, m.phone, p.name, p.price, p.p_date from T_member m join reservation r on m.id = r.id join performance p on p.id=r.p_num where m.id=?";
	   try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			PerformanceVo v1 = new PerformanceVo(rs.getString(2), null, rs.getInt(7), rs.getString(6), rs.getString(1), rs.getString(8),0);
            MemberVo v2 = new MemberVo(rs.getString(4), null, null, rs.getString(5));
            list.add(new ReservationVo(rs.getInt(3), v1, v2, null));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   return list;
   }
   
   public ArrayList<ReservationVo> selectById(String id){// 회원id로 예매내역확인
      Connection conn = dbconn.conn();
      ArrayList<ReservationVo> list = new ArrayList<>();
      String sql = "select p.category, p.id, r.r_num, m.id, m.phone, p.name, p.price, p.p_date from T_member m join reservation r on m.id = r.id join performance p on p.id=r.p_num where m.id=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {
            PerformanceVo v1 = new PerformanceVo(rs.getString(2), null, rs.getInt(7), rs.getString(6), rs.getString(1), rs.getString(8),0);
            MemberVo v2 = new MemberVo(rs.getString(4), null, null, rs.getString(5));
            list.add(new ReservationVo(rs.getInt(3), v1, v2, null));
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return list;
   }
   
   public void insert(ReservationVo vo) {
      Connection conn = dbconn.conn();
      String sql = "insert into reservation values(seq_reservation.nextval,?,?,sysdate)";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getP_num().getId());
         pstmt.setString(2, vo.getId().getId());
         int num = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
   
   public int update(ReservationVo vo) {//예매 번호를 받아와서 공연코드 수정
      Connection conn = dbconn.conn();
      String sql = "update reservation set p_num=?, r_date=sysdate where r_num = ?";
      int num=0;
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getP_num().getId());
         pstmt.setInt(2, vo.getR_num());
         num = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return num;
   }
   
   public int delete(int num) {
      Connection conn = dbconn.conn();
      String sql = "delete reservation where r_num=?";
      int num1=0;
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);
         num1 = pstmt.executeUpdate();
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return num1;
   }
}