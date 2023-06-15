package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class MemberDao {

   private DBConnect dbconn;

   public MemberDao() {
      dbconn = DBConnect.getInstance();
   }

   // 추가
   public void insert(MemberVo vo) {
      Connection conn = dbconn.conn();
      String sql = "insert into t_member values (?,?,?,?)";

      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getId());
         pstmt.setString(2, vo.getPwd());
         pstmt.setString(3, vo.getName());
         pstmt.setString(4, vo.getPhone());

         int num = pstmt.executeUpdate();
         System.out.println("회원이 추가되었습니다");

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

   // 수정
   public void update(MemberVo vo) {
      Connection conn = dbconn.conn();
      String sql = "update t_member set pwd=?, phone =? where id =?";

      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, vo.getPwd());
         pstmt.setString(2, vo.getPhone());
         pstmt.setString(3, vo.getId());

         int num = pstmt.executeUpdate();
         System.out.println("회원정보가 수정되었습니다");

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

   // 삭제
   public void delete(String id) {
      Connection conn = dbconn.conn();
      String sql = "delete t_member where id = ?";
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);

         int num = pstmt.executeUpdate();
         System.out.println("회원정보가 삭제되었습니다");

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

   public ArrayList<MemberVo> selectAll() { // 전체 검색
      Connection conn = dbconn.conn();
      String sql = "select * from t_member";
      ArrayList<MemberVo> list = new ArrayList<MemberVo>();
      // BoardVo 오른쪽 생략 가능
      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery();
         while (rs.next()) {
            list.add(new MemberVo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

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

   // 검색
   public MemberVo select(String id) {
      MemberVo vo = null;
      Connection conn = dbconn.conn();
      String sql = "select * from t_member where id =?";

      try {
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, id);
         ResultSet rs = pstmt.executeQuery(); // 실행
         if (rs.next()) { // 검색 결과가 있다면
            String id2 = rs.getString("id");
            String pwd = rs.getString("pwd");
            String name = rs.getString("name");
            String phone = rs.getString("phone");

            vo = new MemberVo(id2, pwd, name, phone);
//            System.out.println(id2 + "계정이 검색되었다");
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

}