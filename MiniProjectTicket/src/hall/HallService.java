package hall;

import java.util.ArrayList;
import java.util.Scanner;

public class HallService {
   private HallDao dao;
   public static String LoginId;

   public HallService() {
      dao = new HallDao();
   }

   // 공연장 정보
   public void getAll() {
      System.out.println("<공연장 정보>");
      System.out.println("[공연장 코드| 공연장 이름| 공연장 위치]");
      ArrayList<HallVo> list = dao.selectAll();
      for (HallVo vo : list) {
         System.out.println(vo.getId() + "| " + vo.getLocation() + "| " + vo.getName());
      }
   }

   // 공연장 추가
   public void addhall(Scanner sc) {
      
     
      System.out.println("※공연장 정보를 참고하여 추가 하세요.");
      getAll();
      System.out.println();
      System.out.println("<주의> 띄어쓰기 금지, 공연코드 중복금지, 공연장 코드 Hall table 참조요망 🙏");
      System.out.println("-공연장 코드 생성시: 스포츠 스**, 뮤지컬 뮤**, 콘서트 콘** | **: 숫자 입력");

      System.out.println("<공연장 추가>");

      System.out.println(" 공연장 코드 추가: ");
      String id = sc.next();

      System.out.println("공연장 이름 추가: ");
      String name = sc.next();

      System.out.println("공연장 위치: ");
      String Location = sc.next();
      dao.insert(new HallVo(id, Location, name));
   }

   // 공연장 삭제
   public void delete(Scanner sc) {
      getAll();
      System.out.println("<공연장 삭제>");
      System.out.println("삭제할 공연장 코드: ");
      String id = sc.next();
      dao.delete(id);
   }
}