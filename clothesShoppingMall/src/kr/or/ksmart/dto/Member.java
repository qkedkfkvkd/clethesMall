package kr.or.ksmart.dto;

// 회원 관리 VO
public class Member {
	private String u_id;			// 아이디 (기본키)
	private String u_pw;			// 비밀번호
	private String u_name;			// 이름
	private String u_level;			// 권한
	private String u_gender;		// 성별
	private String u_phone;			// 폰번호
	private String u_email;			// 이메일
	private String u_address;		// 주소
	private boolean u_sanctions;	// 제재 여부 : 디비에는 (0, 1)로 저장됨
	
	public String getU_id() {
		return u_id;
	}
	public void setU_id(String u_id) {
		System.out.println(u_id + " <- u_id   setU_id()   Member.java");
		this.u_id = u_id;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		System.out.println(u_pw + " <- u_pw   setU_pw()   Member.java");
		this.u_pw = u_pw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		System.out.println(u_name + " <- u_name   setU_name()   Member.java");
		this.u_name = u_name;
	}
	public String getU_level() {
		return u_level;
	}
	public void setU_level(String u_level) {
		System.out.println(u_level + " <- u_level   setU_level()   Member.java");
		this.u_level = u_level;
	}
	public String getU_gender() {
		return u_gender;
	}
	public void setU_gender(String u_gender) {
		System.out.println(u_gender + " <- u_gender   setU_gender()   Member.java");
		this.u_gender = u_gender;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		System.out.println(u_phone + " <- u_phone   setU_phone()   Member.java");
		this.u_phone = u_phone;
	}
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		System.out.println(u_email + " <- u_email   setU_email()   Member.java");
		this.u_email = u_email;
	}
	public String getU_address() {
		return u_address;
	}
	public void setU_address(String u_address) {
		System.out.println(u_address + " <- u_address   setU_address()   Member.java");
		this.u_address = u_address;
	}
	public boolean isU_sanctions() {
		return u_sanctions;
	}
	public void setU_sanctions(boolean u_sanctions) {
		System.out.println(u_sanctions + " <- u_sanctions   setU_sanctions()   Member.java");
		this.u_sanctions = u_sanctions;
	}
	
}
