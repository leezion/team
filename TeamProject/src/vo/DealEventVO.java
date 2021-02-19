package vo;

import java.sql.Timestamp;

public class DealEventVO {
	private int dealid;// number(10)
	private Timestamp regdate;// timestamp default sysdate not null
	private String sellerid;// varchar2(20)
	private String buyerid;// varchar2(20)
	private int itemid;// number(10) not null
	private int reviewid;// number(10)
	private String canceled;// varchar2(1) default 'N' not null
	public int getDealid() {
		return dealid;
	}
	public void setDealid(int dealid) {
		this.dealid = dealid;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	public String getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(String buyerid) {
		this.buyerid = buyerid;
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public String getCanceled() {
		return canceled;
	}
	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}
	
}
