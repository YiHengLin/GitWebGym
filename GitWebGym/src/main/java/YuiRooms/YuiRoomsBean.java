package YuiRooms;

import java.io.Serializable;

public class YuiRoomsBean implements Serializable {
	private String roomno;
	private String locationno;
	private String title;
	private String capacity;
	public YuiRoomsBean(){
		
	}
	public YuiRoomsBean(String roomno, String locationno, String title, String capacity) {
		super();
		this.roomno = roomno;
		this.locationno = locationno;
		this.title = title;
		this.capacity = capacity;
	}
	public String getRoomno() {
		return roomno;
	}
	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}
	public String getLocationno() {
		return locationno;
	}
	public void setLocationno(String locationno) {
		this.locationno = locationno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	@Override
	public String toString() {
		return "YuiRoomsBean [roomno=" + roomno + ", locationno=" + locationno + ", title=" + title + ", capacity="
				+ capacity + "]";
	}
	
	
	
}
