package YuiShoppingCart;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import YuiOrderDetails.YuiOrderDetailsBean;


public class YuiShoppingCartBean implements Serializable {
	private int eventno;
	private String event_title;
	private java.sql.Timestamp eventstart;
	private java.sql.Timestamp eventend;
	private double charge = 0;
	private String t_name;
	private String locationname;
	private String room_title;
	

	public YuiShoppingCartBean() {

	}

	public YuiShoppingCartBean(int eventno, String event_title, Timestamp eventstart, Timestamp eventend,
			double charge, String t_name, String locationname, String room_title) {
		super();
		this.eventno = eventno;
		this.event_title = event_title;
		this.eventstart = eventstart;
		this.eventend = eventend;
		this.charge = charge;
		this.t_name = t_name;
		this.locationname = locationname;
		this.room_title = room_title;
	}

	public int getEventno() {
		return eventno;
	}

	public void setEventno(int eventno) {
		this.eventno = eventno;
	}

	public String getEvent_title() {
		return event_title;
	}

	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}

	public java.sql.Timestamp getEventstart() {
		return eventstart;
	}

	public void setEventstart(java.sql.Timestamp eventstart) {
		this.eventstart = eventstart;
	}

	public java.sql.Timestamp getEventend() {
		return eventend;
	}

	public void setEventend(java.sql.Timestamp eventend) {
		this.eventend = eventend;
	}

	public double getCharge() {
		return charge;
	}

	public void setCharge(double charge) {
		this.charge = charge;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

	public String getRoom_title() {
		return room_title;
	}

	public void setRoom_title(String room_title) {
		this.room_title = room_title;
	}

	@Override
	public String toString() {
		return "YuiShoppingCartBean [eventno=" + eventno + ", event_title=" + event_title + ", eventstart=" + eventstart
				+ ", eventend=" + eventend + ", charge=" + charge + ", t_name=" + t_name + ", locationname="
				+ locationname + ", room_title=" + room_title + "]";
	}

}
