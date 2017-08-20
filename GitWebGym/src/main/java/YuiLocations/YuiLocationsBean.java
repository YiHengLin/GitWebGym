package YuiLocations;

import java.io.Serializable;

public class YuiLocationsBean implements Serializable {
	private String locationno;
	private String locationname;
	private String l_address;
	private String openhour;
	private String phone;
	
	public YuiLocationsBean(){
		
	}

	public YuiLocationsBean(String locationno, String locationname, String l_address, String openhour, String phone) {
		super();
		this.locationno = locationno;
		this.locationname = locationname;
		this.l_address = l_address;
		this.openhour = openhour;
		this.phone = phone;
	}

	public String getLocationno() {
		return locationno;
	}

	public void setLocationno(String locationno) {
		this.locationno = locationno;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "YuiLocationsBean [locationno=" + locationno + ", locationname=" + locationname + ", l_address="
				+ l_address + ", openhour=" + openhour + ", phone=" + phone + "]";
	}
	
	

}
