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
	
	// 전체 상품 리스트 조회
	public List<Product> productAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> plist = null;
		// 전체 상품 리스트 리턴을 위한 리스트 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체 받아오기
			
			String sql = "select p_code, seller_id, p_name, p_price from product";
			// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격 조회 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   productAllList()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productAllList()   ProductDao.java");
			
			plist = new ArrayList<Product>();
			
			while(rs.next()) { // 값이 있다면
				Product pro = new Product();				// 상품 VO 변수 선언
				pro.setP_code(rs.getString("p_code"));			// 상품 코드
				pro.setSeller_id(rs.getString("seller_id"));	// 판매자 아이디
				pro.setP_name(rs.getString("p_name"));			// 상품 이름
				pro.setP_price(rs.getInt("p_price"));			// 가격
				plist.add(pro);		// DB에서 뽑아낸 상품 정보 하나씩 리스트 추가
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
	
	
	// 상품 상세 조회
	public Product productDetail(String p_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product pro = new Product();
		// 상품 정보를 리턴할 VO 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체 받아오기
			
			pstmt = conn.prepareStatement("select * from product where p_code=?");
			// 매개변수로 받은 상품 코드로 상품 테이블에서 조회 쿼리문 작성
			
			pstmt.setString(1, p_code);	// 상품 코드 삽입
			System.out.println(pstmt + "<-- pstmt   productDetail()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productDetail()   ProductDao.java");
			
			if(rs.next()) {
				pro.setP_code(rs.getString("p_code"));			// 상품 코드
				pro.setSeller_id(rs.getString("seller_id"));	// 판매자 아이디
				pro.setP_name(rs.getString("p_name"));			// 상품 이름
				pro.setP_cate(rs.getString("p_cate"));			// 카테고리
				pro.setP_price(rs.getInt("p_price"));			// 가격
				pro.setP_date(rs.getString("p_date"));			// 등록 일자
				pro.setP_desc(rs.getString("p_desc"));			// 상세 설명
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
	
	// 검색 상품 리스트 조회
	public List<Product> productSearchList(Product pro) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> plist = null;
		// 검색된 상품 리스트 리턴할 리스트 변수 선언
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체 받아오기
			
			String sql = "";
			
			
			if(pro.getOrderby().equals("") & pro.getSort().equals("")) {
			   // 정렬 기준과 정렬 방식을 입력하지 않고 검색했을 시
				
				if(!pro.getP_name().equals("") & pro.getP_price_min().equals("") &
					pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
					// 상품명만 입력하고 최소 가격, 최소 등록 일자, 카테고리를 입력하지 않고 검색 시
					
					sql = "select * from product where p_name like ?";
					// 상품명 글자를 포함해서 검색하는 쿼리문 작성
					String pname_temp = "%" +  pro.getP_name() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pname_temp);		// %상품명% 입력
					
				} else if(pro.getP_name().equals("") & !pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // 최소 가격를 입력하고 상품명, 최소 등록일, 카테고리를 입력하지 않고 검색 시
					
					sql ="select * from product where p_price between ? and ?";
					// 최소 가격 ~ 최대 가격 구간 사이의 상품을 구하는 쿼리문 작성
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pro.getP_price_min()));	// 최소 가격
					pstmt.setInt(2, Integer.parseInt(pro.getP_price_max()));	// 최대 가격
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  !pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // 최소 등록 일자를 입력하고 상품명, 최소 가격, 카테고리를 입력하지 않고 검색 시
					
					sql ="select * from product where p_date between ? and ?";
					// 최소 등록일자 ~ 최대 등록 일자 구간 사이의 상품을 구하는 쿼리문 작성
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pro.getP_date_min());	// 최소 등록일자
					pstmt.setString(2, pro.getP_date_max());	// 최대 등록일자
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & !pro.getP_cate().equals("")) {
						  // 카테고리만 입력하고 상품명, 최소 가격, 최소 등록일자를 입력하지 않고 검색 시
					
					sql = "select * from product where p_cate like ?";
					// 카테고리 글자를 포함해서 검색하는 쿼리문 작성
					
					String pcate_temp = "%" + pro.getP_cate() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pcate_temp);		// %카테고리% 삽입
					
				} else { // 에러 막기용
					System.out.println("에러막기용01 -   productSearchList()   ProductDao.java");
					sql = "select * from product";
					// 에러를 막고자 두 개 이상을 입력하고 검색 시 전체 리스트를 조회하는 쿼리문 작성
					
					pstmt = conn.prepareStatement(sql);
				}
			} else { // 정렬 기준과 정렬 방식을 입력하고 검색했을 시
				if(!pro.getP_name().equals("") & pro.getP_price_min().equals("") &
					pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
					// 상품명만 입력하고 최소 가격, 최소 등록 일자, 카테고리를 입력하지 않고 검색 시
					
					sql = "select * from product where p_name like ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// 상품명 글자를 포함해서 정렬하는 쿼리문 작성
					
					String pname_temp = "%" +  pro.getP_name() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pname_temp);		// %상품명% 입력
						
				} else if(pro.getP_name().equals("") & !pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // 최소 가격를 입력하고 상품명, 최소 등록일, 카테고리를 입력하지 않고 검색 시
					
					sql = "select * from product where p_price between ? and ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// 최소 가격 ~ 최대 가격 구간 사이의 상품을 정렬해서 구하는 쿼리문 작성
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(pro.getP_price_min()));	// 최소 가격
					pstmt.setInt(2, Integer.parseInt(pro.getP_price_max()));	// 최대 가격
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  !pro.getP_date_min().equals("") & pro.getP_cate().equals("")) {
						  // 최소 등록 일자를 입력하고 상품명, 최소 가격, 카테고리를 입력하지 않고 검색 시
					
					sql = "select * from product where p_date between ? and ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// 최소 등록일자 ~ 최대 등록 일자 구간 사이의 상품을 정렬해서 구하는 쿼리문 작성
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pro.getP_date_min());	// 최소 등록일자
					pstmt.setString(2, pro.getP_date_max());	// 최대 등록일자
					
				} else if(pro.getP_name().equals("") & pro.getP_price_min().equals("") &
						  pro.getP_date_min().equals("") & !pro.getP_cate().equals("")) {
						  // 카테고리만 입력하고 상품명, 최소 가격, 최소 등록일자를 입력하지 않고 검색 시
					
					sql = "select * from product where p_cate like ? order by "
						  + pro.getOrderby() + " " + pro.getSort();
					// 카테고리 글자를 포함하고 정렬해서 검색하는 쿼리문 작성
					
					String pcate_temp = "%" + pro.getP_cate() + "%";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, pcate_temp);			// %카테고리% 삽입
					
				} else { // 에러 막기용
					System.out.println("에러막기용02 -   productSearchList()   ProductDao.java");
					sql = "select * from product order by p_price desc";
					// 에러를 막고자 두 개 이상을 입력하고 검색 시 가장 많이 검색한다고 생각되는
					// 가격을 내림차순으로 하여 전체 리스트를 조회하는 쿼리문 작성
					pstmt = conn.prepareStatement(sql);
				}
			}
			System.out.println(pstmt + "<-- pstmt   productSelectList()   ProductDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productSelectList()   ProductDao.java");
			
			plist = new ArrayList<Product>();
			
			while(rs.next()) {	// 값이 있다면
				Product product = new Product();			// 상품 VO 객체 선언
				product.setP_code(rs.getString("p_code"));			// 상품 코드
				product.setSeller_id(rs.getString("seller_id"));	// 판매자 아이디
				product.setP_name(rs.getString("p_name"));			// 상품명
				product.setP_cate(rs.getString("p_cate"));			// 카테고리
				product.setP_price(rs.getInt("p_price"));			// 가격
				product.setP_date(rs.getString("p_date"));			// 등록일자
				plist.add(product);		// DB에서 뽑아낸 상품 정보를 리스트에 저장
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
