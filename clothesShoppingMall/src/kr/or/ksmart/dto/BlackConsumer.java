package kr.or.ksmart.dto;

public class BlackConsumer {
	private String buyer_id;			// 구매자 아이디(기본키)
	private String request_seller_id;	// 요청 판매자 아이디 (기본키)
	private boolean treat_whether;		// 처리 여부 : 디비에는 (0, 1)로 저장됨
	private String treat_reason;		// 처리 사유
	private String request_reason;		// 요청 사유
	
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		System.out.println(buyer_id + " <- buyer_id   setBuyer_id()   BlackConsumer.java");
		this.buyer_id = buyer_id;
	}
	public String getRequest_seller_id() {
		return request_seller_id;
	}
	public void setRequest_seller_id(String request_seller_id) {
		System.out.println(request_seller_id + " <- request_seller_id   setRequest_seller_id()   BlackConsumer.java");
		this.request_seller_id = request_seller_id;
	}
	public boolean isTreat_whether() {
		return treat_whether;
	}
	public void setTreat_whether(boolean treat_whether) {
		System.out.println(treat_whether + " <- treat_whether   setTreat_whether()   BlackConsumer.java");
		this.treat_whether = treat_whether;
	}
	public String getTreat_reason() {
		return treat_reason;
	}
	public void setTreat_reason(String treat_reason) {
		System.out.println(treat_reason + " <- treat_reason   setTreat_reason()   BlackConsumer.java");
		this.treat_reason = treat_reason;
	}
	public String getRequest_reason() {
		return request_reason;
	}
	public void setRequest_reason(String request_reason) {
		System.out.println(request_reason + " <- request_reason   setRequest_reason()   BlackConsumer.java");
		this.request_reason = request_reason;
	}
}
