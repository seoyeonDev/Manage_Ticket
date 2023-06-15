package reservation;

import java.util.ArrayList;
import java.util.Scanner;

import member.MemberService;
import member.MemberVo;
import performance.PerformanceDao;
import performance.PerformanceVo;

public class ReservationService {
	private ReservationDao dao;
	private PerformanceDao dao2;

	public ReservationService() {
		dao = new ReservationDao();
		dao2 = new PerformanceDao();
	}

	public void addReservation(Scanner sc) {// 1.공연 예매하기
		System.out.println("<예매 하기>");
		System.out.println("위의 공연코드 중에서 입력해주세요~");// 곧 입력 예정
		System.out.println("공연코드: ");
		sc.nextLine();
		String p_num = sc.next();
		String id = MemberService.loginId;
		
		PerformanceVo vo = dao2.getByCode(p_num);
		if (vo.getSeat() <= 0) {
			System.out.println("좌석이 없어서 예매가 불가합니다. 다른 공연을 선택해주세요");
			return;
		}
		dao.insert(new ReservationVo(0, new PerformanceVo(p_num, null, 0, null, null, null, 0),
				new MemberVo(id, null, null, null), null));
		dao2.updateSeat(p_num);
		System.out.println("공연이 정상적으로 예매되었습니다. 예매내역에서 확인바랍니다.");
	}

	public void editReservation(Scanner sc) {// 2. 공연 예매 변경하기
		System.out.println("<예매 내역 변경>");
		System.out.println("변경할 예매번호: ");
		int r_num = sc.nextInt();
		System.out.println("변경하고 싶은 공연코드: ");
		String p_num = sc.next();
		int num = dao.update(new ReservationVo(r_num, new PerformanceVo(p_num, null, 0, null, null, null, 0),
				new MemberVo(null, null, null, null), null));
		if (num == 0) {
			System.out.println("존재하지 않은 예매번호입니다~");
		} else {
			System.out.println("예매번호가 " + r_num + "번인 공연이 공연코드가 " + p_num + "인 공연으로 변경되었습니다.");
		}
	}

	public void getAll() {
		String id = MemberService.loginId;
		ArrayList<ReservationVo> list = dao.selectAll(id);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("예매번호: " + list.get(i).getR_num());
		}
	}

	public void getAll2() {
		String id = MemberService.loginId;
		ArrayList<ReservationVo> list = dao.selectAll(id);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("예매번호: " + list.get(i).getR_num() + " |공연종류: " + list.get(i).getP_num().getCategory()
					+ " |공연코드: " + list.get(i).getP_num().getId() + " |공연명: " + list.get(i).getP_num().getName()
					+ " |공연가격: " + list.get(i).getP_num().getPrice() + " |공연날짜: " + list.get(i).getP_num().getP_date());
		}
	}

	public void getById() {
		ArrayList<ReservationVo> list = dao.selectById(MemberService.loginId);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("예매번호: " + list.get(i).getR_num() + " | 공연종류: " + list.get(i).getP_num().getCategory()
					+ " | 공연코드: " + list.get(i).getP_num().getId() + " | 공연명: " + list.get(i).getP_num().getName()
					+ " | 공연가격: " + list.get(i).getP_num().getPrice() + " | 공연날짜: "
					+ list.get(i).getP_num().getP_date());
		}
	}

	public void getByNum(Scanner sc) {
		System.out.println("검색할 예매 번호(위의 예매번호 중 선택): ");
		int r_num = sc.nextInt();
		ReservationVo vo = dao.selectByNum(r_num);
		System.out.println("예매번호: " + vo.getR_num() + " | 공연종류: " + vo.getP_num().getCategory() + " | 공연코드: "
				+ vo.getP_num().getId() + " | 공연명: " + vo.getP_num().getName() + " | 공연가격: " + vo.getP_num().getPrice()
				+ " | 공연날짜: " + vo.getP_num().getP_date());
	}

	public void delete(Scanner sc) {
		System.out.println("취소할 예매번호: ");
		int r_num = sc.nextInt();
		System.out.println("취소할 공연코드(취소한 예매번호와 같은 공연코드를 입력해주세요): ");
		String id = sc.next();
		int num = dao.delete(r_num);
		if (num == 0) {
			System.out.println("존재하지 않은 예매번호입니다~");
		} else {
			System.out.println("예매번호가 " + r_num + "번인 예매가 취소되었습니다.");
			dao2.updateSeat2(id);
		}
	}

}