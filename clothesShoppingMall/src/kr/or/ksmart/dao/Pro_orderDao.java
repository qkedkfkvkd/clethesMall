package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ksmart.driverdb.DriverDb;
import kr.or.ksmart.dto.Pro_order;

public class Pro_orderDao {
	
	//전체 주문 현황 리스트
	public List<Pro_order> proOrderAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pro_order> prlist = null;
		// 전체 주문 현황 리스트를 리턴하기 위해 리스트 변수를 선언한다.
		
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			unitPricePaymentUpdate(conn);
			// 커넥션 객체를 매개변수로 받아서 개당 금액과 결제 금액을
			// 업데이트 시켜주는 내부처리 메소드 호출
			
			String sql = "select o_code, buyer_id, p_code, o_amount, "
					   + "o_unit_price, o_payment from pro_order";
			// 주문 코드, 구매자 아이디, 상품 코드, 주문 수량,
			// 개당 가격, 결제 금액을 반환 받을 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   proOrderAllList()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   proOrderAllList()   Pro_orderDao.java");
			
			prlist = new ArrayList<Pro_order>();
			
			while(rs.next()) { // 결과값이 있다면
				Pro_order pro = new Pro_order();				// 주문 VO 객체 선언
				pro.setO_code(rs.getString("o_code"));			// 주문 코드
				pro.setBuyer_id(rs.getString("buyer_id"));		// 구매자 아이디
				pro.setP_code(rs.getString("p_code"));			// 상품 코드
				pro.setO_amount(rs.getInt("o_amount"));			// 주문 수량
				pro.setO_unit_price(rs.getInt("o_unit_price"));	// 개당 가격
				pro.setO_payment(rs.getInt("o_payment"));		// 결제 금액
				
				prlist.add(pro);
				// DB로부터 뽑아낸 값을 저장한 객체를 리스트에 추가한다.
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return prlist;
	}
	
	
	// (내부처리)
	// 상품코드를 이용하여 개당 가격을 업데이트 시키고
	// 주문 수량과 개당 가격을 곱하여 결제 금액을 업데이트 시킨다.
	private void unitPricePaymentUpdate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pro_order> prlist = new ArrayList<Pro_order>();
		// 주문 코드, 상품 코드, 주문 수량을 받을 리스트 선언	
		
		String sql = "select o_code, p_code, o_amount from pro_order";
		// 주문 코드와 상품 코드, 주문 수량을 결과값으로 받는 쿼리 작성
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   unitPricePaymentUpdate()   Pro_orderDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   unitPricePaymentUpdate()   Pro_orderDao.java");
		
		
		while(rs.next()) { // 결과값이 있다면
			Pro_order pro = new Pro_order();
			pro.setO_code(rs.getString("o_code"));		// 주문 코드
			pro.setP_code(rs.getString("p_code"));		// 상품 코드
			pro.setO_amount(rs.getInt("o_amount"));		// 주문 수량
			
			prlist.add(pro);
			// DB로부터 뽑아낸 값을 저장한 객체를 리스트에 추가한다.
		}
		
		
		for(int i=0; i<prlist.size(); i++) { // 주문 테이블의 레코드 갯수만큼 돌린다.
			
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			// 주문 테이블의 레코드 갯수 만큼 쿼리문을 돌려야 하기 때문에
			// 반복문의 시작부터 한번 닫아준 후 레코드 갯수만큼 돌려준다.
			
			sql = "select p_price from prduct where p_code =?";
			// 상품 테이블에서 상품코드를 이용하여 가격을 뽑아내는 쿼리문을 작성한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (prlist.get(i)).getP_code());
			// 주문 테이블의 첫번째 상품 코드부터 순서대로 돌린다.
			
			System.out.println(pstmt + "<-- pstmt   unitPricePaymentUpdate()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   unitPricePaymentUpdate()   Pro_orderDao.java");
			
			int p_price = 0;
			// 상품 가격을 저장할 변수
			
			if(rs.next()) { // 값이 있다면
				p_price = rs.getInt("p_price"); // 뽑아낸 상품 가격을 변수에 저장한다.
			}
			
			int payment = p_price * (prlist.get(i)).getO_amount();
			//  결제 금액 =   개당 가격 * 주문 수량
			
			try { rs.close(); } catch(SQLException ex) {}
			try { pstmt.close(); } catch(SQLException ex) {}
			// update를 시켜야하기 때문에 한번 닫아줘야 한다.
			
			sql = "UPDATE pro_order SET o_unit_price=?, o_payment=? WHERE o_code=?";
			// 해당 주문 코드의 개당 가격, 결제 금액을 업데이트 시켜줄 쿼리문을 작성한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_price);	// 상품 테이블로부터 뽑아낸 개당 가격
			pstmt.setInt(2, payment);	// 개당 가격과 주문 수량을 곱한 결제 금액
			pstmt.setString(3, (prlist.get(i)).getO_code());	// 주문 코드
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   unitPricePaymentUpdate()   Pro_orderDao.java");
			
		} // for end
	}
	
	
	// 회원 주문 상세 조회
	public Pro_order proOrderDetail(String o_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pro_order pro = new Pro_order();
		// 주문 상세 내용을 리턴할 객체 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			pstmt = conn.prepareStatement("select * from pro_order where o_code=?");
			// 매개변수로 받은 주문 코드로 주문 테이블에서 조회한다.
			
			pstmt.setString(1, o_code);	// 주문 코드 삽입
			System.out.println(pstmt + "<-- pstmt   proOrderDetail()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   proOrderDetail()   Pro_orderDao.java");
			
			if(rs.next()) {	// 값이 있다면
				pro.setO_code(rs.getString("o_code"));				// 주문 코드
				pro.setBuyer_id(rs.getString("buyer_id"));			// 구매자 아이디
				pro.setP_code(rs.getString("p_code"));				// 상품 코드
				pro.setO_amount(rs.getInt("o_amount"));				// 주문 수량
				pro.setO_unit_price(rs.getInt("o_unit_price"));		// 개당 가격
				pro.setO_payment(rs.getInt("o_payment"));			// 주문 수량
				pro.setO_get_addr(rs.getString("o_get_addr"));		// 받는 사람 주소
				pro.setO_get_call(rs.getString("o_get_call"));		// 받는 사람 번호
				pro.setO_send_name(rs.getString("o_send_name"));	// 보내는 사람 이름
				pro.setO_send_call(rs.getString("o_send_call"));	// 보내는 사람 번호
				pro.setO_date(rs.getString("o_date"));				// 주문 일자
				pro.setO_message(rs.getString("o_message"));		// 배송 메세지
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return pro;
	}
}
