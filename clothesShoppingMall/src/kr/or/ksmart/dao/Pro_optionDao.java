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
import kr.or.ksmart.dto.Pro_option;
import kr.or.ksmart.dto.Product;

public class Pro_optionDao {
	
	// ��ü ��ǰ ����Ʈ ��ȸ (��ǰ ���̺�, ��ǰ �ɼ� ���̺� ����)
	public Map<String, Object> productOpAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// ��ǰ ���̺�� ��ǰ �ɼ� ���̺��� ����Ʈ ���·� �����ϱ� ���ؼ� map ������ �����Ѵ�.
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� ���´�.
			
			String sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
					   + "prop.op_gender, prop.op_color, prop.pro_op_code "
					   + "from product as pro inner join pro_option as prop on pro.p_code = prop.p_code";
			// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ������,
			// ��ǰ �ɼ� ���̺��� �� ����, ����, �ɼ� �ڵ带
			// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
			// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� ����� ������� �����ϴ� ���� ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   productOpAllList()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpAllList()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			List<Product> prolist = new ArrayList<Product>();			// ��ǰ ��ü ����Ʈ ����
			List<Pro_option> proplist = new ArrayList<Pro_option>();	// ��ǰ �ɼ� ��ü ����Ʈ ����
			
			while(rs.next()) {
				Product pro = new Product();			// ��ǰ VO ��ü ����
				Pro_option prop = new Pro_option();		// ��ǰ �ɼ� VO ��ü ����
																	// ��ǰ ���̺�
				pro.setP_code(rs.getString("p_code"));				// ��ǰ �ڵ�
				pro.setSeller_id(rs.getString("seller_id"));		// �Ǹ��� ���̵�
				pro.setP_name(rs.getString("p_name"));				// ��ǰ �̸�
				pro.setP_price(rs.getInt("p_price"));				// ��ǰ ����
				
																	// ��ǰ �ɼ� ���̺�
				prop.setOp_gender(rs.getString("op_gender"));		// �� ����
				prop.setOp_color(rs.getString("op_color"));			// ����
				prop.setPro_op_code(rs.getString("pro_op_code"));	// �ɼ� �ڵ�
				
				prolist.add(pro);		// DB�κ��� �̾Ƴ� ��ǰ ������ �ϳ��� �����Ѵ�.
				proplist.add(prop);		// DB�κ��� �̾Ƴ� ��ǰ �ɼ� ������ �ϳ��� �����Ѵ�.
			}
			
			map.put("prolist", prolist);	// ��ǰ ����Ʈ�� "prolist"�� �̸����� �����Ѵ�.
			map.put("proplist", proplist);	// ��ǰ �ɼ� ����Ʈ�� "proplist"�� �̸����� �����Ѵ�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// �� ��ǰ ���� ��ȸ (��ǰ ���̺�, ��ǰ �ɼ� ���̺� ����)
	public Map<String, Object> productOpDetail(String pro_op_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// ��ǰ ���̺�� ��ǰ �ɼ� ���̺��� ������ ��ü ���·� �����ϱ� ���ؼ� map ������ �����Ѵ�.
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� �޾ƿ´�.
			
			String sql = "select pro.*, prop.pro_op_code, prop.op_gender, prop.op_color, prop.op_size "
					   + "from product as pro inner join pro_option as prop "
					   + "on pro.p_code = prop.p_code and prop.pro_op_code = ?";
			// ��ǰ ���̺��� ��� ���ڵ�� ��ǰ �ɼ� ���̺��� �ɼ� �ڵ�, �� ����, ����, �����
			// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
			// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� Ư�� ��ǰ �ɼ� �ڵ��� ������� �����ϴ� ���� ������ �ۼ� (���ڵ� �� �ٸ� ���´�.)
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro_op_code);	// ��ǰ �ɼ� �ڵ� ����
			System.out.println(pstmt + "<-- pstmt   productOpDetail()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpDetail()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			Product pro = new Product();			// map ��ü�� �ֱ� ���� ��ǰ VO ����
			Pro_option prop = new Pro_option();		// map ��ü�� �ֱ� ���� ��ǰ �ɼ� VO ����
			
			if(rs.next()) {											// ��ǰ ���̺�
				pro.setP_code(rs.getString("p_code"));				// ��ǰ �ڵ�
				pro.setSeller_id(rs.getString("seller_id"));		// �Ǹ��� ���̵�
				pro.setP_name(rs.getString("p_name"));				// ��ǰ �̸�
				pro.setP_cate(rs.getString("p_cate"));				// ī�װ�
				pro.setP_price(rs.getInt("p_price"));				// ����
				pro.setP_date(rs.getString("p_date"));				// �����
				pro.setP_desc(rs.getString("p_desc"));				// �� ����
				
																	// ��ǰ �ɼ� ���̺�
				prop.setPro_op_code(rs.getString("pro_op_code"));	// ��ǰ �ɼ� �ڵ�
				prop.setOp_gender(rs.getString("op_gender"));		// �� ����
				prop.setOp_color(rs.getString("op_color"));			// ����
				prop.setOp_size(rs.getInt("op_size"));				// ������
			}
			
			map.put("product", pro);		// DB�κ��� �̾Ƴ� ��ǰ ������ �����Ѵ�
			map.put("pro_option", prop);	// DB�κ��� �̾Ƴ� ��ǰ �ɼ� ������ �����Ѵ�.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// �� ��ǰ �˻� ��� ����Ʈ (��ǰ ���̺�, ��ǰ �ɼ� ���̺� ����)
	public Map<String, Object> productOpSearchList(Pro_option prop)
			throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// ��ǰ ���̺�� ��ǰ �ɼ� ���̺��� �˻� ����� ������ ��ü ����Ʈ ���·� �����ϱ� ���ؼ� map ������ �����Ѵ�.
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü�� �޾ƿ´�.
			
			String sql = "";
			
			// �� ������ �ۼ��ϰ� ����� ������� �������� �ϰ� �˻��� ���
			if(!prop.getOp_gender().equals("") & prop.getOp_color().equals("") &
				prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
					+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
					+ "from product as pro inner join pro_option as prop "
					+ "on pro.p_code = prop.p_code and prop.op_gender = ?";
				// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ������,
				// ��ǰ �ɼ� ���̺��� �� ����, ����, �ɼ� �ڵ带
				// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
				// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� ��ǰ �ɼ� ���̺��� �� ������ ������� �����ϴ� ���� ������ �ۼ�
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prop.getOp_gender());	// �� ���� ����
				
				// ���� �ۼ��ϰ� �� ������ �ּ� ������� �����ϰ� �ϰ� �˻��� ���
			} else if(prop.getOp_gender().equals("") & !prop.getOp_color().equals("") &
					  prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code and prop.op_color = ?";
				// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ������,
				// ��ǰ �ɼ� ���̺��� �� ����, ����, �ɼ� �ڵ带
				// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
				// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� ��ǰ �ɼ� ���̺��� �� ������ ������� �����ϴ� ���� ������ �ۼ�
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prop.getOp_color());		// �� ���� ����
				
				// �ּ� ����� �ۼ��ϰ� �� ������ ������ �������� �ϰ� �˻��� ���
			} else if(prop.getOp_gender().equals("") & prop.getOp_color().equals("") &
					  !prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code "
						+ "and prop.op_size between ? and ?";
				// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ������,
				// ��ǰ �ɼ� ���̺��� �� ����, ����, �ɼ� �ڵ带
				// ��ǰ ���̺��� ������ pro�� ��ǰ �ɼ� ���̺��� ������ prop�� �ϰ�
				// ��ǰ ���̺��� ��ǰ �ڵ�� ��ǰ �ɼ� ���̺��� ��ǰ �ڵ尡 ���� ��ǰ �ɼ� ���̺��� �� �������� Ư�� ������ �����ϴ� ���� ������ �ۼ�
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(prop.getOp_size_min()));	// �ּ� ������
				pstmt.setInt(2, Integer.parseInt(prop.getOp_size_max()));	// �ִ� ������
				
			} else { // ���� �����
				System.out.println("��������� -   productOpSearchList()   Pro_optionDao.java");
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code";
				// ��� ������ ���� �����Ϸ��� �������� ����Ȱ� ������� �ǹǷ� �� �� �̻��� �Է��ϰ� �˻� ��ư�� ������ ��
				// �׳� ��ü ����Ʈ�� �����ֵ��� ó���Ͽ���.
				pstmt = conn.prepareStatement(sql);
			}
			System.out.println(pstmt + "<-- pstmt   productOpSearchList()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpSearchList()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			List<Product> prolist = new ArrayList<Product>();			// ��ǰ�� ��ü���� ������ ����Ʈ ����
			List<Pro_option> proplist = new ArrayList<Pro_option>();	// ��ǰ �ɼ� �� ��ü���� ������ ����Ʈ ����
			
			while(rs.next()) { // ���� �ִٸ�
				Product pro = new Product();				// ��ǰ VO ��ü ����
				Pro_option pro_op = new Pro_option();		// ��ǰ �ɼ� VO ��ü ����
																// ��ǰ ���̺�
				pro.setP_code(rs.getString("p_code"));			// ��ǰ �ڵ�
				pro.setSeller_id(rs.getString("seller_id"));	// �Ǹ��� ���̵�
				pro.setP_name(rs.getString("p_name"));			// ��ǰ �̸�
				pro.setP_price(rs.getInt("p_price"));			// ��ǰ ����
				
																		// ��ǰ �ɼ� ���̺�
				pro_op.setPro_op_code(rs.getString("pro_op_code"));		// ��ǰ �ɼ� �ڵ�
				pro_op.setOp_gender(rs.getString("op_gender"));			// �� ����
				pro_op.setOp_color(rs.getString("op_color"));			// ����
				pro_op.setOp_size(rs.getInt("op_size"));				// ������
				
				prolist.add(pro);		// DB���� �̾Ƴ� ��ǰ ���� ����
				proplist.add(pro_op);	// DB���� �̾Ƴ� ��ǰ �ɼ� ���� ����
			}
			
			map.put("prolist", prolist);		// ��ǰ ����Ʈ "prolist" �̸����� ����
			map.put("proplist", proplist);		// ��ǰ �ɼ� ����Ʈ "proplist" �̸����� ����
			
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
