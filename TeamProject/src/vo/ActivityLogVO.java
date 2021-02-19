package vo;

import java.sql.Timestamp;

public class ActivityLogVO {
	private int eventid;// number(10)
	private Timestamp regdate;// timestamp default sysdate not null
	private String userid;// varchar2(20)
	private String user_off;// varchar2(20)
	private String ip;// varchar2(39)
	private int replyid;// number(10)
	private int articleid;// number(10)
	private int itemid;// number(10)
	private int dealid;// number(10)
	private int reviewid;// number(10)
	private int reportid;// number(10)
	private String event;// varchar2(300) not null

	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser_off() {
		return user_off;
	}
	public void setUser_off(String user_off) {
		this.user_off = user_off;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getDealid() {
		return dealid;
	}
	public void setDealid(int dealid) {
		this.dealid = dealid;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public int getReportid() {
		return reportid;
	}
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
}
