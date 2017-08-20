package Groups.model;

import java.io.Serializable;

public class GroupsBean implements Serializable {
	public String groupid;
	public String memberid;
	String tilte;
	public String groupid1;
	String memberid1=null;
	String memberid2=null;
	String memberid3=null;
	String memberid4=null;
	String memberid5=null;
	
	
	

	public  GroupsBean (String groupid,String memberid,String tilte){
		super();
		this.groupid = groupid;
		this.memberid = memberid;
		this.tilte = tilte;
	}
	public  GroupsBean (String groupid,String memberid){
		super();
		this.groupid = groupid;
		this.memberid = memberid;
	}
	public String getGroupid1() {
		return groupid1;
	}
	public void setGroupid1(String groupid1) {
		this.groupid1 = groupid1;
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
	public  GroupsBean (String groupid,String memberid,String tilte,String groupid1){
		super();
		this.groupid = groupid;
		this.memberid = memberid;
		this.tilte = tilte;
		this.groupid1=groupid1;
	}
	
	public GroupsBean (String groupid){
		super();
		this.groupid = groupid;
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
}


