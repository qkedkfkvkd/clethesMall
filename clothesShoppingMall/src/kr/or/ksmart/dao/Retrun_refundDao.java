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
			
			String sql = "select pro.p_name, ref.retf_o_code, ref.p_code, "
					   + "ref.buyer_id, ref.return_payment"
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = ref.p_code";
			// ��ǰ ���̺��� ��ǰ �̸���,
			// ��ǰ ȯ�� ���̺��� ��ǰ ȯ�� �ڵ�, ��ǰ �ڵ�, ������ ���̵�, ȯ�� �ݾ���
			// ��ǰ ���̺��� ������ pro�� ��ǰ ȯ�� ���̺��� ������ ref�� �ϰ�
			// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ ȯ�� ���̺��� ��ǰ �ڵ尡 ���� ����� ������� �����ϴ� ���� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
			
			map = new HashMap<String, Object>();
			List<String> prolist = new ArrayList<String>();
			List<Retrun_refund> reflist = new ArrayList<Retrun_refund>();
			
			while(rs.next()) {
				Retrun_refund ref = new Retrun_refund();
				ref.setRetf_o_code(rs.getString("retf_o_code"));		// ��ǰ ȯ�� �ֹ��ڵ�
				ref.setP_code(rs.getString("p_code"));					// ��ǰ �ڵ�
				ref.setBuyer_id(rs.getString("buyer_id"));				// ������ ���̵�
				ref.setReturn_payment(rs.getInt("return_payment"));		// ȯ�� �ݾ�
				
				prolist.add(rs.getString("p_name"));					// ��ǰ �̸�
				reflist.add(ref);					// DB���� �̾Ƴ� ��ü�� �ϳ��� �����Ѵ�.
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
	
	
	// (����ó��)
	// �ֹ� �ڵ带 �̿��Ͽ� ��ǰ ȯ�� ���̺��� �ֹ� ������ ������Ʈ �Ѵ�.
	// ��ǰ �ڵ带 �̿��Ͽ� ��ǰ ȯ�� ���̺��� ���� ������ ������Ʈ �Ѵ�.
	// ���� �ֹ� ������ ���� ������ ���� ������ ȯ�� �ݾ��� ������Ʈ �Ѵ�.
	private void amountUnitPricePaymentUpdate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> retfolist = new ArrayList<String>();
		// ��ǰ ȯ�� ���̺��� ��ǰ ȯ�� �ڵ带 ���� ����Ʈ ����
		
		List<String> pcodelist = new ArrayList<String>();
		// ��ǰ ȯ�� ���̺��� ��ǰ �ڵ带 ���� ����Ʈ ����
		
		int retRefCount = 0;
		// ��ǰ ȯ�� ���̺� ��ü ���ڵ� ���� �����ϴ� ���� ����
		
		String sql = "select retf_o_code from retrun_refund";
		// ��ǰ ȯ�� �ڵ带 ��������� �޴� ���� �ۼ�
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // ������� �ִٸ�
			retfolist.add(rs.getString("retf_o_code"));
			// DB�κ��� �̾Ƴ� �ֹ� ���̺��� �ֹ� �ڵ带 ����Ʈ�� �߰��Ѵ�.
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		sql = "select distinct p_code from retrun_refund";
		// ��ǰ ȯ�� ���̺��� ��ǰ �ڵ带 �̾Ƴ��� ������ �ۼ�
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		while(rs.next()) { // ������� �ִٸ�
			pcodelist.add(rs.getString("p_code"));
			// DB�κ��� �̾Ƴ� ��ǰ ȯ�� ���̺��� ��ǰ �ڵ带 ����Ʈ�� �߰��Ѵ�.
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		sql = "select count(*) as refcnt from retrun_refund";
		// ��ǰ ȯ�� ���̺� ��ü ���ڵ���� �̾Ƴ� ������ �ۼ�
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");

		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		
		if(rs.next()) {
			retRefCount = rs.getInt("refcnt"); // ��ǰ ȯ�� ���̺� ��ü ���ڵ� �� ����
		}
		
		try { rs.close(); } catch(SQLException ex) {}
		try { pstmt.close(); } catch(SQLException ex) {}
		
		// ��ǰ ȯ�� ���̺��� ���ڵ� ������ŭ ȸ���Ѵ�.
		for(int i=0; i<retfolist.size(); i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund as ref "
				+ "set ref.o_amount = "
				+ "(select o_amount from pro_order where o_code = ?) "
				+ "where ref.retf_o_code = ?";
			// �ֹ� ���̺��� �⺻Ű�� �ֹ� �ڵ�� �ֹ� ������ ���ؿͼ�
			// ��ǰ ȯ�� ���̺��� �⺻Ű�� ��ǰ ȯ�� �ڵ忡 �ش��ϴ� ��ǰ ȯ�� ������ ������Ʈ �ϴ� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, retfolist.get(i));	// ��ǰ ȯ�� ���̺� ��ǰ ȯ�� �ڵ�
			pstmt.setString(2, retfolist.get(i));
			// ��ǰȯ�����̺��� ��ǰȯ�� �ڵ带 �ֹ� �ڵ忡 ����
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		}
		
		
		// ��ǰ ȯ�� ���̺��� ��ǰ ���� ��ŭ ȸ���Ѵ�.
		for(int i=0; i<pcodelist.size(); i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund as ref "
				+ "set ref.o_unit_price = "
				+ "(select p_price from product where p_code = ?) "
				+ "where ref.p_code = ?";
			// ��ǰ ���̺��� ��ǰ�ڵ�� ��ǰ ������ ���ؿͼ� ��ǰ ȯ�� ���̺�
			// �ش� ��ǰ �ڵ带 ���� ��ǰ���� ���� ������ ������Ʈ�ϴ� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pcodelist.get(i));	// ��ǰ ȯ�� ���̺��� ��ǰ �ڵ�
			pstmt.setString(2, pcodelist.get(i));
			// ��ǰ ���̺��� ��ǰ �ڵ带 ��ǰ ȯ�� ���̺��� ��ǰ �ڵ忡 ����
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   amountUnitPricePaymentUpdate()   Retrun_refundDao.java");
		}
		
		// ��ǰ ȯ�� ���̺��� ���ڵ� ������ŭ ȸ���Ѵ�.
		for(int i=0; i<retRefCount; i++) {
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			
			sql = "update retrun_refund "
				+ "set return_payment =(o_amount*o_unit_price) "
				+ "where retf_o_code = ?";
			// ���ڵ� �ϳ��� �ϳ��� ȯ�ұݾ��� ������Ʈ �ϴ� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, retfolist.get(i));
		}
		
		if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		// ��ǰ ���̺��� ��ǰ ������ŭ �ݺ����� ȸ���� ��ġ���� pstmt ���ҽ��� �Ѱ� ������ �ݾ��ش�.
	}
	
	
	// ��ǰ ȯ�� ���̺� �� ��ȸ
	public Map<String, Object> returnRefundDetail(String retf_o_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// ��ǰ ���̺��� ��ǰ��, ��ǰ ȯ�� ���̺� �ִ� ������ ����Ʈ ���·�
		// �����ϱ� ���ؼ� map ������ �����Ѵ�.
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select pro.p_name, pro.p_cate, ref.* "
					   + "from product as pro inner join retrun_refund as ref on pro.p_code = ref.p_code "
					   + "where retf_o_code = ?";
			// ��ǰ ���̺��� ��ǰ �̸�, ī�װ���,
			// ��ǰȯ�� ���̺��� ��ǰȯ�� �ڵ�, ��ǰ �ڵ�, ������ ���̵�, ȯ�� �ݾ���
			// ��ǰ ���̺��� ������ pro�� ��ǰ ȯ�� ���̺��� ������ ref�� �ϰ�
			// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ ȯ�� ���̺��� ��ǰ �ڵ尡 ���� ����� ������� �����ϴ� ���� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, retf_o_code);		// ��ǰ ȯ�� �ֹ��ڵ�
			System.out.println(pstmt + "<-- pstmt   returnRefundDetail()   Retrun_refundDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   returnRefundDetail()   Retrun_refundDao.java");
			
			map = new HashMap<String, Object>();
			
			Product pro = new Product();
			Retrun_refund ref = new Retrun_refund();
			
			while(rs.next()) {
				pro.setP_name(rs.getString("p_name"));					// ��ǰ �̸�
				pro.setP_cate(rs.getString("p_cate"));					// ī�װ�
				
				ref.setRetf_o_code(rs.getString("retf_o_code"));		// ��ǰ ȯ�� �ڵ�
				ref.setP_code(rs.getString("p_code"));					// ��ǰ �ڵ�
				ref.setSeller_id(rs.getString("seller_id"));			// �Ǹ��� ���̵�
				ref.setBuyer_id(rs.getString("buyer_id"));				// ������ ���̵�
				ref.setO_amount(rs.getInt("o_amount"));					// �ֹ� ����
				ref.setO_unit_price(rs.getInt("o_unit_price"));			// ���� ����
				ref.setReturn_payment(rs.getInt("return_payment"));		// ȯ�� �ݾ�
			}
			
			map.put("pro", pro);			// ��ǰ ��ü ����
			map.put("ref", ref);			// ��ǰ ȯ�� ��ü ����
			
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
