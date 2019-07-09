package kr.or.ksmart.dto;

// 주문 관리 VO
public class Pro_order {
	private String o_code;		// 주문 코드  (기본키)
	private String buyer_id;	// 구매자 아이디 (외래키 테이블:회원)
	private String p_code;		// 상품 코드 (외래키 테이블:상품)
	private int o_amount;		// 주문 수량
	private int o_unit_price;	// 개당 가격
	// 개당 가격으로 select 쿼리 이용하여 상품 테이블에서 상품코드로 가격 추출한다.
	private int o_payment;		// 결제 금액
	// 결제 금액으로 추출한 개당 가격과 주문 수량(o_amount)을 곱한다.
	private String o_get_addr;	// 받는 사람 주소
	private String o_get_call;	// 받는 사람 번호
	private String o_send_name;	// 보내는 사람 이름
	// 보내는 사람 이름 : select 쿼리 이용하여 상품코드로 판매자 이름 추출 (조인 쿼리)
	// 즉, 상품 테이블에서 상품코드로 판매자 아이디를 추출하여 판매자 아이디로 멤버 테이블의 이름을 검색한다.
	private String o_send_call;	// 보내는 사람 번호
	// 보내는 사람 번호 : 보내는 사람 이름과 동일하게 처리한다. 조인 쿼리 한개로 이름과 번호를 추출한다.
	private String o_date;		// 주문 일자
	private String o_message;	// 배송 메세지
	
	public String getO_code() {
		return o_code;
	}
	public void setO_code(String o_code) {
		System.out.println(o_code + " <- o_code   setO_code()   Pro_order.java");
		this.o_code = o_code;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		System.out.println(buyer_id + " <- buyer_id   setBuyer_id()   Pro_order.java");
		this.buyer_id = buyer_id;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		System.out.println(p_code + " <- p_code   setP_code()   Pro_order.java");
		this.p_code = p_code;
	}
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		System.out.println(o_amount + " <- o_amount   setO_amount()   Pro_order.java");
		this.o_amount = o_amount;
	}
	public int getO_unit_price() {
		return o_unit_price;
	}
	public void setO_unit_price(int o_unit_price) {
		System.out.println(o_unit_price + " <- o_unit_price   setO_unit_price()   Pro_order.java");
		this.o_unit_price = o_unit_price;
	}
	public int getO_payment() {
		return o_payment;
	}
	public void setO_payment(int o_payment) {
		System.out.println(o_payment + " <- o_payment   setO_payment()   Pro_order.java");
		this.o_payment = o_payment;
	}
	public String getO_get_addr() {
		return o_get_addr;
	}
	public void setO_get_addr(String o_get_addr) {
		System.out.println(o_get_addr + " <- o_get_addr   setO_get_addr()   Pro_order.java");
		this.o_get_addr = o_get_addr;
	}
	public String getO_get_call() {
		return o_get_call;
	}
	public void setO_get_call(String o_get_call) {
		System.out.println(o_get_call + " <- o_get_call   setO_get_call()   Pro_order.java");
		this.o_get_call = o_get_call;
	}
	public String getO_send_name() {
		return o_send_name;
	}
	public void setO_send_name(String o_send_name) {
		System.out.println(o_send_name + " <- o_send_name   setO_send_name()   Pro_order.java");
		this.o_send_name = o_send_name;
	}
	public String getO_send_call() {
		return o_send_call;
	}
	public void setO_send_call(String o_send_call) {
		System.out.println(o_send_call + " <- o_send_call   setO_send_call()   Pro_order.java");
		this.o_send_call = o_send_call;
	}
	public String getO_date() {
		return o_date;
	}
	public void setO_date(String o_date) {
		System.out.println(o_date + " <- o_date   setO_date()   Pro_order.java");
		this.o_date = o_date;
	}
	public String getO_message() {
		return o_message;
	}
	public void setO_message(String o_message) {
		System.out.println(o_message + " <- o_message   setO_message()   Pro_order.java");
		this.o_message = o_message;
	}
}
