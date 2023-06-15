package performance;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class PerformanceDao {
   private DBConnect dbconn;

   public PerformanceDao() {
      dbconn = DBConnect.getInstance();
   }

   // 공연정보 전체검색
   public ArrayList<PerformanceVo> selectAll() {
      Connection conn = dbconn.conn();
      String sql = "select * from Performance";
      ArrayList<PerformanceVo> list = new ArrayList<>();         
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);         
         ResultSet rs = pstmt.executeQuery();
         while(rs.next()) {            
            list.add(new PerformanceVo( rs.getString(1),rs.getString(2),rs.getInt(3),rs.getString(4),
                  rs.getString(5),rs.getString(6),rs.getInt(7)));
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
   
   public ArrayList<PerformanceVo> selectAllCode() {
	      Connection conn = dbconn.conn();
	      String sql = "select id from Performance";
	      ArrayList<PerformanceVo> list = new ArrayList<>();         
	      try {
	         PreparedStatement pstmt = conn.prepareStatement(sql);         
	         ResultSet rs = pstmt.executeQuery();
	         while(rs.next()) {            
	            list.add(new PerformanceVo(rs.getString(1),null,0,null,null,null,0));
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
   

   // 공연날짜로 검색
   public ArrayList<PerformanceVo> selectByDate(String p_date) {
      ArrayList<PerformanceVo> list = new ArrayList<>();
      Connection conn = dbconn.conn();
      String sql = "select * from performance where p_date=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, p_date);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new PerformanceVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                  rs.getString(5), rs.getString(6), rs.getInt(7)));
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

   // 공연명으로 검색
   public ArrayList<PerformanceVo> selectByName(String name) {
      ArrayList<PerformanceVo> list = new ArrayList<>();
      Connection conn = dbconn.conn();
      String sql = "select * from performance where name=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, name);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new PerformanceVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
                  rs.getString(5), rs.getString(6), rs.getInt(7)));
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
   
   public PerformanceVo getByCode(String id) {
	   PerformanceVo vo = null;
	   Connection conn = dbconn.conn();
	   String sql = "select * from performance where id=?";
	   try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			String id2 = rs.getString(1);
			String h_id = rs.getString(2);
			int price = rs.getInt(3);
			String name = rs.getString(4);
			String category = rs.getString(5);
			String p_date = rs.getString(6);
			int seat = rs.getInt(7);
			vo = new PerformanceVo(id2, h_id, price, name, category, p_date, seat);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  finally {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	   return vo;
   }

   public void insert(PerformanceVo vo) {
      Connection conn = dbconn.conn();
      String sql = "insert into Performance values(?, ?, ?, ?, ?, ?, 30)";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getId());
         pstmt.setString(2, vo.getH_id());
         pstmt.setInt(3, vo.getPrice());
         pstmt.setString(4, vo.getName());
         pstmt.setString(5, vo.getCategory());
         pstmt.setString(6, vo.getP_date());
         //pstmt.setInt(7, vo.getSeat());

         int num = pstmt.executeUpdate();
         System.out.println(vo.getName()+" 공연이 추가되었습니다.");
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

   public void updateSeat(String id) {
      Connection conn = dbconn.conn();
      String sql = "update performance set seat=seat-1 where id=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);            
         pstmt.setString(1, id);
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
   
   public void updateSeat2(String id) {
      Connection conn = dbconn.conn();
      String sql = "update performance set seat=seat+1 where id=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);            
         pstmt.setString(1, id);
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
   
   public int update(PerformanceVo vo) {
      Connection conn = dbconn.conn();
      String sql = "update Performance set name = ?, price = ? where id = ?";
      int num = 0; 
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getName());
         pstmt.setInt(2, vo.getPrice());
         pstmt.setString(3, vo.getId());

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

   public int delete(String id) {
      Connection conn = dbconn.conn();
      String sql = "delete from Performance where id = ?";
      int num = 0;
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);

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
}