package vo;

import java.sql.Timestamp;

public class ReplyVO {
	private int replyid;// number(10)
	private String content;// varchar2(1000) not null
	private Timestamp regdate;// timestamp default sysdate not null
	private int articleid;// number(10) not null
	private String ip;// varchar2(39) not null
	private String userid;// varchar2(20)
	private String userid_off;// varchar2(20)
	private String password_off;// varchar2(20)
	
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
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
