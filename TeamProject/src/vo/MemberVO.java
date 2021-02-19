package vo;

import java.sql.Timestamp;

public class MemberVO {
	private String userid;// varchar2(20)
	private String password;// varchar2(100) not null
	private String salt;// varchar2(100)
	private String name;// varchar2(20) not null
	private Timestamp regdate;// timestamp default sysdate not null
	private String email;// varchar2(40) not null
	private String phone;// varchar2(20) not null
	private String kakao_open;// varchar2(20)
	private String kakao_id;// varchar2(20)
	private String zipcode;// varchar2(7) not null
	private String address1;// varchar2(120) not null
	private String address2;// varchar2(60) not null
	private int state;// number(1) not null
	private int role;// number(1) not null
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getKakao_open() {
		return kakao_open;
	}
	public void setKakao_open(String kakao_open) {
		this.kakao_open = kakao_open;
	}
	public String getKakao_id() {
		return kakao_id;
	}
	public void setKakao_id(String kakao_id) {
		this.kakao_id = kakao_id;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	
}
