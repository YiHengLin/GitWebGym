package YuiEvent;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class YuiEventBean implements Serializable {
	private int eventno;
	private String trainerid;
	private String roomno;
	private String title;
	private String coursekind;
	private String e_status;
	private java.sql.Timestamp eventstart;
	private java.sql.Timestamp eventend;
	private String enroll;
	private double charge = 0;

	public YuiEventBean() {

	}


	public YuiEventBean(int eventno, String trainerid, String roomno, String title, String coursekind, String e_status,
			Timestamp eventstart, Timestamp eventend, String enroll, double charge) {
		super();
		this.eventno = eventno;
		this.trainerid = trainerid;
		this.roomno = roomno;
		this.title = title;
		this.coursekind = coursekind;
		this.e_status = e_status;
		this.eventstart = eventstart;
		this.eventend = eventend;
		this.enroll = enroll;
		this.charge = charge;
	}


	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static java.util.Date convertDate(String temp) {
		java.util.Date result = new java.util.Date();
		try {
			result = sdf.parse(temp);
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		return result;
	}


	public int getEventno() {
		return eventno;
	}


	public void setEventno(int eventno) {
		this.eventno = eventno;
	}


	public String getTrainerid() {
		return trainerid;
	}


	public void setTrainerid(String trainerid) {
		this.trainerid = trainerid;
	}


	public String getRoomno() {
		return roomno;
	}


	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCoursekind() {
		return coursekind;
	}


	public void setCoursekind(String coursekind) {
		this.coursekind = coursekind;
	}


	public String getE_status() {
		return e_status;
	}


	public void setE_status(String e_status) {
		this.e_status = e_status;
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


	public String getEnroll() {
		return enroll;
	}


	public void setEnroll(String enroll) {
		this.enroll = enroll;
	}


	public double getCharge() {
		return charge;
	}


	public void setCharge(double charge) {
		this.charge = charge;
	}


	@Override
	public String toString() {
		return "TestBean [eventno=" + eventno + ", trainerid=" + trainerid + ", roomno=" + roomno + ", title=" + title
				+ ", coursekind=" + coursekind + ", e_status=" + e_status + ", eventstart=" + eventstart + ", eventend="
				+ eventend + ", enroll=" + enroll + ", charge=" + charge + "]";
	}
	
	
	
	

}