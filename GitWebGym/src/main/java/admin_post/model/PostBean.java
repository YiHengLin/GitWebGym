package admin_post.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.*;
//import java.sql.Blob;

public class PostBean implements Serializable {

	private int postno;
	private String memberid;
	private String title;
	private String content;
	private Timestamp postdate;
	private String kind;

	// 必需的不帶參數建構子
	public PostBean() {

	}

	// getter setter 若以系統生成，修改刪除也要由系統管理，不然系統不認得

	@Override
	public String toString() {
		return "PostBean [postno=" + postno + ", memberid=" + memberid + ", title=" + title + ", content=" + content
				+ ", postdate=" + postdate + ", kind=" + kind + "]";
	}

	public PostBean(String memberid, String title, String content, Timestamp postdate, String kind) {
		this.memberid = memberid;
		this.title = title;
		this.content = content;
		this.postdate = postdate;
		this.kind = kind;
	}

	public PostBean(int postno, String memberid, String title, String content, Timestamp postdate, String kind) {
		this.postno = postno;
		this.memberid = memberid;
		this.title = title;
		this.content = content;
		this.postdate = postdate;
		this.kind = kind;
	}

	public int getPostno() {
		return postno;
	}

	public void setPostno(int postno) {
		this.postno = postno;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
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

	public Timestamp getPostdate() {
		return postdate;
	}

	public void setPostdate(Timestamp postdate) {
		this.postdate = postdate;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

}
