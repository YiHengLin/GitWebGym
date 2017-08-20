package location.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import room.model.RoomBean;


@Entity
@Table(name="locations")
public class LocationBean implements java.io.Serializable{
	

	private Integer locationno;
	private String locationname;
	private String l_address;
	private String openhour;
	private String phone;
	private Set<RoomBean> rooms = new HashSet<RoomBean>();  // One-To-Many
		
	public LocationBean(){}
	
	@Override
	public String toString() {
		return "{"+locationno+","+locationname+","+phone+"}";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getLocationno() {
		return locationno;
	}
	public void setLocationno(Integer locationno) {
		this.locationno = locationno;
	}
	public String getLocationname() {
		return locationname;
	}
	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getL_address() {
		return l_address;
	}

	public void setL_address(String l_address) {
		this.l_address = l_address;
	}

	public String getOpenhour() {
		return openhour;
	}

	public void setOpenhour(String openhour) {
		this.openhour = openhour;
	}

	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true, fetch=FetchType.LAZY, mappedBy="locationBean")
	public Set<RoomBean> getRooms() {
		return rooms;
	}

	public void setRooms(Set<RoomBean> rooms) {
		this.rooms = rooms;
	}
	
	

}
