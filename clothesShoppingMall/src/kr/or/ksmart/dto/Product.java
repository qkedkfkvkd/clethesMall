package kr.or.ksmart.dto;

// ��ǰ ���� VO
public class Product {
	private String p_code;		// ��ǰ �ڵ� (�⺻Ű)
	private String seller_id;	// �Ǹ��� ���̵� (�ܷ�Ű ���̺�:ȸ��)
	private String p_name;		// ��ǰ �̸�
	private String p_cate;		// ī�װ�
	private int p_price;		// ����
	private String p_date;		// �����
	private String p_desc;		// ��ǰ �� ����
	
	// �˻� ���� ������
	private String orderby;		// ���� ���� ����
	private String sort;		// �������� / �������� ����
	private String p_price_min;	// ���� ���� �ּ� ����
	private String p_price_max;	// ���� ���� �ִ� ����
	private String p_date_min;	// ����� ���� �ּ� ��¥
	private String p_date_max;	// ����� ���� �ִ� ��¥
	
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
