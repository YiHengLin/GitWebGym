package YuiTrainers;

import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class YuiTrainerBean implements java.io.Serializable{
	
	private String trainerID;
	private String t_password;
	private String t_name;
	private String t_id_number;
	private String t_gender;
	private  java.sql.Date t_bday;
	private String t_mail;
	private String t_mobile;
	private String t_tel;
	private String t_addr;
	private java.sql.Timestamp t_Regi;
	private Blob t_pic;
	private boolean t_blist;
	private String t_iden;
	private String t_field;
	private String t_exp;
	private String t_lice;

	public YuiTrainerBean(){
		
	}

	public YuiTrainerBean(String trainerID, String t_password, String t_name, String t_id_number, String t_gender,
			Date t_bday, String t_mail, String t_mobile, String t_tel, String t_addr, Timestamp t_Regi, Blob t_pic,
			boolean t_blist, String t_iden, String t_field, String t_exp, String t_lice) {
		super();
		this.trainerID = trainerID;
		this.t_password = t_password;
		this.t_name = t_name;
		this.t_id_number = t_id_number;
		this.t_gender = t_gender;
		this.t_bday = t_bday;
		this.t_mail = t_mail;
		this.t_mobile = t_mobile;
		this.t_tel = t_tel;
		this.t_addr = t_addr;
		this.t_Regi = t_Regi;
		this.t_pic = t_pic;
		this.t_blist = t_blist;
		this.t_iden = t_iden;
		this.t_field = t_field;
		this.t_exp = t_exp;
		this.t_lice = t_lice;
	}

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

	public java.sql.Date getT_bday() {
		return t_bday;
	}

	public void setT_bday(java.sql.Date t_bday) {
		this.t_bday = t_bday;
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

	public String getT_addr() {
		return t_addr;
	}

	public void setT_addr(String t_addr) {
		this.t_addr = t_addr;
	}

	public java.sql.Timestamp getT_Regi() {
		return t_Regi;
	}

	public void setT_Regi(java.sql.Timestamp t_Regi) {
		this.t_Regi = t_Regi;
	}

	public Blob getT_pic() {
		return t_pic;
	}

	public void setT_pic(Blob t_pic) {
		this.t_pic = t_pic;
	}

	public boolean isT_blist() {
		return t_blist;
	}

	public void setT_blist(boolean t_blist) {
		this.t_blist = t_blist;
	}

	public String getT_iden() {
		return t_iden;
	}

	public void setT_iden(String t_iden) {
		this.t_iden = t_iden;
	}

	public String getT_field() {
		return t_field;
	}

	public void setT_field(String t_field) {
		this.t_field = t_field;
	}

	public String getT_exp() {
		return t_exp;
	}

	public void setT_exp(String t_exp) {
		this.t_exp = t_exp;
	}

	public String getT_lice() {
		return t_lice;
	}

	public void setT_lice(String t_lice) {
		this.t_lice = t_lice;
	}

	@Override
	public String toString() {
		return "YuiTrainerBean [trainerID=" + trainerID + ", t_password=" + t_password + ", t_name=" + t_name
				+ ", t_id_number=" + t_id_number + ", t_gender=" + t_gender + ", t_bday=" + t_bday + ", t_mail="
				+ t_mail + ", t_mobile=" + t_mobile + ", t_tel=" + t_tel + ", t_addr=" + t_addr + ", t_Regi=" + t_Regi
				+ ", t_pic=" + t_pic + ", t_blist=" + t_blist + ", t_iden=" + t_iden + ", t_field=" + t_field
				+ ", t_exp=" + t_exp + ", t_lice=" + t_lice + "]";
	}
	
	

}
