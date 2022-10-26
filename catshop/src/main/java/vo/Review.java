package vo;

public class Review {
	private int reviewNo;
	private int goodNo;
	private String id;
	private String reviewContent;
	private String reviewPw;
	private String updateDate;
	private String createDate;
	public int getReviewNo() {
		return reviewNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public int getGoodNo() {
		return goodNo;
	}
	public void setGoodNo(int goodNo) {
		this.goodNo = goodNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewPw() {
		return reviewPw;
	}
	public void setReviewPw(String reviewPw) {
		this.reviewPw = reviewPw;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", goodNo=" + goodNo + ", id=" + id + ", reviewContent=" + reviewContent
				+ ", reviewPw=" + reviewPw + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}

	
	
}
