package room.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import event.model.EventBean;
import location.model.LocationBean;

@Entity
@Table(name="rooms")
public class RoomBean implements java.io.Serializable {
	

	private Integer roomno;
	private LocationBean locationBean; // One-To-Many
	private String title;
	private String kind;
	private String roomstatus;
	private Integer capacity;	
	private Set<EventBean> events = new HashSet<EventBean>();
	
	public RoomBean(){}
	
	@Override
	public String toString() {
		
		return "{"+roomno+","+locationBean+","+title+","+roomstatus+","+capacity+"}";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRoomno() {
		return roomno;
	}
	
	public void setRoomno(Integer roomno) {
		this.roomno = roomno;
	}
	
	@ManyToOne(fetch=FetchType.LAZY) 			
	@JoinColumn(name = "locationno")       // join locationno
	public LocationBean getLocationBean() {
		return locationBean;
	}
	
	public void setLocationBean(LocationBean locationBean) {
		this.locationBean = locationBean;
	}
	
	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	public String getRoomstatus() {
		return roomstatus;
	}

	public void setRoomstatus(String roomstatus) {
		this.roomstatus = roomstatus;
	}

	public Integer getCapacity() {
		return capacity;
	}
	
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, fetch=FetchType.LAZY, mappedBy="roomBean")
	public Set<EventBean> getEvents() {
		return events;
	}

	public void setEvents(Set<EventBean> events) {
		this.events = events;
	}
	
	
}
