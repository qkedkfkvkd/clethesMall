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
	
	//��ü �ֹ� ��Ȳ ����Ʈ
	public List<Pro_order> proOrderAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pro_order> prlist = null;
		// ��ü �ֹ� ��Ȳ ����Ʈ�� �����ϱ� ���� ����Ʈ ������ �����Ѵ�.
		
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			unitPricePaymentUpdate(conn);
			// Ŀ�ؼ� ��ü�� �Ű������� �޾Ƽ� ���� �ݾװ� ���� �ݾ���
			// ������Ʈ �����ִ� ����ó�� �޼ҵ� ȣ��
			
			String sql = "select o_code, buyer_id, p_code, o_amount, "
					   + "o_unit_price, o_payment from pro_order";
			// �ֹ� �ڵ�, ������ ���̵�, ��ǰ �ڵ�, �ֹ� ����,
			// ���� ����, ���� �ݾ��� ��ȯ ���� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   proOrderAllList()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   proOrderAllList()   Pro_orderDao.java");
			
			prlist = new ArrayList<Pro_order>();
			
			while(rs.next()) { // ������� �ִٸ�
				Pro_order pro = new Pro_order();				// �ֹ� VO ��ü ����
				pro.setO_code(rs.getString("o_code"));			// �ֹ� �ڵ�
				pro.setBuyer_id(rs.getString("buyer_id"));		// ������ ���̵�
				pro.setP_code(rs.getString("p_code"));			// ��ǰ �ڵ�
				pro.setO_amount(rs.getInt("o_amount"));			// �ֹ� ����
				pro.setO_unit_price(rs.getInt("o_unit_price"));	// ���� ����
				pro.setO_payment(rs.getInt("o_payment"));		// ���� �ݾ�
				
				prlist.add(pro);
				// DB�κ��� �̾Ƴ� ���� ������ ��ü�� ����Ʈ�� �߰��Ѵ�.
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
	
	
	// (����ó��)
	// ��ǰ�ڵ带 �̿��Ͽ� ���� ������ ������Ʈ ��Ű��
	// �ֹ� ������ ���� ������ ���Ͽ� ���� �ݾ��� ������Ʈ ��Ų��.
	private void unitPricePaymentUpdate(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pro_order> prlist = new ArrayList<Pro_order>();
		// �ֹ� �ڵ�, ��ǰ �ڵ�, �ֹ� ������ ���� ����Ʈ ����	
		
		String sql = "select o_code, p_code, o_amount from pro_order";
		// �ֹ� �ڵ�� ��ǰ �ڵ�, �ֹ� ������ ��������� �޴� ���� �ۼ�
		
		pstmt = conn.prepareStatement(sql);
		System.out.println(pstmt + "<-- pstmt   unitPricePaymentUpdate()   Pro_orderDao.java");
		
		rs = pstmt.executeQuery();
		System.out.println(rs + "<-- rs   unitPricePaymentUpdate()   Pro_orderDao.java");
		
		
		while(rs.next()) { // ������� �ִٸ�
			Pro_order pro = new Pro_order();
			pro.setO_code(rs.getString("o_code"));		// �ֹ� �ڵ�
			pro.setP_code(rs.getString("p_code"));		// ��ǰ �ڵ�
			pro.setO_amount(rs.getInt("o_amount"));		// �ֹ� ����
			
			prlist.add(pro);
			// DB�κ��� �̾Ƴ� ���� ������ ��ü�� ����Ʈ�� �߰��Ѵ�.
		}
		
		
		for(int i=0; i<prlist.size(); i++) { // �ֹ� ���̺��� ���ڵ� ������ŭ ������.
			
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			// �ֹ� ���̺��� ���ڵ� ���� ��ŭ �������� ������ �ϱ� ������
			// �ݺ����� ���ۺ��� �ѹ� �ݾ��� �� ���ڵ� ������ŭ �����ش�.
			
			sql = "select p_price from prduct where p_code =?";
			// ��ǰ ���̺��� ��ǰ�ڵ带 �̿��Ͽ� ������ �̾Ƴ��� �������� �ۼ��Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, (prlist.get(i)).getP_code());
			// �ֹ� ���̺��� ù��° ��ǰ �ڵ���� ������� ������.
			
			System.out.println(pstmt + "<-- pstmt   unitPricePaymentUpdate()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   unitPricePaymentUpdate()   Pro_orderDao.java");
			
			int p_price = 0;
			// ��ǰ ������ ������ ����
			
			if(rs.next()) { // ���� �ִٸ�
				p_price = rs.getInt("p_price"); // �̾Ƴ� ��ǰ ������ ������ �����Ѵ�.
			}
			
			int payment = p_price * (prlist.get(i)).getO_amount();
			//  ���� �ݾ� =   ���� ���� * �ֹ� ����
			
			try { rs.close(); } catch(SQLException ex) {}
			try { pstmt.close(); } catch(SQLException ex) {}
			// update�� ���Ѿ��ϱ� ������ �ѹ� �ݾ���� �Ѵ�.
			
			sql = "UPDATE pro_order SET o_unit_price=?, o_payment=? WHERE o_code=?";
			// �ش� �ֹ� �ڵ��� ���� ����, ���� �ݾ��� ������Ʈ ������ �������� �ۼ��Ѵ�.
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_price);	// ��ǰ ���̺�κ��� �̾Ƴ� ���� ����
			pstmt.setInt(2, payment);	// ���� ���ݰ� �ֹ� ������ ���� ���� �ݾ�
			pstmt.setString(3, (prlist.get(i)).getO_code());	// �ֹ� �ڵ�
			
			int result = pstmt.executeUpdate();
			System.out.println(result + "<-- result   unitPricePaymentUpdate()   Pro_orderDao.java");
			
		} // for end
	}
	
	
	// ȸ�� �ֹ� �� ��ȸ
	public Pro_order proOrderDetail(String o_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Pro_order pro = new Pro_order();
		// �ֹ� �� ������ ������ ��ü ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			pstmt = conn.prepareStatement("select * from pro_order where o_code=?");
			// �Ű������� ���� �ֹ� �ڵ�� �ֹ� ���̺��� ��ȸ�Ѵ�.
			
			pstmt.setString(1, o_code);	// �ֹ� �ڵ� ����
			System.out.println(pstmt + "<-- pstmt   proOrderDetail()   Pro_orderDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   proOrderDetail()   Pro_orderDao.java");
			
			if(rs.next()) {	// ���� �ִٸ�
				pro.setO_code(rs.getString("o_code"));				// �ֹ� �ڵ�
				pro.setBuyer_id(rs.getString("buyer_id"));			// ������ ���̵�
				pro.setP_code(rs.getString("p_code"));				// ��ǰ �ڵ�
				pro.setO_amount(rs.getInt("o_amount"));				// �ֹ� ����
				pro.setO_unit_price(rs.getInt("o_unit_price"));		// ���� ����
				pro.setO_payment(rs.getInt("o_payment"));			// �ֹ� ����
				pro.setO_get_addr(rs.getString("o_get_addr"));		// �޴� ��� �ּ�
				pro.setO_get_call(rs.getString("o_get_call"));		// �޴� ��� ��ȣ
				pro.setO_send_name(rs.getString("o_send_name"));	// ������ ��� �̸�
				pro.setO_send_call(rs.getString("o_send_call"));	// ������ ��� ��ȣ
				pro.setO_date(rs.getString("o_date"));				// �ֹ� ����
				pro.setO_message(rs.getString("o_message"));		// ��� �޼���
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
