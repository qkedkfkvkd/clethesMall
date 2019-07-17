package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ksmart.driverdb.DriverDb;
import kr.or.ksmart.dto.Product;
import kr.or.ksmart.dto.Retrun_refund;

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
			
			String sql = "select pro.p_name, ref.retf_o_code, ref.p_code, "
					   + "ref.buyer_id, ref.return_payment"
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = ref.p_code";
			// 상품 테이블에서 상품 이름을,
			// 반품 환불 테이블에서 반품 환불 코드, 상품 코드, 구매자 아이디, 환불 금액을
			// 상품 테이블의 별명을 pro로 반품 환불 테이블의 별명을 ref로 하고
			// 상품 테이블의 상품 코드와 반품 환불 테이블의 상품 코드가 같을 경우의 결과만을 추출하는 조인 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
			
			map = new HashMap<String, Object>();
			List<String> prolist = new ArrayList<String>();
			List<Retrun_refund> reflist = new ArrayList<Retrun_refund>();
			
			while(rs.next()) {
				Retrun_refund ref = new Retrun_refund();
				ref.setRetf_o_code(rs.getString("retf_o_code"));		// 반품 환불 주문코드
				ref.setP_code(rs.getString("p_code"));					// 상품 코드
				ref.setBuyer_id(rs.getString("buyer_id"));				// 구매자 아이디
				ref.setReturn_payment(rs.getInt("return_payment"));		// 환불 금액
				
				prolist.add(rs.getString("p_name"));					// 상품 이름
				reflist.add(ref);					// DB에서 뽑아낸 객체를 하나씩 저장한다.
			}
			map.put("pname", prolist);
			map.put("reflist", reflist);
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
		List<String> retfolist = new ArrayList<String>();
		// 반품 환불 테이블에서 반품 환불 코드를 받을 리스트 선언
		
		List<String> pcodelist = new ArrayList<String>();
		// 반품 환불 테이블에서 상품 코드를 받을 리스트 선언
		
		int retRefCount = 0;
		// 반품 환불 테이블 전체 레코드 수를 저장하는 변수 선언
		
		String sql = "select retf_o_code from retrun_refund";
		// 반품 환불 코드를 결과값으로 받는 쿼리 작성
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // 결과값이 있다면
			retfolist.add(rs.getString("retf_o_code"));
			// DB로부터 뽑아낸 주문 테이블의 주문 코드를 리스트에 추가한다.
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		sql = "select distinct p_code from retrun_refund";
		// 반품 환불 테이블에서 상품 코드를 뽑아내는 쿼리문 작성
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // 결과값이 있다면
			pcodelist.add(rs.getString("p_code"));
			// DB로부터 뽑아낸 반품 환불 테이블의 상품 코드를 리스트에 추가한다.
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		sql = "select count(*) as refcnt from retrun_refund";
		// 반품 환불 테이블 전체 레코드수를 뽑아낼 쿼리문 작성
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");

		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		if(rs.next()) {
			retRefCount = rs.getInt("refcnt"); // 반품 환불 테이블 전체 레코드 수 저장
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		// 반품 환불 테이블의 레코드 갯수만큼 회전한다.
		for(int i=0; i<retfolist.size(); i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund as ref "
				+ "set ref.o_amount = "
				+ "(select o_amount from pro_order where o_code = ?) "
				+ "where ref.retf_o_code = ?";
			// 주문 테이블의 기본키인 주문 코드로 주문 수량을 구해와서
			// 반품 환불 테이블의 기본키인 반품 환불 코드에 해당하는 반품 환불 수량에 업데이트 하는 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, retfolist.get(i));	// 반품 환불 테이블 반품 환불 코드
			pstmt.setString(2, retfolist.get(i));
			// 반품환불테이블의 반품환불 코드를 주문 코드에 삽입
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		}
		
		
		// 반품 환불 테이블의 상품 갯수 만큼 회전한다.
		for(int i=0; i<pcodelist.size(); i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund as ref "
				+ "set ref.o_unit_price = "
				+ "(select p_price from product where p_code = ?) "
				+ "where ref.p_code = ?";
			// 상품 테이블의 상품코드로 상품 가격을 구해와서 반품 환불 테이블에
			// 해당 상품 코드를 가진 상품들의 개당 가격을 업데이트하는 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcodelist.get(i));	// 반품 환불 테이블의 상품 코드
			pstmt.setString(2, pcodelist.get(i));
			// 상품 테이블의 상품 코드를 반품 환불 테이블의 상품 코드에 삽입
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		}
		
		// 반품 환불 테이블의 레코드 갯수만큼 회전한다.
		for(int i=0; i<retRefCount; i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund "
				+ "set return_payment =(o_amount*o_unit_price) "
				+ "where retf_o_code = ?";
			// 레코드 하나당 하나의 환불금액을 업데이트 하는 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, retfolist.get(i));
		}
		
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		// 상품 테이블의 상품 갯수만큼 반복문의 회전을 마치고나면 pstmt 리소스가 한개 남으니 닫아준다.
	}
	
	
	// 반품 환불 테이블 상세 조회
	public Map<String, Object> returnRefundDetail(String retf_o_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// 상품 테이블에서 상품명, 반품 환불 테이블에 있는 정보를 리스트 형태로
		// 리턴하기 위해서 map 변수를 선언한다.
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select pro.p_name, pro.p_cate, ref.* "
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = ref.p_code "
					   + "where retf_o_code = ?";
			// 상품 테이블에서 상품 이름, 카테고리를,
			// 반품환불 테이블에서 반품환불 코드, 상품 코드, 구매자 아이디, 환불 금액을
			// 상품 테이블의 별명을 pro로 반품 환불 테이블의 별명을 ref로 하고
			// 상품 테이블의 상품 코드와 반품 환불 테이블의 상품 코드가 같을 경우의 결과만을 추출하는 조인 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, retf_o_code);		// 반품 환불 주문코드
			System.out.println(pstmt + "<-- pstmt   returnRefundDetail()   Retrun_refundDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   returnRefundDetail()   Retrun_refundDao.java");
			
			map = new HashMap<String, Object>();
			
			Product pro = new Product();
			Retrun_refund ref = new Retrun_refund();
			
			while(rs.next()) {
				pro.setP_name(rs.getString("p_name"));					// 상품 이름
				pro.setP_cate(rs.getString("p_cate"));					// 카테고리
				
				ref.setRetf_o_code(rs.getString("retf_o_code"));		// 반품 환불 코드
				ref.setP_code(rs.getString("p_code"));					// 상품 코드
				ref.setSeller_id(rs.getString("seller_id"));			// 판매자 아이디
				ref.setBuyer_id(rs.getString("buyer_id"));				// 구매자 아이디
				ref.setO_amount(rs.getInt("o_amount"));					// 주문 수량
				ref.setO_unit_price(rs.getInt("o_unit_price"));			// 개당 가격
				ref.setReturn_payment(rs.getInt("return_payment"));		// 환불 금액
			}
			
			map.put("pro", pro);			// 상품 객체 저장
			map.put("ref", ref);			// 반품 환불 객체 저장
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
}
