package mail.model;

import java.io.Serializable;
import java.sql.*;

public class MailBean implements Serializable{

	private int mailno;
	private String memberid;
	private String title;
	private String content;
	private Timestamp m_postdata;
	private String kind;
	
	 public MailBean(){}
	 
	 public MailBean(String memberid,String title,String content,Timestamp m_postdata,String kind){
		 this.memberid=memberid;
		 this.title=title;
		 this.content=content;
		 this.m_postdata=m_postdata;
		 this.kind=kind; 
	 }
	 
	 public MailBean(int mailno,String memberid,String title,String content,Timestamp m_postdata,String kind){
		 this.mailno=mailno;
		 this.memberid=memberid;
		 this.title=title;
		 this.content=content;
		 this.m_postdata=m_postdata;
		 this.kind=kind; 
	 }
	
	
	public int getMailno() {
		return mailno;
	}
	public void setMailno(int mailno) {
		this.mailno = mailno;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public Timestamp getM_postdata() {
		return m_postdata;
	}

	public void setM_postdata(Timestamp m_postdata) {
		this.m_postdata = m_postdata;
	}
	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
}
