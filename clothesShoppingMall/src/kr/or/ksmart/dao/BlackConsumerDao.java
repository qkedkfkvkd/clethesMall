package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ksmart.driverdb.DriverDb;
import kr.or.ksmart.dto.BlackConsumer;

public class BlackConsumerDao {
	
	// 전체 지정되거나 지정요청된 블랙컨슈머 리스트
	public List<BlackConsumer> blackAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlackConsumer> bclist = null;
		// 블랙 컨슈머 리스트를 리턴하기 위한 리스트 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select buyer_id, request_seller_id, treat_whether from black_consumer";
			// 블랙컨슈머로 제재요청된 구매자 아이디, 제재를 요청한 판매자 아이디, 제재 여부(0, 1)를 리턴 받는 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   blackAllList()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   blackAllList()   BlackConsumerDao.java");
			
			bclist = new ArrayList<BlackConsumer>();
			
			while(rs.next()) {
				BlackConsumer bcn = new BlackConsumer();	// 블랙컨슈머 VO 객체 선언
				bcn.setBuyer_id(rs.getString("buyer_id"));	// 블랙컨슈머로 제재 요청된 구매자 아이디
				bcn.setRequest_seller_id(rs.getString("request_seller_id"));  // 제재 요청한 판매자 아이디
				bcn.setTreat_whether(rs.getBoolean("treat_whether"));	// 현재 제재 여부 (0, 1)
				bclist.add(bcn);	// 내용이 저장된 블랙컨슈머 객체를 리스트에 저장
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return bclist;
	}
	
	// 판매자로부터 블랙컨슈머 지정 요청된 리스트 
	public List<BlackConsumer> request_blacon() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlackConsumer> bclist = null;
		// 블랙 컨슈머 리스트를 리턴하기 위한 리스트 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select * from black_consumer";
			// 블랙 컨슈머 테이블로부터 전체 리스트를 리턴받을 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   request_blacon()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   request_blacon()   BlackConsumerDao.java");
			
			bclist = new ArrayList<BlackConsumer>();
			while(rs.next()) { // 값이 있다면
				if(!rs.getBoolean("treat_whether")) {	// 현재 제재 여부가 0(false)일 경우
					BlackConsumer bcn = new BlackConsumer();	// 블랙컨슈머 VO 객체 선언
					bcn.setBuyer_id(rs.getString("buyer_id"));	// 블랙컨슈머로 제재 요청된 구매자 아이디
					bcn.setRequest_seller_id(rs.getString("request_seller_id")); // 제재 요청한 판매자 아이디
					bcn.setRequest_reason(rs.getString("request_reason")); // 요청 사유
					bclist.add(bcn); // 내용이 저장된 블랙컨슈머 객체를 리스트에 저장
				}
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return bclist;
	}
	
	// 지정되거나 지정 요청된 블랙컨슈머 상세 보기
	public BlackConsumer blaconDetail(String buyer_id, String request_seller_id)
			throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BlackConsumer bcn = null;
		// 블랙컨슈머 상세 내용을 리턴하기 위한 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select * from black_consumer where buyer_id=? and request_seller_id=?";
			// 기본키인 제재 요청된 구매자 아이디와 제재를 요청한 판매자 아이디를 이용하여 조회할 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buyer_id);	// 블랙 컨슈머로 제재 요청된 구매자 아이디
			pstmt.setString(2, request_seller_id);	// 제재를 요청한 판매자 아이디
			System.out.println(pstmt + "<-- pstmt   blaconDetail()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   blaconDetail()   BlackConsumerDao.java");
			
			bcn = new BlackConsumer();
			if(rs.next()) {	// 값이 있다면
				bcn.setBuyer_id(rs.getString("buyer_id"));	// 블랙 컨슈머로 제재 요청된 구매자 아이디
				bcn.setRequest_seller_id(rs.getString("request_seller_id")); // 제재를 요청한 판매자 아이디
				bcn.setRequest_reason(rs.getString("request_reason")); // 요청 사유
				bcn.setTreat_reason(rs.getString("treat_reason"));	// 처리 사유
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return bcn;
	}
	
	// 블랙 컨슈머 처리하기
	public void blaconTreat(String buyer_id, String request_seller_id,
			String treat_reason) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "update black_consumer set treat_whether=1, treat_reason=? "
					   + "where buyer_id=? and request_seller_id=?";
			// 현재 제재여부를 1(true)로 지정하고 처리사유를 업데이트 하기 위하여
			// 기본키인 제재 요청된 구매자 아이디와 제재를 요청한 판매자 아이디를 이용한다.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, treat_reason);		// 처리 사유
			pstmt.setString(2, buyer_id);			// 제재 요청된 구매자 아이디
			pstmt.setString(3, request_seller_id);	// 제재를 요청한 판매자 아이디
			System.out.println(pstmt + "<-- pstmt   blaconTreat()   BlackConsumerDao.java");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   blaconTreat()   BlackConsumerDao.java");
			// 업데이트가 성공적으로 이뤄졌을 경우 1을 콘솔창에 출력한다.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
