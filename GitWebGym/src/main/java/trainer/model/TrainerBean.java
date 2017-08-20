package trainer.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import event.model.EventBean;


@Entity
@Table(name="trainers")
public class TrainerBean implements Serializable {
	

	private String trainerID;
	private String t_password;
	private String t_name;
	private String t_id_number;
	private String t_gender;
	private java.sql.Date t_bday;
	private String t_mail;
	private String t_mobile;
	private String t_tel;
	private String t_address;
	private java.sql.Date t_register;
	private byte[] t_photo;
	private String is_blacklist;
	private String t_identity;
	private String t_field;
	private String t_experience;
	private String t_licence;

	private Set<EventBean> events = new HashSet<EventBean>();
	
	public TrainerBean(){}
	
	@Override
	public String toString() {
		return "{"+trainerID+","+t_name+","+t_mail+","+t_identity+","+t_field+","+t_experience+","+t_licence+"}";
	}

	@Id
	public String getTrainerID() {
		return trainerID;
	}
	public void setTrainerID(String trainerID) {
		this.trainerID = trainerID;
	}
	
	public String getT_password() {
		return t_password;
	}

	public void setT_password(String t_password) {
		this.t_password = t_password;
	}

	public String getT_name() {
		return t_name;
	}

	public void setT_name(String t_name) {
		this.t_name = t_name;
	}

	public String getT_id_number() {
		return t_id_number;
	}

	public void setT_id_number(String t_id_number) {
		this.t_id_number = t_id_number;
	}

	public String getT_gender() {
		return t_gender;
	}

	public void setT_gender(String t_gender) {
		this.t_gender = t_gender;
	}



	public String getT_mail() {
		return t_mail;
	}

	public void setT_mail(String t_mail) {
		this.t_mail = t_mail;
	}

	public String getT_mobile() {
		return t_mobile;
	}

	public void setT_mobile(String t_mobile) {
		this.t_mobile = t_mobile;
	}

	public String getT_tel() {
		return t_tel;
	}

	public void setT_tel(String t_tel) {
		this.t_tel = t_tel;
	}

	public String getT_address() {
		return t_address;
	}

	public void setT_address(String t_address) {
		this.t_address = t_address;
	}


	public java.sql.Date getT_bday() {
		return t_bday;
	}

	public void setT_bday(java.sql.Date t_bday) {
		this.t_bday = t_bday;
	}

	public java.sql.Date getT_register() {
		return t_register;
	}

	public void setT_register(java.sql.Date t_register) {
		this.t_register = t_register;
	}

	public byte[] getT_photo() {
		return t_photo;
	}

	public void setT_photo(byte[] t_photo) {
		this.t_photo = t_photo;
	}

	public String getIs_blacklist() {
		return is_blacklist;
	}

	public void setIs_blacklist(String is_blacklist) {
		this.is_blacklist = is_blacklist;
	}

	public String getT_identity() {
		return t_identity;
	}

	public void setT_identity(String t_identity) {
		this.t_identity = t_identity;
	}

	public String getT_field() {
		return t_field;
	}

	public void setT_field(String t_field) {
		this.t_field = t_field;
	}

	public String getT_experience() {
		return t_experience;
	}

	public void setT_experience(String t_experience) {
		this.t_experience = t_experience;
	}

	public String getT_licence() {
		return t_licence;
	}

	public void setT_licence(String t_licence) {
		this.t_licence = t_licence;
	}
	
	
	//orphanRemoval = true
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY, mappedBy="trainerBean")
	public Set<EventBean> getEvents() {
		return events;
	}

	public void setEvents(Set<EventBean> events) {
		this.events.clear();
		if(events!=null){
			this.events.addAll(events);
		}
		//this.events = events;
	}

	@Override
	public int hashCode() {
        int result;
        result = getTrainerID().hashCode();
        return result;
	}

	@Override
	public boolean equals(Object obj) {
		
        if (this == obj) return true;
        if ( !(obj instanceof TrainerBean) ) return false;

        final TrainerBean trainer = (TrainerBean) obj;

        if ( !trainer.getTrainerID().equals( getTrainerID() ) ) return false;

        return true;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	

}
