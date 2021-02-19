package vo;

public class CardDBVO {
	private int cardid; // number(10)
	private String code_first;// varchar2(10)
	private String nation;// varchar2(30)
	private String ncode;// varchar2(5)
	private String year;// varchar2(5)
	private String package_type;// varchar2(60)
	private String package_kr;// varchar2(100)
	private String package_jp;// varchar2(100)
	private String package_en;// varchar2(100)
	
	public int getCardid() {
		return cardid;
	}
	public void setCardid(int cardid) {
		this.cardid = cardid;
	}
	public String getCode_first() {
		return code_first;
	}
	public void setCode_first(String code_first) {
		this.code_first = code_first;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNcode() {
		return ncode;
	}
	public void setNcode(String ncode) {
		this.ncode = ncode;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPackage_type() {
		return package_type;
	}
	public void setPackage_type(String package_type) {
		this.package_type = package_type;
	}
	public String getPackage_kr() {
		return package_kr;
	}
	public void setPackage_kr(String package_kr) {
		this.package_kr = package_kr;
	}
	public String getPackage_jp() {
		return package_jp;
	}
	public void setPackage_jp(String package_jp) {
		this.package_jp = package_jp;
	}
	public String getPackage_en() {
		return package_en;
	}
	public void setPackage_en(String package_en) {
		this.package_en = package_en;
	}
	
	
}
