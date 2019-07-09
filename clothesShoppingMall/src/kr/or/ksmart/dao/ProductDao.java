package kr.or.ksmart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ksmart.driverdb.DriverDb;
import kr.or.ksmart.dto.Product;

public class ProductDao {
	
	// ��ü ��ǰ ����Ʈ ��ȸ
	public List<Product> productAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> plist = null;
		// ��ü ��ǰ ����Ʈ ������ ���� ����Ʈ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü �޾ƿ���
			
			String sql = "select p_code, seller_id, p_name, p_price from product";
			// ��ǰ ���̺��� ��ǰ �ڵ�, �Ǹ��� ���̵�, ��ǰ �̸�, ��ǰ ���� ��ȸ ������ �ۼ�
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   productAllList()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productAllList()   ProductDao.java");
			
			plist = new ArrayList<Product>();
			
			while(rs.next()) { // ���� �ִٸ�
				Product pro = new Product();				// ��ǰ VO ���� ����
				pro.setP_code(rs.getString("p_code"));			// ��ǰ �ڵ�
				pro.setSeller_id(rs.getString("seller_id"));	// �Ǹ��� ���̵�
				pro.setP_name(rs.getString("p_name"));			// ��ǰ �̸�
				pro.setP_price(rs.getInt("p_price"));			// ����
				plist.add(pro);		// DB���� �̾Ƴ� ��ǰ ���� �ϳ��� ����Ʈ �߰�
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return plist;
	}
	
	
	// ��ǰ �� ��ȸ
	public Product productDetail(String p_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product pro = new Product();
		// ��ǰ ������ ������ VO ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü �޾ƿ���
			
			pstmt = conn.prepareStatement("select * from product where p_code=?");
			// �Ű������� ���� ��ǰ �ڵ�� ��ǰ ���̺��� ��ȸ ������ �ۼ�
			
			pstmt.setString(1, p_code);	// ��ǰ �ڵ� ����
			System.out.println(pstmt + "<-- pstmt   productDetail()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productDetail()   ProductDao.java");
			
			if(rs.next()) {
				pro.setP_code(rs.getString("p_code"));			// ��ǰ �ڵ�
				pro.setSeller_id(rs.getString("seller_id"));	// �Ǹ��� ���̵�
				pro.setP_name(rs.getString("p_name"));			// ��ǰ �̸�
				pro.setP_cate(rs.getString("p_cate"));			// ī�װ�
				pro.setP_price(rs.getInt("p_price"));			// ����
				pro.setP_date(rs.getString("p_date"));			// ��� ����
				pro.setP_desc(rs.getString("p_desc"));			// �� ����
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
	
	// �˻� ��ǰ ����Ʈ ��ȸ
	public List<Product> productSearchList(Product pro) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> plist = null;
		// �˻��� ��ǰ ����Ʈ ������ ����Ʈ ���� ����
		try{
			conn = DriverDb.driverDBcon();
			// Ŀ�ؼ� ��ü �޾ƿ���
			
			String sql = "";
			
			
			if(pro.getOrderby().equals("") & pro.getSort().equals("")) {
			   // ���� ���ذ� ���� ����� �Է����� �ʰ� �˻����� ��
				
				if(!pro.getP_name().equals("") & pro.getP_price_min().equals("") &
					pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
					// ��ǰ�� �Է��ϰ� �ּ� ����, �ּ� ��� ����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_name like ?";
					// ��ǰ�� ���ڸ� �����ؼ� �˻��ϴ� ������ �ۼ�
					String pname_temp = "%" +  pro.getP_name() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pname_temp);		// %��ǰ��% �Է�
					
				} else if(pro.getP_name().equals("") & !pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // �ּ� ���ݸ� �Է��ϰ� ��ǰ��, �ּ� �����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql ="select * from product where p_price between ? and ?";
					// �ּ� ���� ~ �ִ� ���� ���� ������ ��ǰ�� ���ϴ� ������ �ۼ�
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pro.getP_price_min()));	// �ּ� ����
					pstmt.setInt(2, Integer.parseInt(pro.getP_price_max()));	// �ִ� ����
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  !pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // �ּ� ��� ���ڸ� �Է��ϰ� ��ǰ��, �ּ� ����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql ="select * from product where p_date between ? and ?";
					// �ּ� ������� ~ �ִ� ��� ���� ���� ������ ��ǰ�� ���ϴ� ������ �ۼ�
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pro.getP_date_min());	// �ּ� �������
					pstmt.setString(2, pro.getP_date_max());	// �ִ� �������
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & !pro.getP_cate().equals("")) {
						  // ī�װ��� �Է��ϰ� ��ǰ��, �ּ� ����, �ּ� ������ڸ� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_cate like ?";
					// ī�װ� ���ڸ� �����ؼ� �˻��ϴ� ������ �ۼ�
					
					String pcate_temp = "%" + pro.getP_cate() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pcate_temp);		// %ī�װ�% ����
					
				} else { // ���� �����
					System.out.println("���������01 -   productSearchList()   ProductDao.java");
					sql = "select * from product";
					// ������ ������ �� �� �̻��� �Է��ϰ� �˻� �� ��ü ����Ʈ�� ��ȸ�ϴ� ������ �ۼ�
					
					pstmt = conn.prepareStatement(sql);
				}
			} else { // ���� ���ذ� ���� ����� �Է��ϰ� �˻����� ��
				if(!pro.getP_name().equals("") & pro.getP_price_min().equals("") &
					pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
					// ��ǰ�� �Է��ϰ� �ּ� ����, �ּ� ��� ����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_name like ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// ��ǰ�� ���ڸ� �����ؼ� �����ϴ� ������ �ۼ�
					
					String pname_temp = "%" +  pro.getP_name() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pname_temp);		// %��ǰ��% �Է�
						
				} else if(pro.getP_name().equals("") & !pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // �ּ� ���ݸ� �Է��ϰ� ��ǰ��, �ּ� �����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_price between ? and ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// �ּ� ���� ~ �ִ� ���� ���� ������ ��ǰ�� �����ؼ� ���ϴ� ������ �ۼ�
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pro.getP_price_min()));	// �ּ� ����
					pstmt.setInt(2, Integer.parseInt(pro.getP_price_max()));	// �ִ� ����
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  !pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // �ּ� ��� ���ڸ� �Է��ϰ� ��ǰ��, �ּ� ����, ī�װ��� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_date between ? and ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// �ּ� ������� ~ �ִ� ��� ���� ���� ������ ��ǰ�� �����ؼ� ���ϴ� ������ �ۼ�
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pro.getP_date_min());	// �ּ� �������
					pstmt.setString(2, pro.getP_date_max());	// �ִ� �������
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & !pro.getP_cate().equals("")) {
						  // ī�װ��� �Է��ϰ� ��ǰ��, �ּ� ����, �ּ� ������ڸ� �Է����� �ʰ� �˻� ��
					
					sql = "select * from product where p_cate like ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// ī�װ� ���ڸ� �����ϰ� �����ؼ� �˻��ϴ� ������ �ۼ�
					
					String pcate_temp = "%" + pro.getP_cate() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pcate_temp);			// %ī�װ�% ����
					
				} else { // ���� �����
					System.out.println("���������02 -   productSearchList()   ProductDao.java");
					sql = "select * from product order by p_price desc";
					// ������ ������ �� �� �̻��� �Է��ϰ� �˻� �� ���� ���� �˻��Ѵٰ� �����Ǵ�
					// ������ ������������ �Ͽ� ��ü ����Ʈ�� ��ȸ�ϴ� ������ �ۼ�
					pstmt = conn.prepareStatement(sql);
				}
			}
			System.out.println(pstmt + "<-- pstmt   productSelectList()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productSelectList()   ProductDao.java");
			
			plist = new ArrayList<Product>();
			
			while(rs.next()) {	// ���� �ִٸ�
				Product product = new Product();			// ��ǰ VO ��ü ����
				product.setP_code(rs.getString("p_code"));			// ��ǰ �ڵ�
				product.setSeller_id(rs.getString("seller_id"));	// �Ǹ��� ���̵�
				product.setP_name(rs.getString("p_name"));			// ��ǰ��
				product.setP_cate(rs.getString("p_cate"));			// ī�װ�
				product.setP_price(rs.getInt("p_price"));			// ����
				product.setP_date(rs.getString("p_date"));			// �������
				plist.add(product);		// DB���� �̾Ƴ� ��ǰ ������ ����Ʈ�� ����
			}
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return plist;
	}
	
}
