package kr.or.ksmart.dto;

public class Retrun_refund {
	private String ret_ref_code;		// ��ǰ ȯ�� �ڵ� (�⺻Ű)
	private String o_code;				// �ֹ� �ڵ� (�⺻Ű)
	private String p_code;				// ��ǰ�ڵ� (�ܷ�Ű ���̺�:��ǰ)
	private String seller_id;			// �Ǹ��� ���̵� (�ܷ�Ű ���̺�:ȸ��)
	private String buyer_id;			// ������ ���̵� (�ܷ�Ű ���̺�:ȸ��)
	private int o_amount;				// �ֹ��ߴ� ����
	// �ֹ� ���� : �ֹ����� ���̺��� �ֹ��ڵ带 �̿��Ͽ� �ֹ� ������ select ���� �̿� �����Ѵ�.
	private int o_unit_price;			// ���� ����
	// ���� ���� : ��ǰ ���̺��� ��ǰ�ڵ带 �̿��Ͽ� ���� ������ select ���� �̿� �����Ѵ�.
	private int return_payment;			// ȯ�� �ݾ�
	// �ֹ� ������ ���� ������ ���� ����� �ִ´�.
	
	public String getRet_ref_code() {
		return ret_ref_code;
	}
	public void setRet_ref_code(String ret_ref_code) {
		System.out.println(ret_ref_code + " <- ret_ref_code   setRet_ref_code()   Retrun_refund.java");
		this.ret_ref_code = ret_ref_code;
	}
	public String getO_code() {
		return o_code;
	}
	public void setO_code(String o_code) {
		System.out.println(o_code + " <- o_code   setO_code()   Retrun_refund.java");
		this.o_code = o_code;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		System.out.println(p_code + " <- p_code   setP_code()   Retrun_refund.java");
		this.p_code = p_code;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		System.out.println(seller_id + " <- seller_id   setSeller_id()   Retrun_refund.java");
		this.seller_id = seller_id;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		System.out.println(buyer_id + " <- buyer_id   setBuyer_id()   Retrun_refund.java");
		this.buyer_id = buyer_id;
	}
	public int getO_amount() {
		return o_amount;
	}
	public void setO_amount(int o_amount) {
		System.out.println(o_amount + " <- o_amount   setO_amount()   Retrun_refund.java");
		this.o_amount = o_amount;
	}
	public int getO_unit_price() {
		return o_unit_price;
	}
	public void setO_unit_price(int o_unit_price) {
		System.out.println(o_unit_price + " <- o_unit_price   setO_unit_price()   Retrun_refund.java");
		this.o_unit_price = o_unit_price;
	}
	public int getReturn_payment() {
		return return_payment;
	}
	public void setReturn_payment(int return_payment) {
		System.out.println(return_payment + " <- return_payment   setReturn_payment()   Retrun_refund.java");
		this.return_payment = return_payment;
	}
}
