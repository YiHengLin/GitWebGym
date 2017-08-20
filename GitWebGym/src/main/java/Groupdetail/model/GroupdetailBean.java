package Groupdetail.model;

import java.io.Serializable;

public class GroupdetailBean implements Serializable{
	String memberid1;
	String memberid2;
	String memberid3;
	String memberid4;
	String memberid5;
	String groupid;
	String memberid;
	String tilte;
	
	public  GroupdetailBean (String memberid1,String memberid2,String memberid3,String memberid4,String memberid5){
		super();
		this.memberid1 = memberid1;
		this.memberid2 = memberid2;
		this.memberid3 = memberid3;
		this.memberid4 = memberid4;
		this.memberid5 = memberid5;
	}
	public  GroupdetailBean (String memberid1,String memberid2){
		super();
		this.memberid1 = memberid1;
		this.memberid2 = memberid2;
		
	}
	public String getMemberid1() {
		return memberid1;
	}
	public void setMemberid1(String memberid1) {
		this.memberid1 = memberid1;
	}
	public String getMemberid2() {
		return memberid2;
	}
	public void setMemberid2(String memberid2) {
		this.memberid2 = memberid2;
	}
	public String getMemberid3() {
		return memberid3;
	}
	public void setMemberid3(String memberid3) {
		this.memberid3 = memberid3;
	}
	public String getMemberid4() {
		return memberid4;
	}
	public void setMemberid4(String memberid4) {
		this.memberid4 = memberid4;
	}
	public String getMemberid5() {
		return memberid5;
	}
	public void setMemberid5(String memberid5) {
		this.memberid5 = memberid5;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public  GroupdetailBean (String groupid,String memberid,String tilte){
		super();
		this.groupid = groupid;
		this.memberid = memberid;
		this.tilte = tilte;
	}
}
