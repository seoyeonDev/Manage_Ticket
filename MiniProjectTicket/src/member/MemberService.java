package member;

import java.util.ArrayList;
import java.util.Scanner;

public class MemberService {

   private MemberDao dao;

   // 로그인한 사람의 아이디 저장. 이 값이 널이면 로그인 안한 상태
   public static String loginId;

   // 생성자
   public MemberService() {
      dao = new MemberDao();
   }

   // join

   //Dao 기능의 추가, 검색 기능 사용
   public void join(Scanner sc) {
      System.out.println("<회원가입>");
      MemberVo vo = new MemberVo();
      String id = "";

      while (true) {
         System.out.print("아이디를 입력하세요: ");
         id = sc.next();

         MemberVo m = dao.select(id);

         if (m == null) {
            System.out.println("가입이 가능한 아이디입니다.");
            break;
         } else {
            System.out.println("이미 존재하는 아이디입니다.");
         }
      }

      vo.setId(id);

      System.out.println("비밀번호를 입력하세요: ");
      String pwd = sc.next();
      vo.setPwd(pwd);

      System.out.println("이름을 입력하세요: ");
      vo.setName(sc.next());

      System.out.println("전화번호를 입력하세요: ");
      vo.setPhone(sc.next());

      dao.insert(vo);
   }

   // login

   public int login(Scanner sc) {// 0:로그인실패 / 1:일반로그인 / 2:매니저로그인

      System.out.println("<로그인>");

      System.out.print("아이디를 입력하세요: ");
      String id = sc.next();

      System.out.print("비밀번호를 입력하세요: ");
      String pwd = sc.next();

      MemberVo vo = dao.select(id);
      if (vo == null) {
         System.out.println("존재하지 않는 아이디입니다.");
      } else {
         if (pwd.equals(vo.getPwd())) {
            loginId = id;
            System.out.println(id + "님 로그인 되었습니다.");

            if (loginId.equals("director")) {
//               System.out.print("관리자 계정 로그인 성공");
               return 2;
            } else {
               return 1;
            }

         } else {
            System.out.print("잘못된 비밀번호입니다. \n");
         }
      }
      return 0;

   }

   // 로그아웃
   public int logout() {
      if (loginId == null) {
      } else {
         System.out.println(loginId + "님 로그아웃 되었습니다.");
         loginId = null;
      }
      return 0;
   }

   // 내 정보 확인
   public boolean printMyInfo() {
      System.out.println("<내 정보 확인>");
      if (loginId == null) {
         System.out.print("로그인 상태가 아닙니다.\n");
         return false;
      } else {

         System.out.print(dao.select(loginId));
         return true;
      }
   }

   // 내 정보 수정
   public void editMyInfo(Scanner sc) {
      System.out.println("<내 정보 수정>");
      boolean flag = printMyInfo();
      if (flag == false) {
         return;
      } else {
         System.out.print("새 비밀번호를 입력하세요: ");
         String pwd = sc.next();
         System.out.print("새 전화번호를 입력하세요: ");
         String phone = sc.next();

         dao.update(new MemberVo(loginId, pwd, null, phone));
      }
   }

   // 회원탈퇴
   public void out() {
      System.out.println("<탈퇴>");
      if (loginId == null) {
         System.out.print("로그인 상태가 아닙니다.");
      } else {
         System.out.println(loginId + "회원님 탈퇴되었습니다. ");
         dao.delete(loginId);
         loginId = null;
      }
   }

   public void getAll() {
      System.out.println("<글 전체목록>");
      ArrayList<MemberVo> list = dao.selectAll();
      for (MemberVo vo : list) {
         System.out.println(vo.getId() + "\t" + vo.getName() + "\t" + vo.getPhone());
      }
   }
}