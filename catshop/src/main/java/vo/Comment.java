package vo;

public class Comment {
	private int commentNo;
	private int noticeNo;
	private String id;
	private String commentContent;
	private String commentPw;
	private String createDate;
	private String updateDate;
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentPw() {
		return commentPw;
	}
	public void setCommentPw(String commentPw) {
		this.commentPw = commentPw;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", noticeNo=" + noticeNo + ", id=" + id + ", commentContent="
				+ commentContent + ", commentPw=" + commentPw + ", createDate=" + createDate + ", updateDate="
				+ updateDate + "]";
	}
	
	
	
}
