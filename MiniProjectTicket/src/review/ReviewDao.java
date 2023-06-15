package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class ReviewDao {
   private DBConnect dbconn;

   public ReviewDao() {
      dbconn = DBConnect.getInstance();
   }

// INSERT
   public int insert(ReviewVo vo) {
      Connection conn = dbconn.conn();
      String sql = "insert into review values(seq_review.nextval, ?, ?, sysdate, ?, ?)";
      int num=0;
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getId());
         pstmt.setString(2, vo.getName());
         pstmt.setString(3, vo.getContent());
         pstmt.setInt(4, vo.getScore());

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

//UPDATE
   public void update(ReviewVo vo) {
      Connection conn = dbconn.conn();
      String sql = "update review set content=?, score=? where r_id=?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getContent());
         pstmt.setInt(2, vo.getScore());
         pstmt.setInt(3, vo.getR_id());
         int num = pstmt.executeUpdate();
         System.out.println(num + " 번째 리뷰가 수정되었습니다.");
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

//DELETE
   public void delete(int num) {
      Connection conn = dbconn.conn();
      String sql = "delete from review where r_id = ?";
      PreparedStatement pstmt;
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);
         int num2 = pstmt.executeUpdate();
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

//SELECTALL
   public ArrayList<ReviewVo> selectAll() {
      Connection conn = dbconn.conn();
      String sql = "select * from review order by r_id ";
      ArrayList<ReviewVo> list = new ArrayList<>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
                  rs.getInt(6)));
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

//SelectByName
   public ArrayList<ReviewVo> selectByName(String name) {
      Connection conn = dbconn.conn();
      String sql = "select * from review where name = ? order by r_id";
      ArrayList<ReviewVo> list = new ArrayList<>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, name);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
                  rs.getInt(6)));
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

   // SelectByID
   public ArrayList<ReviewVo> selectById(String id) {
      Connection conn = dbconn.conn();
      String sql = "select * from review where id = ? order by r_id";
      ArrayList<ReviewVo> list = new ArrayList<>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5),
                  rs.getInt(6)));
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

}