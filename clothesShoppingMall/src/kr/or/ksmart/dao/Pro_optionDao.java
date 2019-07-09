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
	
	// 전체 상품 리스트 조회 (상품 테이블, 상품 옵션 테이블 조인)
	public Map<String, Object> productOpAllList() throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// 상품 테이블과 상품 옵션 테이블을 리스트 형태로 리턴하기 위해서 map 변수를 선언한다.
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 얻어온다.
			
			String sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
					   + "prop.op_gender, prop.op_color, prop.pro_op_code "
					   + "from product as pro inner join pro_option as prop on pro.p_code = prop.p_code";
			// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격을,
			// 상품 옵션 테이블에서 옷 성별, 색상, 옵션 코드를
			// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
			// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같을 경우의 결과만을 추출하는 조인 쿼리문 작성
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt + "<-- pstmt   productOpAllList()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpAllList()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			List<Product> prolist = new ArrayList<Product>();			// 상품 객체 리스트 선언
			List<Pro_option> proplist = new ArrayList<Pro_option>();	// 상품 옵션 객체 리스트 선언
			
			while(rs.next()) {
				Product pro = new Product();			// 상품 VO 객체 선언
				Pro_option prop = new Pro_option();		// 상품 옵션 VO 객체 선언
																	// 상품 테이블
				pro.setP_code(rs.getString("p_code"));				// 상품 코드
				pro.setSeller_id(rs.getString("seller_id"));		// 판매자 아이디
				pro.setP_name(rs.getString("p_name"));				// 상품 이름
				pro.setP_price(rs.getInt("p_price"));				// 상품 가격
				
																	// 상품 옵션 테이블
				prop.setOp_gender(rs.getString("op_gender"));		// 옷 성별
				prop.setOp_color(rs.getString("op_color"));			// 색상
				prop.setPro_op_code(rs.getString("pro_op_code"));	// 옵션 코드
				
				prolist.add(pro);		// DB로부터 뽑아낸 상품 정보를 하나씩 저장한다.
				proplist.add(prop);		// DB로부터 뽑아낸 상품 옵션 정보를 하나씩 저장한다.
			}
			
			map.put("prolist", prolist);	// 상품 리스트를 "prolist"의 이름으로 저장한다.
			map.put("proplist", proplist);	// 상품 옵션 리스트를 "proplist"의 이름으로 저장한다.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// 상세 상품 내용 조회 (상품 테이블, 상품 옵션 테이블 조인)
	public Map<String, Object> productOpDetail(String pro_op_code) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// 상품 테이블과 상품 옵션 테이블을 각각의 객체 형태로 리턴하기 위해서 map 변수를 선언한다.
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 받아온다.
			
			String sql = "select pro.*, prop.pro_op_code, prop.op_gender, prop.op_color, prop.op_size "
					   + "from product as pro inner join pro_option as prop "
					   + "on pro.p_code = prop.p_code and prop.pro_op_code = ?";
			// 상품 테이블의 모든 레코드와 상품 옵션 테이블에서 옵션 코드, 옷 성별, 색상, 사이즈를
			// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
			// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같고 특정 상품 옵션 코드의 결과만을 추출하는 조인 쿼리문 작성 (레코드 한 줄만 나온다.)
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pro_op_code);	// 상품 옵션 코드 삽입
			System.out.println(pstmt + "<-- pstmt   productOpDetail()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpDetail()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			Product pro = new Product();			// map 객체에 넣기 위한 상품 VO 변수
			Pro_option prop = new Pro_option();		// map 객체에 넣기 위한 상품 옵션 VO 변수
			
			if(rs.next()) {											// 상품 테이블
				pro.setP_code(rs.getString("p_code"));				// 상품 코드
				pro.setSeller_id(rs.getString("seller_id"));		// 판매자 아이디
				pro.setP_name(rs.getString("p_name"));				// 상품 이름
				pro.setP_cate(rs.getString("p_cate"));				// 카테고리
				pro.setP_price(rs.getInt("p_price"));				// 가격
				pro.setP_date(rs.getString("p_date"));				// 등록일
				pro.setP_desc(rs.getString("p_desc"));				// 상세 설명
				
																	// 상품 옵션 테이블
				prop.setPro_op_code(rs.getString("pro_op_code"));	// 상품 옵션 코드
				prop.setOp_gender(rs.getString("op_gender"));		// 옷 성별
				prop.setOp_color(rs.getString("op_color"));			// 색상
				prop.setOp_size(rs.getInt("op_size"));				// 사이즈
			}
			
			map.put("product", pro);		// DB로부터 뽑아낸 상품 정보를 저장한다
			map.put("pro_option", prop);	// DB로부터 뽑아낸 상품 옵션 정보를 저장한다.
			
		} catch(SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return map;
	}
	
	
	// 상세 상품 검색 결과 리스트 (상품 테이블, 상품 옵션 테이블 조인)
	public Map<String, Object> productOpSearchList(Pro_option prop)
			throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Object> map = null;
		// 상품 테이블과 상품 옵션 테이블의 검색 결과를 각각의 객체 리스트 형태로 리턴하기 위해서 map 변수를 선언한다.
		try{
			conn = DriverDb.driverDBcon();
			// 커넥션 객체를 받아온다.
			
			String sql = "";
			
			// 옷 성별만 작성하고 색상과 사이즈는 공백으로 하고 검색한 경우
			if(!prop.getOp_gender().equals("") & prop.getOp_color().equals("") &
				prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
					+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
					+ "from product as pro inner join pro_option as prop "
					+ "on pro.p_code = prop.p_code and prop.op_gender = ?";
				// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격을,
				// 상품 옵션 테이블에서 옷 성별, 색상, 옵션 코드를
				// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
				// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같고 상품 옵션 테이블에서 옷 성별의 결과만을 추출하는 조인 쿼리문 작성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prop.getOp_gender());	// 옷 성별 삽입
				
				// 색상만 작성하고 옷 성별과 최소 사이즈는 공백하고 하고 검색한 경우
			} else if(prop.getOp_gender().equals("") & !prop.getOp_color().equals("") &
					  prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code and prop.op_color = ?";
				// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격을,
				// 상품 옵션 테이블에서 옷 성별, 색상, 옵션 코드를
				// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
				// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같고 상품 옵션 테이블에서 옷 색상의 결과만을 추출하는 조인 쿼리문 작성
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, prop.getOp_color());		// 옷 색상 삽입
				
				// 최소 사이즈를 작성하고 옷 성별과 색상은 공백으로 하고 검색한 경우
			} else if(prop.getOp_gender().equals("") & prop.getOp_color().equals("") &
					  !prop.getOp_size_min().equals("")) {
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code "
						+ "and prop.op_size between ? and ?";
				// 상품 테이블에서 상품 코드, 판매자 아이디, 상품 이름, 상품 가격을,
				// 상품 옵션 테이블에서 옷 성별, 색상, 옵션 코드를
				// 상품 테이블의 별명을 pro로 상품 옵션 테이블의 별명을 prop로 하고
				// 상품 테이블의 상품 코드와 상품 옵션 테이블의 상품 코드가 같고 상품 옵션 테이블에서 옷 사이즈의 특정 구간을 추출하는 조인 쿼리문 작성
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(prop.getOp_size_min()));	// 최소 사이즈
				pstmt.setInt(2, Integer.parseInt(prop.getOp_size_max()));	// 최대 사이즈
				
			} else { // 에러 막기용
				System.out.println("에러막기용 -   productOpSearchList()   Pro_optionDao.java");
				sql = "select pro.p_code, pro.seller_id, pro.p_name, pro.p_price, "
						+ "prop.op_gender, prop.op_color, prop.op_size, prop.pro_op_code "
						+ "from product as pro inner join pro_option as prop "
						+ "on pro.p_code = prop.p_code";
				// 모든 조건을 전부 적용하려면 조건절만 보기싫게 길어지게 되므로 두 개 이상을 입력하고 검색 버튼을 눌렀을 시
				// 그냥 전체 리스트를 보여주도록 처리하였다.
				pstmt = conn.prepareStatement(sql);
			}
			System.out.println(pstmt + "<-- pstmt   productOpSearchList()   Pro_optionDao.java");
			
			rs = pstmt.executeQuery();
			System.out.println(rs + "<-- rs   productOpSearchList()   Pro_optionDao.java");
			
			map = new HashMap<String, Object>();
			
			List<Product> prolist = new ArrayList<Product>();			// 상품쪽 객체들을 저장할 리스트 선언
			List<Pro_option> proplist = new ArrayList<Pro_option>();	// 상품 옵션 쪽 객체들을 저장할 리스트 선언
			
			while(rs.next()) { // 값이 있다면
				Product pro = new Product();				// 상품 VO 객체 선언
				Pro_option pro_op = new Pro_option();		// 상품 옵션 VO 객체 선언
																// 상품 테이블
				pro.setP_code(rs.getString("p_code"));			// 상품 코드
				pro.setSeller_id(rs.getString("seller_id"));	// 판매자 아이디
				pro.setP_name(rs.getString("p_name"));			// 상품 이름
				pro.setP_price(rs.getInt("p_price"));			// 상품 가격
				
																		// 상품 옵션 테이블
				pro_op.setPro_op_code(rs.getString("pro_op_code"));		// 상품 옵션 코드
				pro_op.setOp_gender(rs.getString("op_gender"));			// 옷 성별
				pro_op.setOp_color(rs.getString("op_color"));			// 색상
				pro_op.setOp_size(rs.getInt("op_size"));				// 사이즈
				
				prolist.add(pro);		// DB에서 뽑아낸 상품 내용 저장
				proplist.add(pro_op);	// DB에서 뽑아낸 상품 옵션 내용 저장
			}
			
			map.put("prolist", prolist);		// 상품 리스트 "prolist" 이름으로 저장
			map.put("proplist", proplist);		// 상품 옵션 리스트 "proplist" 이름으로 저장
			
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
