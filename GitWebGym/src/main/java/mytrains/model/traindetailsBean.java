package mytrains.model; 

import java.io.Serializable;

public class traindetailsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String traindetailno;           // 訓練序號
	private String trainno;   			    // 訓練外部序號
	private String oldtraintitle;
	private String traintitle;   			// 訓練項目
	private String trainset;       			// 訓練組數
	private String trainweight;    			// 訓練重量	
	
	public traindetailsBean() {
	}
	
	//新增訓練項目時使用
	public traindetailsBean(String oldtraintitle, String trainno, String traintitle, String trainset, String trainweight) {
		super();
		this.oldtraintitle = oldtraintitle;
		this.trainno = trainno;
		this.traintitle = traintitle;
		this.trainset = trainset;
		this.trainweight = trainweight;
	}
	
	//刪除訓練項目時使用
	public traindetailsBean(String trainno, String traintitle) {
		super();
		this.trainno = trainno;
		this.traintitle = traintitle;
	}

	public traindetailsBean(String trainno, String traintitle, String trainset, String trainweight) {
		super();
		this.trainno = trainno;
		this.traintitle = traintitle;
		this.trainset = trainset;
		this.trainweight = trainweight;
	}
	
	public traindetailsBean(String traindetailno) {
		this.traindetailno = traindetailno;
	}
	
	public String getTraindetailno() {
		return traindetailno;
	}

	public void setTraindetailno(String traindetailno) {
		this.traindetailno = traindetailno;
	}

	public String toString() {
		return "["+trainno+","+traintitle+","+trainset+","+trainweight+"]";
	}

	public String getTrainno() {
		return trainno;
	}

	public String getOldtraintitle() {
		return oldtraintitle;
	}

	public void setOldtraintitle(String oldtraintitle) {
		this.oldtraintitle = oldtraintitle;
	}

	public void setTrainno(String trainno) {
		this.trainno = trainno;
	}

	public String getTraintitle() {
		return traintitle;
	}

	public void setTraintitle(String traintitle) {
		this.traintitle = traintitle;
	}

	public String getTrainset() {
		return trainset;
	}

	public void setTrainset(String trainset) {
		this.trainset = trainset;
	}

	public String getTrainweight() {
		return trainweight;
	}

	public void setTrainweight(String trainweight) {
		this.trainweight = trainweight;
	}

	



	
}