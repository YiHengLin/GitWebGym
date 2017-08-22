package event.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import room.model.RoomBean;
import trainer.model.TrainerBean;

@Entity
@Table(name="event")
public class EventBean implements java.io.Serializable {
	
	private Integer eventno;
	private TrainerBean trainerBean;
	private RoomBean roomBean;
	private String title;
	private String coursekind;
	private String e_status;
	private java.sql.Timestamp eventstart; 
	private java.sql.Timestamp eventend; 
	private Integer enroll;
	private Integer charge;
	
	public EventBean(){}
	
	
	@Override
	public String toString() {
		return "{"+eventno+","+eventstart+","+eventend+","+title+","+charge+"}";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getEventno() {
		return eventno;
	}
	public void setEventno(Integer eventno) {
		this.eventno = eventno;
	}
	
	@ManyToOne(fetch=FetchType.LAZY) 			
	@JoinColumn(name = "trainerID")
	public TrainerBean getTrainerBean() {
		return trainerBean;
	}
	public void setTrainerBean(TrainerBean trainerBean) {
		this.trainerBean = trainerBean;
	}
	
	@ManyToOne(fetch=FetchType.LAZY) 			
	@JoinColumn(name = "roomno")
	public RoomBean getRoomBean() {
		return roomBean;
	}
	public void setRoomBean(RoomBean roomBean) {
		this.roomBean = roomBean;
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


	public Integer getEnroll() {
		return enroll;
	}
	public void setEnroll(Integer enroll) {
		this.enroll = enroll;
	}


	public Integer getCharge() {
		return charge;
	}
	public void setCharge(Integer charge) {
		this.charge = charge;
	}
}
