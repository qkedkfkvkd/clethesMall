package kr.or.ksmart.dto;

// 상품 관리 VO
public class Product {
	private String p_code;		// 상품 코드 (기본키)
	private String seller_id;	// 판매자 아이디 (외래키 테이블:회원)
	private String p_name;		// 상품 이름
	private String p_cate;		// 카테고리
	private int p_price;		// 가격
	private String p_date;		// 등록일
	private String p_desc;		// 상품 상세 정보
	
	// 검색 전용 변수들
	private String orderby;		// 정렬 기준 선택
	private String sort;		// 내림차순 / 오름차순 선택
	private String p_price_min;	// 가격 구간 최소 가격
	private String p_price_max;	// 가격 구간 최대 가격
	private String p_date_min;	// 등록일 구간 최소 날짜
	private String p_date_max;	// 등록일 구간 최대 날짜
	
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		System.out.println(p_code + " <- p_code   setP_code()   Product.java");
		this.p_code = p_code;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		System.out.println(seller_id + " <- seller_id   setSeller_id()   Product.java");
		this.seller_id = seller_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		System.out.println(p_name + " <- p_name   setP_name()   Product.java");
		this.p_name = p_name;
	}
	public String getP_cate() {
		return p_cate;
	}
	public void setP_cate(String p_cate) {
		System.out.println(p_cate + " <- p_cate   setP_cate()   Product.java");
		this.p_cate = p_cate;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		System.out.println(p_price + " <- p_price   setP_price()   Product.java");
		this.p_price = p_price;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		System.out.println(p_date + " <- p_date   setP_date()   Product.java");
		this.p_date = p_date;
	}
	public String getP_desc() {
		return p_desc;
	}
	public void setP_desc(String p_desc) {
		System.out.println(p_desc + " <- p_desc   setP_desc()   Product.java");
		this.p_desc = p_desc;
	}
	public String getOrderby() {
		return orderby;
	}
	public void setOrderby(String orderby) {
		System.out.println(orderby + " <- orderby   setOrderby()   Product.java");
		this.orderby = orderby;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		System.out.println(sort + " <- sort   setSort()   Product.java");
		this.sort = sort;
	}
	public String getP_price_min() {
		return p_price_min;
	}
	public void setP_price_min(String p_price_min) {
		System.out.println(p_price_min + " <- p_price_min   setP_price_min()   Product.java");
		this.p_price_min = p_price_min;
	}
	public String getP_price_max() {
		return p_price_max;
	}
	public void setP_price_max(String p_price_max) {
		System.out.println(p_price_max + " <- p_price_max   setP_price_max()   Product.java");
		this.p_price_max = p_price_max;
	}
	public String getP_date_min() {
		return p_date_min;
	}
	public void setP_date_min(String p_date_min) {
		System.out.println(p_date_min + " <- p_date_min   setP_date_min()   Product.java");
		this.p_date_min = p_date_min;
	}
	public String getP_date_max() {
		return p_date_max;
	}
	public void setP_date_max(String p_date_max) {
		System.out.println(p_date_max + " <- p_date_max   setP_date_max()   Product.java");
		this.p_date_max = p_date_max;
	}
}
