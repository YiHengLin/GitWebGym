package article.model;


import java.sql.Timestamp;


public class ArticleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private int ArticleNo;
	private String trainId;
	private Timestamp pubdate;
	private String title;
	private String content;
	

    public ArticleBean(String trainId,Timestamp pubdate,String title,String content ){
    	this.trainId=trainId;
    	this.pubdate=pubdate;
    	this.title=title;
    	this.content=content;
	}
    public ArticleBean(int ArticleNo,String trainId,Timestamp pubdate,String title,String content ){
    	super();
    	this.ArticleNo=ArticleNo;
    	this.trainId=trainId;
    	this.pubdate=pubdate;
    	this.title=title;
    	this.content=content;
	}
    
	public ArticleBean(){
		
	}
	
  
	
	public int getArticleNo() {
		return ArticleNo;
	}
	public void setArticleNo(int articleNo) {
		ArticleNo = articleNo;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public Timestamp getPubdate() {
		return pubdate;
	}
	public void setPubdate(Timestamp pubdate) {
		this.pubdate = pubdate;
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
	
	
	

}
