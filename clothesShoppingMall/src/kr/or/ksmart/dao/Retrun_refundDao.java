package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ksmart.driverdb.DriverDb;

public class Retrun_refundDao {
	
	// 반품 환불 전체 리스트 조회 (반품 환불 테이블, 상품 테이블 조인)
	public Map<String, Object> retrefAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// 상품 테이블에서 상품명, 반품 환불 테이블에 있는 정보를 리스트 형태로
		// 리턴하기 위해서 map 변수를 선언한다.
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			amountUnitPricePaymentUpdate(conn);
			// 쿼리 계산 전 미리 주문 수량, 개당 가격, 환불 금액을 업데이트 하는 메소드 호출
			
			String sql = "select pro.p_name, ref.ret_ref_code, ref.o_code, ref.p_code, "
					   + "buyer_id, return_payment"
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = prop.p_code";
			// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격을,
			// 상품 옵션 테이블에서 옷 성별, 색상, 옵션 코드를
			// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
			// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같을 경우의 결과만을 추출하는 조인 쿼리문 작성
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// (내부처리)
	// 주문 코드를 이용하여 반품 환불 테이블에서 주문 수량을 업데이트 한다.
	// 상품 코드를 이용하여 반품 환불 테이블에서 개당 가격을 업데이트 한다.
	// 구한 주문 수량과 개당 가격을 곱한 값으로 환불 금액을 업데이트 한다.
	private void amountUnitPricePaymentUpdate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> amountlist = new ArrayList<Integer>();
		// 주문 수량을 받을 리스트 선언
		
		List<Integer> unitlist = new ArrayList<Integer>();
		// 개당 가격을 받을 리스트 선언
		
		String sql = "select o_amount from pro_order";
		// 주문 수량을 결과값으로 받는 쿼리 작성
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // 결과값이 있다면
			amountlist.add(rs.getInt("o_amount"));		// 주문 수량
			// DB로부터 뽑아낸 값을 리스트에 추가한다.
		}
		
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		
		pstmt = conn.prepareStatement("select p_price from product");
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // 결과값이 있다면
			unitlist.add(rs.getInt("p_price"));		// 개당 가격
			// DB로부터 뽑아낸 값을 리스트에 추가한다.
		}
		
		
	}
}
