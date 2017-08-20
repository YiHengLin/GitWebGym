package YuiMember;

import java.io.Serializable;
import java.sql.Timestamp;

public class YuiMemberBean implements Serializable {

	private String memberID;
	private String m_password;
	private String m_name;
	private String m_id_number;
	private String m_gender;
	private java.sql.Timestamp m_bday;
	private String m_mail;
	private String m_mobile;
	private String m_tel;
	private String m_addr;
	private java.sql.Timestamp m_Regi;
	private String m_photo;
	private String blist;
	private String m_iden;
	
	public YuiMemberBean (){}
	
//	@Override
//	public String toString() {
//		return "YuiMemberBean [memberID=" + memberID + ", m_password=" + m_password + ", m_name=" + m_name
//				+ ", m_id_number=" + m_id_number + ", m_gender=" + m_gender + ", m_bday=" + m_bday + ", m_mail="
//				+ m_mail + ", m_mobile=" + m_mobile + ", m_tel=" + m_tel + ", m_address=" + m_address + ", m_register="
//				+ m_register + ", m_photo=" + m_photo + ", is_blacklist=" + is_blacklist + ", m_identity=" + m_identity
//				+ "]";
//	}
	
	
	@Override
	public String toString() {
		return "{"+memberID+","+m_password+","+m_name+","+m_id_number+","+m_gender+","+m_bday+","+m_mail+","+m_mobile+","+m_tel+","+m_addr+","+m_Regi+","+blist+","+","+m_iden+"}";
	}
	
	
	public YuiMemberBean(String memberID, String m_password, String m_name, String m_id_number, String m_gender,
			Timestamp m_bday, String m_mail, String m_mobile, String m_tel, String m_address, Timestamp m_register,
			String m_photo, String is_blacklist, String m_identity) {
		super();
		this.memberID = memberID;
		this.m_password = m_password;
		this.m_name = m_name;
		this.m_id_number = m_id_number;
		this.m_gender = m_gender;
		this.m_bday = m_bday;
		this.m_mail = m_mail;
		this.m_mobile = m_mobile;
		this.m_tel = m_tel;
		this.m_addr = m_address;
		this.m_Regi = m_register;
		this.m_photo = m_photo;
		this.blist = is_blacklist;
		this.m_iden = m_identity;
	}
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
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
	public java.sql.Timestamp getM_bday() {
		return m_bday;
	}
	public void setM_bday(java.sql.Timestamp m_bday) {
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
		return m_addr;
	}
	public void setM_address(String m_address) {
		this.m_addr = m_address;
	}

	public String getM_photo() {
		return m_photo;
	}
	public void setM_photo(String m_photo) {
		this.m_photo = m_photo;
	}

	public String getM_addr() {
		return m_addr;
	}

	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}

	public java.sql.Timestamp getM_Regi() {
		return m_Regi;
	}

	public void setM_Regi(java.sql.Timestamp m_Regi) {
		this.m_Regi = m_Regi;
	}

	public String getBlist() {
		return blist;
	}

	public void setBlist(String blist) {
		this.blist = blist;
	}

	public String getM_iden() {
		return m_iden;
	}

	public void setM_iden(String m_iden) {
		this.m_iden = m_iden;
	}


	
}
