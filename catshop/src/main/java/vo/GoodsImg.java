package vo;

public class GoodsImg {
	private int goodsNo;
	private String filename;
	private String originalFilename;
	private String contentType;
	private String createDate;
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "GoodsImg [goodsNo=" + goodsNo + ", filename=" + filename + ", originalFilename=" + originalFilename
				+ ", contentType=" + contentType + ", createDate=" + createDate + "]";
	}
	
	
}
