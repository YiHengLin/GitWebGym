package YuiAddOrder;

import java.io.Serializable;
import java.sql.Timestamp;

public class YuiOrderBean implements Serializable {
	public YuiOrderBean(){
		
	}
	
	
	public YuiOrderBean(int orderno, String memberID, String totalcharge, Timestamp enrollday, String is_pay) {
		super();
		this.orderno = orderno;
		this.memberID = memberID;
		this.totalcharge = totalcharge;
		this.enrollday = enrollday;
		this.is_pay = is_pay;
	}


	private int orderno;
	private String memberID;
	private String totalcharge;
	private Timestamp enrollday;
	private String is_pay;
	
	
	public int getOrderno() {
		return orderno;
	}


	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}


	public String getmemberID() {
		return memberID;
	}


	public void setmemberID(String memberID) {
		this.memberID = memberID;
	}


	public String getTotalcharge() {
		return totalcharge;
	}


	public void setTotalcharge(String totalcharge) {
		this.totalcharge = totalcharge;
	}


	public Timestamp getEnrollday() {
		return enrollday;
	}


	public void setEnrollday(Timestamp enrollday) {
		this.enrollday = enrollday;
	}


	public String getIs_pay() {
		return is_pay;
	}


	public void setIs_pay(String is_pay) {
		this.is_pay = is_pay;
	}


	@Override
	public String toString() {
		return "orderBean [orderno=" + orderno + ", memberID=" + memberID + ", totalcharge=" + totalcharge
				+ ", enrollday=" + enrollday + ", is_pay=" + is_pay + "]";
	}
	
	
}
