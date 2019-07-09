package kr.or.ksmart.dto;

public class Pro_option {
	private String pro_op_code;		// 옵션 코드 (기본키)
	private String p_code;			// 상품 코드 (외래키 테이블:상품)
	private String op_gender;		// 성별옷
	private String op_color;		// 색상
	private int op_size;			// 사이즈
	
	// 검색 전용 변수들
	private String op_size_min;		// 사이즈 구간 최소 사이즈
	private String op_size_max;		// 사이즈 구간 최대 사이즈
	
	public String getPro_op_code() {
		return pro_op_code;
	}
	public void setPro_op_code(String pro_op_code) {
		System.out.println(pro_op_code + " <- pro_op_code   setPro_op_code()   Pro_option.java");
		this.pro_op_code = pro_op_code;
	}
	public String getP_code() {
		return p_code;
	}
	public void setP_code(String p_code) {
		System.out.println(p_code + " <- p_code   setP_code()   Pro_option.java");
		this.p_code = p_code;
	}
	public String getOp_gender() {
		return op_gender;
	}
	public void setOp_gender(String op_gender) {
		System.out.println(op_gender + " <- op_gender   setOp_gender()   Pro_option.java");
		this.op_gender = op_gender;
	}
	public String getOp_color() {
		return op_color;
	}
	public void setOp_color(String op_color) {
		System.out.println(op_color + " <- op_color   setOp_color()   Pro_option.java");
		this.op_color = op_color;
	}
	public int getOp_size() {
		return op_size;
	}
	public void setOp_size(int op_size) {
		System.out.println(op_size + " <- op_size   setOp_size()   Pro_option.java");
		this.op_size = op_size;
	}
	public String getOp_size_min() {
		return op_size_min;
	}
	public void setOp_size_min(String op_size_min) {
		System.out.println(op_size_min + " <- op_size_min   setOp_size_min()   Pro_option.java");
		this.op_size_min = op_size_min;
	}
	public String getOp_size_max() {
		return op_size_max;
	}
	public void setOp_size_max(String op_size_max) {
		System.out.println(op_size_max + " <- op_size_max   setOp_size_max()   Pro_option.java");
		this.op_size_max = op_size_max;
	}
}
