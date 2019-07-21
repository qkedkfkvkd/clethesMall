package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ksmart.driverdb.DriverDb;
import kr.or.ksmart.dto.Member;

public class MemberDao {
	
	public int memIdChk(String u_id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			pstmt = conn.prepareStatement("select count(*) as uid from member where u_id=?");
			// 매개변수로 받은 회원 아이디로 멤버 테이블 조회할 쿼리문 작성
			
			pstmt.setString(1, u_id);	// 회원 아이디 삽입
			System.out.println(pstmt + "<-- pstmt   memIdChk()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   memIdChk()   MemberDao.java");
			
			if(rs.next()) {
				result = rs.getInt("uid");
				// 해당 계정이 존재하면 1을, 존재하지 않으면 0을 리턴한다.
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return result;
	}
	
	// 개인 회원 조회
	public Member gaeinMem(String u_id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member mem = new Member();
		// 회원 한명의 상세 내용을 리턴할 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			pstmt = conn.prepareStatement("select * from member where u_id=?");
			// 매개변수로 받은 회원 아이디로 멤버 테이블 조회할 쿼리문 작성
			
			pstmt.setString(1, u_id);	// 회원 아이디 삽입
			System.out.println(pstmt + "<-- pstmt   gaeinMem()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gaeinMem()   MemberDao.java");
			
			if(rs.next()) {
				mem.setU_id(rs.getString("u_id"));				// 아이디
				mem.setU_name(rs.getString("u_name"));			// 이름
				mem.setU_level(rs.getString("u_level"));		// 권한
				mem.setU_gender(rs.getString("u_gender"));		// 성별
				mem.setU_phone(rs.getString("u_phone"));		// 폰번호
				mem.setU_email(rs.getString("u_email"));		// 이메일
				mem.setU_address(rs.getString("u_address"));	// 주소
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return mem;
	}
	
	// 전체 회원 리스트 조회
	public List<Member> memberAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = null;
		// 전체 회원 리스트를 리턴하기 위한 리스트 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select u_id, u_name, u_level, u_gender, u_sanctions from member";
			// 아이디, 이름, 권한, 성별, 재제 여부(0, 1)를 리턴하는 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   memberAllList()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   memberAllList()   MemberDao.java");
			
			list = new ArrayList<Member>();
			
			while(rs.next()){ // 값이 있다면
				Member member = new Member();							// 회원 VO 객체 선언
				member.setU_id(rs.getString("u_id"));					// 아이디
				member.setU_name(rs.getString("u_name"));				// 이름
				member.setU_level(rs.getString("u_level"));				// 권한
				member.setU_gender(rs.getString("u_gender"));			// 성별
				member.setU_sanctions(rs.getBoolean("u_sanctions"));	// 제재 여부(0, 1) false, true
				list.add(member); // DB로부터 뽑아낸 값을 저장한 객체를 리스트에 추가한다.
			}
			
			blacon_notsell(conn, list);	// 내부처리 메소드
			// 블랙 컨슈머와 부정 판매자 테이블로부터 리스트에 저장된 회원 아이디로
			// 현재 제재 중인 회원인지 아닌지 회원 테이블에 수정한다.
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return list;
	}
	
	//(내부처리) 블랙 컨슈머 체크 (체크되면 제재중(true) 표시)
	private void blacon_notsell(Connection conn, List<Member> list)
			throws ClassNotFoundException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select treat_whether from black_consumer where buyer_id=? "
					   + "union all "
					   + "select treat_whether from not_good_seller where seller_id=?";
			// 블랙 컨슈머와 부정 판매자 테이블로부터 현재 재제 여부(0, 1)를 얻어오는 쿼리문 작성 
			
			for(int i=0; i<list.size(); i++) { // 전체 회원 만큼 회전한다.
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				// 회원 테이블의 레코드 갯수 만큼 쿼리문을 돌려야 하기 때문에
				// 반복문의 시작부터 한번 닫아준 후 레코드 갯수만큼 돌려준다.
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (list.get(i)).getU_id());	// 회원 아이디
				pstmt.setString(2, (list.get(i)).getU_id());	// 회원 아이디
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // 값이 있다면
					if(rs.getBoolean("treat_whether")) { // 제재중 (1) ->true 이라면
						String chksql = "UPDATE member SET u_sanctions=1 WHERE u_id=?";
						// 회원 테이블에 해당 회원을 현재 제재 여부를 1(true)로 업데이트할 쿼리문 작성
						
						PreparedStatement pstmtchk = conn.prepareStatement(chksql);
						// 쿼리문 셋팅
						
						pstmtchk.setString(1, (list.get(i)).getU_id()); // 해당 회원 아이디
						
						int result = pstmtchk.executeUpdate();
						System.out.println(result + " <- result blacon_notsell()   "
								   +"if(rs.next())   MemberDao.java");
						// 수정이 잘 됬는지 확인할 result 변수를 선언하고 콘솔창에 출력해본다.
						
						try { pstmtchk.close(); } catch(SQLException ex) {}
						// 회원 테이블의 레코드 갯수 만큼 쿼리문을 돌려야 하기 때문에
						// 쿼리 완료 후 닫아준 후 레코드 갯수만큼 돌려준다.
						
					} // if(rs.getBoolean("treat_whether"))
				} // if(rs.next())
			} // for
			System.out.println(pstmt + "<-- pstmt   blacon_notsell()   MemberDao.java");
			System.out.println(rs + "<-- rs   blacon_notsell()   MemberDao.java");
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
