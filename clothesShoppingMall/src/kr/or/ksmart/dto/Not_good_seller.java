package kr.or.ksmart.dto;

public class Not_good_seller {
	private String seller_id;			// �Ǹ��� ���̵�(�⺻Ű)
	private String request_buyer_id;	// ��û ������ ���̵� (�⺻Ű)
	private boolean treat_whether;		// ó�� ���� : ��񿡴� (0, 1)�� �����
	private String treat_reason;		// ó�� ����
	private String request_reason;		// ��û ����
	
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		System.out.println(seller_id + " <- seller_id   setSeller_id()   Not_good_seller.java");
		this.seller_id = seller_id;
	}
	public String getRequest_buyer_id() {
		return request_buyer_id;
	}
	public void setRequest_buyer_id(String request_buyer_id) {
		System.out.println(request_buyer_id + " <- request_buyer_id   setRequest_buyer_id()   Not_good_seller.java");
		this.request_buyer_id = request_buyer_id;
	}
	public boolean isTreat_whether() {
		return treat_whether;
	}
	public void setTreat_whether(boolean treat_whether) {
		System.out.println(treat_whether + " <- treat_whether   setTreat_whether()   Not_good_seller.java");
		this.treat_whether = treat_whether;
	}
	public String getTreat_reason() {
		return treat_reason;
	}
	public void setTreat_reason(String treat_reason) {
		System.out.println(treat_reason + " <- treat_reason   setTreat_reason()   Not_good_seller.java");
		this.treat_reason = treat_reason;
	}
	public String getRequest_reason() {
		return request_reason;
	}
	public void setRequest_reason(String request_reason) {
		System.out.println(request_reason + " <- request_reason   setRequest_reason()   Not_good_seller.java");
		this.request_reason = request_reason;
	}
}
