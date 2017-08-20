package mytrains.model; 

import java.io.Serializable;

public class selftrainsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String selftrainno;
	private String memberid;                // 會員ID
	private String trainday;   			    // 訓練日期
	
	public selftrainsBean() {
	}
	
	public String toString() {
		return "["+selftrainno+","+memberid+","+trainday+"]";
	}
	
	public selftrainsBean(String memberid, String trainday) {
		super();
		this.memberid =  memberid;
		this.trainday = trainday;
	}	

	public String getSelftrainno() {
		return selftrainno;
	}

	public void setSelftrainno(String selftrainno) {
		this.selftrainno = selftrainno;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getTrainday() {
		return trainday;
	}

	public void setTrainday(String trainday) {
		this.trainday = trainday;
	}
	
}