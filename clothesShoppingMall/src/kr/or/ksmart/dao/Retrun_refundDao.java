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
	
	// ��ǰ ȯ�� ��ü ����Ʈ ��ȸ (��ǰ ȯ�� ���̺�, ��ǰ ���̺� ����)
	public Map<String, Object> retrefAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// ��ǰ ���̺��� ��ǰ��, ��ǰ ȯ�� ���̺� �ִ� ������ ����Ʈ ���·�
		// �����ϱ� ���ؼ� map ������ �����Ѵ�.
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			amountUnitPricePaymentUpdate(conn);
			// ���� ��� �� �̸� �ֹ� ����, ���� ����, ȯ�� �ݾ��� ������Ʈ �ϴ� �޼ҵ� ȣ��
			
			String sql = "select pro.p_name, ref.ret_ref_code, ref.o_code, ref.p_code, "
					   + "buyer_id, return_payment"
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = prop.p_code";
			// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ������,
			// ��ǰ �ɼ� ���̺��� �� ����, ����, �ɼ� �ڵ带
			// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
			// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� ����� ������� �����ϴ� ���� ������ �ۼ�
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// (����ó��)
	// �ֹ� �ڵ带 �̿��Ͽ� ��ǰ ȯ�� ���̺��� �ֹ� ������ ������Ʈ �Ѵ�.
	// ��ǰ �ڵ带 �̿��Ͽ� ��ǰ ȯ�� ���̺��� ���� ������ ������Ʈ �Ѵ�.
	// ���� �ֹ� ������ ���� ������ ���� ������ ȯ�� �ݾ��� ������Ʈ �Ѵ�.
	private void amountUnitPricePaymentUpdate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> amountlist = new ArrayList<Integer>();
		// �ֹ� ������ ���� ����Ʈ ����
		
		List<Integer> unitlist = new ArrayList<Integer>();
		// ���� ������ ���� ����Ʈ ����
		
		String sql = "select o_amount from pro_order";
		// �ֹ� ������ ��������� �޴� ���� �ۼ�
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // ������� �ִٸ�
			amountlist.add(rs.getInt("o_amount"));		// �ֹ� ����
			// DB�κ��� �̾Ƴ� ���� ����Ʈ�� �߰��Ѵ�.
		}
		
		if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		
		pstmt = conn.prepareStatement("select p_price from product");
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // ������� �ִٸ�
			unitlist.add(rs.getInt("p_price"));		// ���� ����
			// DB�κ��� �̾Ƴ� ���� ����Ʈ�� �߰��Ѵ�.
		}
		
		
	}
}
