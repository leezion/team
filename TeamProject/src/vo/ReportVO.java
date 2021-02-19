package vo;

import java.sql.Timestamp;

public class ReportVO {
	private int reportid;// number(10)
	private int category;// number(1) not null
	private String title;// varchar2(120) not null
	private String content;// varchar2(1500) not null
	private Timestamp regdate;// timestamp default sysdate not null
	private String userid;// varchar2(20)
	private String readconfirm;// varchar2(1) default 'N' not null
	public int getReportid() {
		return reportid;
	}
	public void setReportid(int reportid) {
		this.reportid = reportid;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	public String getReadconfirm() {
		return readconfirm;
	}
	public void setReadconfirm(String readconfirm) {
		this.readconfirm = readconfirm;
	}

}
