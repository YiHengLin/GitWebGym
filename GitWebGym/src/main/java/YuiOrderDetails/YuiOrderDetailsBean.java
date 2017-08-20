package YuiOrderDetails;

import java.io.Serializable;

public class YuiOrderDetailsBean implements Serializable {
	private String orderdetailno;
	private int orderno;
	private int eventno;
	private double charge;
	
	public YuiOrderDetailsBean(){
		
	}
	
	public YuiOrderDetailsBean(String orderdetailno, int orderno, int eventno, double charge) {
		super();
		this.orderdetailno = orderdetailno;
		this.orderno = orderno;
		this.eventno = eventno;
		this.charge = charge;
	}

	public String getOrderdetailno() {
		return orderdetailno;
	}

	public void setOrderdetailno(String orderdetailno) {
		this.orderdetailno = orderdetailno;
	}

	public int getOrderno() {
		return orderno;
	}

	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

	public int getEventno() {
		return eventno;
	}

	public void setEventno(int eventno) {
		this.eventno = eventno;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	@Override
	public String toString() {
		return "YuiOrderDetailsBean [orderdetailno=" + orderdetailno + ", orderno=" + orderno + ", eventno=" + eventno
				+ ", charge=" + charge + "]";
	}
	

}
