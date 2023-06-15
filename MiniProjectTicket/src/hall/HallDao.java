package hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class HallDao {

   private DBConnect dbconn;

   public HallDao() {
      dbconn = DBConnect.getInstance();
   }

   public void insert(HallVo vo) {
      Connection conn = dbconn.conn();
      String sql = "insert into hall values (?,?,?)";

      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getId());
         pstmt.setString(2, vo.getLocation());
         pstmt.setString(3, vo.getName());

         int num = pstmt.executeUpdate();
         System.out.println("<공연장이 추가되었습니다>");

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

   public void delete(String id) {
      Connection conn = dbconn.conn();
      String sql = "delete hall where id = ?";

      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);

         int num = pstmt.executeUpdate();
         System.out.println("<공연장이 삭제되었습니다>");

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

   public ArrayList<HallVo> selectAll() {
      // TODO Auto-generated method stub
      Connection conn = dbconn.conn();
      String sql = "select * from Hall";
      ArrayList<HallVo> list = new ArrayList<>();
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new HallVo(rs.getString(1), rs.getString(2), rs.getString(3)));
         }
      } catch (SQLException e) {
         // TODO: handle exception
         e.printStackTrace();
      } finally {
         try {
            conn.close();
         } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
         }
      }

      return list;
   }

}