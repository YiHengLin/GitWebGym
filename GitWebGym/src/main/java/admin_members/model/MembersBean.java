package admin_members.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class MembersBean implements Serializable {

	private String memberid;
	private String m_password;
	private String m_name;
	private String m_id_number;
	private String m_gender;
	private Date m_bday;
	private String m_mail;
	private String m_mobile;
	private String m_tel;
	private String m_address;
	private Date m_register;
	private Blob m_photo;
	private String is_blacklist;
	private String m_identity;
	
	
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_id_number() {
		return m_id_number;
	}
	public void setM_id_number(String m_id_number) {
		this.m_id_number = m_id_number;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public Date getM_bday() {
		return m_bday;
	}
	public void setM_bday(Date m_bday) {
		this.m_bday = m_bday;
	}
	public String getM_mail() {
		return m_mail;
	}
	public void setM_mail(String m_mail) {
		this.m_mail = m_mail;
	}
	public String getM_mobile() {
		return m_mobile;
	}
	public void setM_mobile(String m_mobile) {
		this.m_mobile = m_mobile;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	public String getM_address() {
		return m_address;
	}
	public void setM_address(String m_address) {
		this.m_address = m_address;
	}
	public Date getM_register() {
		return m_register;
	}
	public void setM_register(Date m_register) {
		this.m_register = m_register;
	}
	public Blob getM_photo() {
		return m_photo;
	}
	public void setM_photo(Blob m_photo) {
		this.m_photo = m_photo;
	}
	public String getIs_blacklist() {
		return is_blacklist;
	}
	public void setIs_blacklist(String is_blacklist) {
		this.is_blacklist = is_blacklist;
	}
	public String getM_identity() {
		return m_identity;
	}
	public void setM_identity(String m_identity) {
		this.m_identity = m_identity;
	}
	
	@Override
	public String toString() {
		return "MembersBean [memberid=" + memberid + ", m_password=" + m_password + ", m_name=" + m_name
				+ ", m_id_number=" + m_id_number + ", m_gender=" + m_gender + ", m_bday=" + m_bday + ", m_mail="
				+ m_mail + ", m_mobile=" + m_mobile + ", m_tel=" + m_tel + ", m_address=" + m_address + ", m_register="
				+ m_register + ", m_photo=" + m_photo + ", is_blacklist=" + is_blacklist + ", m_identity=" + m_identity
				+ "]";
	}

	
	
	
	
	
}
