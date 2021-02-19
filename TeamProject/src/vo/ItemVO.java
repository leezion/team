package vo;

import java.sql.Timestamp;

public class ItemVO {
	private int itemid;// number(10)
	private int category;// number(3)
	private int generalgoods;// number(3)
	private String code;// varchar2(10)
	private int rarity;// number(3)
	private String firstlimited;// varchar2(1)
	private int condition;// number(3)
	private int price;// number(10) not null
	private Timestamp regdate;// timestamp default sysdate not null
	private String description;// varchar2(1000) not null
	private int quantity;// number(4) not null
	private String visible;// varchar2(1) default 'N' not null
	private String deleted;// varchar2(1) default 'N' not null
	private String image1;// varchar2(300)
	private String image2;// varchar2(300)
	private String image3;// varchar2(300)
	private String image4;// varchar2(300)
	private String sellerid;// varchar2(20)
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getGeneralgoods() {
		return generalgoods;
	}
	public void setGeneralgoods(int generalgoods) {
		this.generalgoods = generalgoods;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getRarity() {
		return rarity;
	}
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	public String getFirstlimited() {
		return firstlimited;
	}
	public void setFirstlimited(String firstlimited) {
		this.firstlimited = firstlimited;
	}
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getVisible() {
		return visible;
	}
	public void setVisible(String visible) {
		this.visible = visible;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getSellerid() {
		return sellerid;
	}
	public void setSellerid(String sellerid) {
		this.sellerid = sellerid;
	}
	
}
