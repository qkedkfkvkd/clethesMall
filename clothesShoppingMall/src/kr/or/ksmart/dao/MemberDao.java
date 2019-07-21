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
			// Ŀ�ؼ� ��ü�� ���´�.
			
			pstmt = conn.prepareStatement("select count(*) as uid from member where u_id=?");
			// �Ű������� ���� ȸ�� ���̵�� ��� ���̺� ��ȸ�� ������ �ۼ�
			
			pstmt.setString(1, u_id);	// ȸ�� ���̵� ����
			System.out.println(pstmt + "<-- pstmt   memIdChk()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   memIdChk()   MemberDao.java");
			
			if(rs.next()) {
				result = rs.getInt("uid");
				// �ش� ������ �����ϸ� 1��, �������� ������ 0�� �����Ѵ�.
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
	
	// ���� ȸ�� ��ȸ
	public Member gaeinMem(String u_id) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Member mem = new Member();
		// ȸ�� �Ѹ��� �� ������ ������ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			pstmt = conn.prepareStatement("select * from member where u_id=?");
			// �Ű������� ���� ȸ�� ���̵�� ��� ���̺� ��ȸ�� ������ �ۼ�
			
			pstmt.setString(1, u_id);	// ȸ�� ���̵� ����
			System.out.println(pstmt + "<-- pstmt   gaeinMem()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   gaeinMem()   MemberDao.java");
			
			if(rs.next()) {
				mem.setU_id(rs.getString("u_id"));				// ���̵�
				mem.setU_name(rs.getString("u_name"));			// �̸�
				mem.setU_level(rs.getString("u_level"));		// ����
				mem.setU_gender(rs.getString("u_gender"));		// ����
				mem.setU_phone(rs.getString("u_phone"));		// ����ȣ
				mem.setU_email(rs.getString("u_email"));		// �̸���
				mem.setU_address(rs.getString("u_address"));	// �ּ�
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
	
	// ��ü ȸ�� ����Ʈ ��ȸ
	public List<Member> memberAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = null;
		// ��ü ȸ�� ����Ʈ�� �����ϱ� ���� ����Ʈ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select u_id, u_name, u_level, u_gender, u_sanctions from member";
			// ���̵�, �̸�, ����, ����, ���� ����(0, 1)�� �����ϴ� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   memberAllList()   MemberDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   memberAllList()   MemberDao.java");
			
			list = new ArrayList<Member>();
			
			while(rs.next()){ // ���� �ִٸ�
				Member member = new Member();							// ȸ�� VO ��ü ����
				member.setU_id(rs.getString("u_id"));					// ���̵�
				member.setU_name(rs.getString("u_name"));				// �̸�
				member.setU_level(rs.getString("u_level"));				// ����
				member.setU_gender(rs.getString("u_gender"));			// ����
				member.setU_sanctions(rs.getBoolean("u_sanctions"));	// ���� ����(0, 1) false, true
				list.add(member); // DB�κ��� �̾Ƴ� ���� ������ ��ü�� ����Ʈ�� �߰��Ѵ�.
			}
			
			blacon_notsell(conn, list);	// ����ó�� �޼ҵ�
			// �� �����ӿ� ���� �Ǹ��� ���̺�κ��� ����Ʈ�� ����� ȸ�� ���̵��
			// ���� ���� ���� ȸ������ �ƴ��� ȸ�� ���̺� �����Ѵ�.
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
		return list;
	}
	
	//(����ó��) �� ������ üũ (üũ�Ǹ� ������(true) ǥ��)
	private void blacon_notsell(Connection conn, List<Member> list)
			throws ClassNotFoundException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = "select treat_whether from black_consumer where buyer_id=? "
					   + "union all "
					   + "select treat_whether from not_good_seller where seller_id=?";
			// �� �����ӿ� ���� �Ǹ��� ���̺�κ��� ���� ���� ����(0, 1)�� ������ ������ �ۼ� 
			
			for(int i=0; i<list.size(); i++) { // ��ü ȸ�� ��ŭ ȸ���Ѵ�.
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				// ȸ�� ���̺��� ���ڵ� ���� ��ŭ �������� ������ �ϱ� ������
				// �ݺ����� ���ۺ��� �ѹ� �ݾ��� �� ���ڵ� ������ŭ �����ش�.
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, (list.get(i)).getU_id());	// ȸ�� ���̵�
				pstmt.setString(2, (list.get(i)).getU_id());	// ȸ�� ���̵�
				rs = pstmt.executeQuery();
				
				if(rs.next()) { // ���� �ִٸ�
					if(rs.getBoolean("treat_whether")) { // ������ (1) ->true �̶��
						String chksql = "UPDATE member SET u_sanctions=1 WHERE u_id=?";
						// ȸ�� ���̺� �ش� ȸ���� ���� ���� ���θ� 1(true)�� ������Ʈ�� ������ �ۼ�
						
						PreparedStatement pstmtchk = conn.prepareStatement(chksql);
						// ������ ����
						
						pstmtchk.setString(1, (list.get(i)).getU_id()); // �ش� ȸ�� ���̵�
						
						int result = pstmtchk.executeUpdate();
						System.out.println(result + " <- result blacon_notsell()   "
								   +"if(rs.next())   MemberDao.java");
						// ������ �� ����� Ȯ���� result ������ �����ϰ� �ܼ�â�� ����غ���.
						
						try { pstmtchk.close(); } catch(SQLException ex) {}
						// ȸ�� ���̺��� ���ڵ� ���� ��ŭ �������� ������ �ϱ� ������
						// ���� �Ϸ� �� �ݾ��� �� ���ڵ� ������ŭ �����ش�.
						
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
