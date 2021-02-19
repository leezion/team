package vo;

import java.sql.Timestamp;

public class BannedEmailVO {

	private String email;// varchar2(40)
	private Timestamp regdate;// timestamp default sysdate not null
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

}
