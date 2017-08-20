package admin_event.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class EventBean implements Serializable {

	private int eventno;
	private String trainerid;
	private int roomno;
	private String title;
	private String coursekind;
	private String e_status;
	private Date eventstart;
	private Date eventend;
	private int enroll;
	private int charge;

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

	public int getRoomno() {
		return roomno;
	}

	public void setRoomno(int roomno) {
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

	public Date getEventstart() {
		return eventstart;
	}

	public void setEventstart(Date eventstart) {
		this.eventstart = eventstart;
	}

	public Date getEventend() {
		return eventend;
	}

	public void setEventend(Date eventend) {
		this.eventend = eventend;
	}

	public int getEnroll() {
		return enroll;
	}

	public void setEnroll(int enroll) {
		this.enroll = enroll;
	}

	public int getCharge() {
		return charge;
	}

	public void setCharge(int charge) {
		this.charge = charge;
	}

}
