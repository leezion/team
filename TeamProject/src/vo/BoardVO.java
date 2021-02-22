package vo;

import java.sql.Timestamp;

public class BoardVO {
	private int articleid;// number(10) 글 번호
	private String title;// varchar2(120) not null 제목
	private String content;// varchar2(4000) not null 내용
	private int readcount;// number(8) not null 조회수
	private Timestamp regdate;// timestamp default sysdate not null 작성시간
	private String ip;// varchar2(39) not null 아이피
	private String userid;// varchar2(20) 유저아이디
	private String userid_off;// varchar2(20) 
	private String password_off;// varchar2(20) 
	
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserid_off() {
		return userid_off;
	}
	public void setUserid_off(String userid_off) {
		this.userid_off = userid_off;
	}
	public String getPassword_off() {
		return password_off;
	}
	public void setPassword_off(String password_off) {
		this.password_off = password_off;
	}

	
}
