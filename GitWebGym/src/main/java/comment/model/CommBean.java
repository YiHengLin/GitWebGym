package comment.model;

import java.sql.Timestamp;

public class CommBean implements java.io.Serializable {
	private int commNo;
	private int ArticleNo;
	private String memberId;
	private String Commcontet;
	private java.sql.Timestamp commdate;

	public CommBean() {

	}
	
	
	
	public CommBean(int ArticleNo,String memberId,String Commcontet,java.sql.Timestamp commdate) {
        this.ArticleNo=ArticleNo;
        this.memberId=memberId;
        this.Commcontet=Commcontet;
        this.commdate=commdate;
	}

	public CommBean(int commNo,int ArticleNo,String memberId,String Commcontet,java.sql.Timestamp commdate) {
           this.commNo=commNo;
           this.ArticleNo=ArticleNo;
           this.memberId=memberId;
           this.Commcontet=Commcontet;
           this.commdate=commdate;
	}

	

	public int getCommNo() {
		return commNo;
	}

	public void setCommNo(int commNo) {
		this.commNo = commNo;
	}

	public int getArticleNo() {
		return ArticleNo;
	}

	public void setArticleNo(int articleNo) {
		ArticleNo = articleNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommcontet() {
		return Commcontet;
	}

	public void setCommcontet(String commcontet) {
		Commcontet = commcontet;
	}

	public java.sql.Timestamp getCommdate() {
		return commdate;
	}

	public void setCommdate(java.sql.Timestamp commdate) {
		this.commdate = commdate;
	}

}
