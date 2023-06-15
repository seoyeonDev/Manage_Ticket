package review;

import java.util.ArrayList;
import java.util.Scanner;

import member.MemberService;
import performance.PerformanceVo;

public class ReviewService {
   private ReviewDao dao;

   public ReviewService() {
      dao = new ReviewDao();
   }

   // 리뷰작성
   public void addReview(Scanner sc) {
      System.out.println("<리뷰작성> (위에 자신이 예매했던 공연명 중 하나를 선택해주세요)");
      System.out.print("공연명: ");
      String name = sc.next();
      System.out.print("코멘트: ");
      sc.nextLine();
      String content = sc.nextLine();
      System.out.print("평점: ");
      int score = sc.nextInt();
      int num =dao.insert(new ReviewVo(0, MemberService.loginId, name, null, content, score));
      if(num==0) {
    	  System.out.println(name+"인 공연명이 존재하지 않습니다.");
      }else {
    	  System.out.println(name+" 리뷰가 작성되었습니다.");
      }
   }

   // 작성자 이름으로 검색
   public void getById(Scanner sc) {
      System.out.println("<작성자 이름으로 리뷰 검색>");
      System.out.print("작성자 이름: ");
      String id = sc.next();
      ArrayList<ReviewVo> vo = dao.selectById(id);
      if (vo.isEmpty()) {
         System.out.println(id+"로 작성된 리뷰가 없습니다");
      } else {
         System.out.println("<리뷰 검색>" + "  작성자: " + id);
         for (ReviewVo vo2 : vo) {
            System.out.println("리뷰번호: "+vo2.getR_id() + "| 작성자: " + vo2.getId() + "| 공연명:\t" + vo2.getName() + "\t| 작성일: " + vo2.getR_date() + "| 리뷰:"
                  + vo2.getContent() + "\t| 평점: " + vo2.getScore());
         }
      }
   }

   // 공연명으로 리뷰 검색
   public void getByName(Scanner sc) {
      System.out.println("<공연 List>");
      ArrayList<ReviewVo> list = dao.selectAll();
      for (ReviewVo vo : list) {
         System.out.println(vo.getName());
      }
      System.out.println("<공연명으로 검색>");
      System.out.print("공연명: ");
      String name = sc.next();
      ArrayList<ReviewVo> vo = dao.selectByName(name);
      if (vo.isEmpty()) {
         System.out.println(name+"에 관련된 공연명이 없습니다.");
      } else {
         System.out.println("<공연명 검색>" + "  공연명: " + name);
         for (ReviewVo vo2 : vo) {
             System.out.println("리뷰번호: "+vo2.getR_id() + "| 작성자: " + vo2.getId() + "| 공연명:\t" + vo2.getName() + "\t| 작성일: " + vo2.getR_date() + "| 리뷰:"
                   + vo2.getContent() + "\t| 평점: " + vo2.getScore());
          }
      }
   }

   public void delete(Scanner sc) {
      System.out.print("삭제할 리뷰 번호: ");
      int r_id = sc.nextInt();
      dao.delete(r_id);
      System.out.println("예매번호가 "+r_id +"인 리뷰가 삭제되었습니다.");
   }

   public void getByIdOnlyMe(Scanner sc) {
      ArrayList<ReviewVo> list = dao.selectById(MemberService.loginId);
      if (list.isEmpty()) {
         System.out.println("작성된 리뷰가 없습니다.");
      } else {
         for(int i=0; i<list.size(); i++) {
        	 System.out.println("리뷰번호: "+list.get(i).getR_id() + "| 작성자: " + list.get(i).getId() + "| 공연명:\t" + list.get(i).getName() + "\t| 작성일: " + list.get(i).getR_date() + "| 리뷰:"
               + list.get(i).getContent() + "\t| 평점: " + list.get(i).getScore());
         }
      }
   }

   public void getReview() { // 리뷰 전체 표현
      System.out.println("<리뷰 List>");
      ArrayList<ReviewVo> list = dao.selectAll();
      for (ReviewVo vo : list) {
         System.out.println("리뷰번호: "+vo.getR_id() + "| 작성자: " + vo.getId() + "| 공연명: \t" + vo.getName() + "\t| 작성일: " + vo.getR_date() + "| 리뷰:"
               + vo.getContent() + "\t| 평점: " + vo.getScore());
      }
   }

   public void getReviewById(Scanner sc) {
      String id = sc.next();
      System.out.println("<리뷰 List>");
      ArrayList<ReviewVo> list = dao.selectById(id);
      for (ReviewVo vo : list) {
         System.out.println("리뷰번호: "+vo.getR_id() + "| 작성자: " + vo.getId() + "| 공연명:\t" + vo.getName() + "\t| 작성일: " + vo.getR_date() + "| 리뷰:"
               + vo.getContent() + "\t| 평점: " + vo.getScore());
      }
   }
}