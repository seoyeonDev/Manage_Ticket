package ticketMain;

import java.util.Scanner;

import hall.HallService;
import member.MemberService;
import performance.PerformanceService;
import reservation.ReservationService;
import review.ReviewService;

public class Menu {

	private MemberService mService;
	private HallService hService;
	private PerformanceService pService;
	private ReservationService rtService;
	private ReviewService rService;
	private int memType;

	public Menu() {
		mService = new MemberService();
		hService = new HallService();
		pService = new PerformanceService();
		rtService = new ReservationService();
		rService = new ReviewService();
	}

	// 전체 메뉴

	public void run(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.로그인/회원가입 2.마이 페이지 3.공연 둘러보기 4.공연 예매하기 5.리뷰 6.종료");
			m = sc.nextInt();
			switch (m) {
			case 1:
				mrun(sc);
				break;
			case 2:
				if (memType == 0) {
					System.out.println("회원 로그인을 먼저 하셔야 합니다.");
					break;
				}
				memrun(sc);
				break;
			case 3:
				prun(sc);
				break;
			case 4:
				if (memType == 0 || memType==2) {
					System.out.println("회원 로그인을 먼저 하셔야 합니다.");
					break;
				}
				rtrun(sc);
				break;
			case 5:
				if (memType == 0) {
					System.out.println("회원 로그인을 먼저 하셔야 합니다.");
					break;
				}
				rrun(sc);
				break;
			case 6:
				System.out.println("종료");
				flag = false;

			}
		}
	}

	public void prun(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.공연목록 2.공연명으로 검색 3.공연날짜로 검색 4.이전메뉴");
			m = sc.nextInt();
			switch (m) {
			case 1:
				pService.getAll();
				break;
			case 2:
				pService.getByName(sc);
				break;
			case 3:
				pService.getByDate(sc);
				break;
			case 4:
				System.out.println("종료");
				flag = false;
			}
		}
	}

	public void rtrun(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("1.예매하기 2.예매변경 3.예매번호로 검색 4.나의 예매내역 5.예매취소 6.이전메뉴");
			m = sc.nextInt();
			switch (m) {
			case 1:
				pService.getAll();
				rtService.addReservation(sc);
				break;
			case 2:
				rtService.getById();
				rtService.editReservation(sc);
				break;
			case 3:
				rtService.getAll();
				rtService.getByNum(sc);
				break;
			case 4:
				rtService.getById();
				break;
			case 5:
				rtService.getById();
				rtService.delete(sc);
				break;
			case 6:
				flag = false;
			}
		}
	}

	public void rrun(Scanner sc) {
	      boolean flag = true;
	      int m = 0;
	      while (flag) {
	         System.out.println("1.리뷰작성 2.작성자로 리뷰검색 3.공연명으로 리뷰검색 4.리뷰삭제 5.리뷰보기 6.이전메뉴");
	         m = sc.nextInt();
	         switch (m) {
	         case 1:
	            rtService.getAll2();
	            rService.addReview(sc);
	            break;
	         case 2:
	            rService.getById(sc);
	            break;
	         case 3:
	            rService.getByName(sc);
	            break;
	         case 4:
	            rService.getReview();
	            rService.delete(sc);
	         case 5:
	            rService.getReview();
	            break;
	         case 6:
	            flag = false;
	         }
	      }
	   }

	public void mrun(Scanner sc) {
		if (memType == 1 || memType == 2) {
			System.out.println("이미 로그인 되어 있습니다");
			return;
		}
		if (memType == 0) {
			boolean flag = true;
			int m = 0;
			while (flag) {
				System.out.println("1.회원가입 2.로그인 3.이전메뉴");
				m = sc.nextInt();
				switch (m) {
				case 1:
					mService.join(sc);
					break;
				case 2:
					memType = mService.login(sc);
					flag = false;
				case 3:
					flag = false;
				}
			}
		}
	}

	// 회원 & 관리자 정보 확인
	public void memrun(Scanner sc) {

		// 회원 메뉴
		if (memType == 1) {
			boolean flag = true;
			int m = 0;
			while (flag) {
				System.out.println("1.내정보 확인 2.내정보 수정 3. 예매내역 확인 4. 내리뷰 목록 5.탈퇴 6.로그아웃 7.이전메뉴");
				m = sc.nextInt();
				switch (m) {
				case 1:
					mService.printMyInfo();
					break;
				case 2:
					mService.editMyInfo(sc);
					break;
				case 3:
					rtService.getById();
					break;
				case 4:
					rService.getByIdOnlyMe(sc);
					break;
				case 5:
					mService.out();
					break;
				case 6:
					memType = mService.logout();
					break;
				case 7:
					flag = false;
				}
			}
		}

		// 관리자 메뉴
		if (memType == 2) {
			boolean flag = true;
			int m = 0;

			while (flag) {
				System.out.println("1.공연 추가 2.공연 삭제 3.공연정보 수정 4.공연장 추가 5.공연장 삭제 6.로그아웃 7.이전메뉴");
				m = sc.nextInt();
				switch (m) {
				case 1:
					pService.getAll();
					System.out.println("<주의> 띄어쓰기 금지, 공연코드 중복금지, 공연장 코드 Hall table 참조요망 🙏");
					pService.addPerformance(sc);
					break;
				case 2:
					pService.getAll();
					pService.delete(sc);
					pService.getAll();
					break;
				case 3:
					pService.getAll();
					pService.editPerformance(sc);
					break;
				case 4:
					hService.addhall(sc);
					break;
				case 5:
					hService.delete(sc);
					break;
				case 6:
					memType = mService.logout();
				case 7:
					flag = false;
					break;
				}
			}
		}
	}
}
