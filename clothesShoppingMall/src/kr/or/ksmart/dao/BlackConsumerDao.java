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
	
	// ��ü �����ǰų� ������û�� �������� ����Ʈ
	public List<BlackConsumer> blackAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlackConsumer> bclist = null;
		// �� ������ ����Ʈ�� �����ϱ� ���� ����Ʈ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select buyer_id, request_seller_id, treat_whether from black_consumer";
			// �������ӷ� �����û�� ������ ���̵�, ���縦 ��û�� �Ǹ��� ���̵�, ���� ����(0, 1)�� ���� �޴� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   blackAllList()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   blackAllList()   BlackConsumerDao.java");
			
			bclist = new ArrayList<BlackConsumer>();
			
			while(rs.next()) {
				BlackConsumer bcn = new BlackConsumer();	// �������� VO ��ü ����
				bcn.setBuyer_id(rs.getString("buyer_id"));	// �������ӷ� ���� ��û�� ������ ���̵�
				bcn.setRequest_seller_id(rs.getString("request_seller_id"));  // ���� ��û�� �Ǹ��� ���̵�
				bcn.setTreat_whether(rs.getBoolean("treat_whether"));	// ���� ���� ���� (0, 1)
				bclist.add(bcn);	// ������ ����� �������� ��ü�� ����Ʈ�� ����
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
	
	// �Ǹ��ڷκ��� �������� ���� ��û�� ����Ʈ 
	public List<BlackConsumer> request_blacon() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BlackConsumer> bclist = null;
		// �� ������ ����Ʈ�� �����ϱ� ���� ����Ʈ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select * from black_consumer";
			// �� ������ ���̺�κ��� ��ü ����Ʈ�� ���Ϲ��� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   request_blacon()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   request_blacon()   BlackConsumerDao.java");
			
			bclist = new ArrayList<BlackConsumer>();
			while(rs.next()) { // ���� �ִٸ�
				if(!rs.getBoolean("treat_whether")) {	// ���� ���� ���ΰ� 0(false)�� ���
					BlackConsumer bcn = new BlackConsumer();	// �������� VO ��ü ����
					bcn.setBuyer_id(rs.getString("buyer_id"));	// �������ӷ� ���� ��û�� ������ ���̵�
					bcn.setRequest_seller_id(rs.getString("request_seller_id")); // ���� ��û�� �Ǹ��� ���̵�
					bcn.setRequest_reason(rs.getString("request_reason")); // ��û ����
					bclist.add(bcn); // ������ ����� �������� ��ü�� ����Ʈ�� ����
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
	
	// �����ǰų� ���� ��û�� �������� �� ����
	public BlackConsumer blaconDetail(String buyer_id, String request_seller_id)
			throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BlackConsumer bcn = null;
		// �������� �� ������ �����ϱ� ���� ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select * from black_consumer where buyer_id=? and request_seller_id=?";
			// �⺻Ű�� ���� ��û�� ������ ���̵�� ���縦 ��û�� �Ǹ��� ���̵� �̿��Ͽ� ��ȸ�� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, buyer_id);	// �� �����ӷ� ���� ��û�� ������ ���̵�
			pstmt.setString(2, request_seller_id);	// ���縦 ��û�� �Ǹ��� ���̵�
			System.out.println(pstmt + "<-- pstmt   blaconDetail()   BlackConsumerDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   blaconDetail()   BlackConsumerDao.java");
			
			bcn = new BlackConsumer();
			if(rs.next()) {	// ���� �ִٸ�
				bcn.setBuyer_id(rs.getString("buyer_id"));	// �� �����ӷ� ���� ��û�� ������ ���̵�
				bcn.setRequest_seller_id(rs.getString("request_seller_id")); // ���縦 ��û�� �Ǹ��� ���̵�
				bcn.setRequest_reason(rs.getString("request_reason")); // ��û ����
				bcn.setTreat_reason(rs.getString("treat_reason"));	// ó�� ����
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
	
	// �� ������ ó���ϱ�
	public void blaconTreat(String buyer_id, String request_seller_id,
			String treat_reason) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "update black_consumer set treat_whether=1, treat_reason=? "
					   + "where buyer_id=? and request_seller_id=?";
			// ���� ���翩�θ� 1(true)�� �����ϰ� ó�������� ������Ʈ �ϱ� ���Ͽ�
			// �⺻Ű�� ���� ��û�� ������ ���̵�� ���縦 ��û�� �Ǹ��� ���̵� �̿��Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, treat_reason);		// ó�� ����
			pstmt.setString(2, buyer_id);			// ���� ��û�� ������ ���̵�
			pstmt.setString(3, request_seller_id);	// ���縦 ��û�� �Ǹ��� ���̵�
			System.out.println(pstmt + "<-- pstmt   blaconTreat()   BlackConsumerDao.java");
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   blaconTreat()   BlackConsumerDao.java");
			// ������Ʈ�� ���������� �̷����� ��� 1�� �ܼ�â�� ����Ѵ�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}
