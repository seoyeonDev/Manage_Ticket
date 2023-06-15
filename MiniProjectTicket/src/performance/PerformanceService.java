package performance;

import java.util.ArrayList;
import java.util.Scanner;

public class PerformanceService {
   PerformanceDao dao;

   public PerformanceService() {
      dao = new PerformanceDao();
   }

   public void getAll() { // 남은 좌석수가 나오게끔
      System.out.println("<전체 공연정보>");
      ArrayList<PerformanceVo> list = dao.selectAll();
      for (PerformanceVo vo : list) {
         System.out.println("|공연종류: " + vo.getCategory() + " |공연코드: " + vo.getId() + " |공연명: \t" + vo.getName()
               + "\t|공연가격: " + vo.getPrice() + "\t|공연날짜: " + vo.getP_date() + "\t>좌석 수: " + vo.getSeat());
      }
   }

   public void getByDate(Scanner sc) {
      System.out.println("<공연날짜로 검색>");
      System.out.print("날짜검색: ");
      String date = sc.next(); // 고정된 공연날짜->String으로
      ArrayList<PerformanceVo> vo = dao.selectByDate(date);
      if (vo.isEmpty()) {
         System.out.println(date + " 공연날짜 없음");
      } else {
         System.out.println(vo);
      }
   }

   public void getByName(Scanner sc) {
      System.out.println("<공연명으로 검색>");
      System.out.println("공연명: ");
      String name = sc.next();
      ArrayList<PerformanceVo> vo = dao.selectByName(name);
      if (vo.isEmpty()) {
         System.out.println(name + " 공연명 없음");
      } else {
         System.out.println(vo);
      }
   }

   public void addPerformance(Scanner sc) {
      System.out.println("<공연추가>");
      System.out.println("공연종류(스포츠/뮤지컬/콘서트): ");
      String category = sc.next();
      System.out.println("공연코드(s00/m00/c00): ");
      String id = sc.next();
      System.out.println("공연장코드(스01/뮤01/콘01): ");
      String h_id = sc.next();
      System.out.println("공연명: ");
      String name = sc.next();
      System.out.println("공연가격(원): ");
      int price = sc.nextInt();
      System.out.println("공연날짜(yy/mm/dd): ");
      String p_date = sc.next();
      dao.insert(new PerformanceVo(id, h_id, price, name, category, p_date, 0));
   }

   public void editPerformance(Scanner sc) {
      System.out.println("<공연정보 수정>");
      System.out.println("변경할 공연코드");
      String id = sc.next();
      System.out.println("변경할 공연명");
      String name = sc.next();
      System.out.println("변경할 가격");
      int price = sc.nextInt();

      int num = dao.update(new PerformanceVo(id, null, price, name, null, null, 0));
      if (num == 0) {
         System.out.println(id + "번의 공연코드가 없습니다 ㅜㅜ");
      } else {
         System.out.println("수정되었습니다.^ㅡㅡ^");
      }
   }

   public void delete(Scanner sc) {
      System.out.println("삭제할 공연코드: ");
      String id = sc.next();
      int num = dao.delete(id);
      if (num == 0) {
         System.out.println(id + "번의 공연코드가 없습니다 ㅜㅜ");
      } else {
         System.out.println(id + "번 공연코드가 삭제되었습니다.");
      }
   }

}