package vo;

public class Cart {
	private int goodsNo;
	private String customerId;
	private int cartTotalcount;
	private int cartTotalprice;
	private String updateDate;
	private String createDate;
	public int getGoodsNo() {
		return goodsNo;
	}
	public void setGoodsNo(int goodsNo) {
		this.goodsNo = goodsNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public int getCartTotalcount() {
		return cartTotalcount;
	}
	public void setCartTotalcount(int cartTotalcount) {
		this.cartTotalcount = cartTotalcount;
	}
	public int getCartTotalprice() {
		return cartTotalprice;
	}
	public void setCartTotalprice(int cartTotalprice) {
		this.cartTotalprice = cartTotalprice;
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
		return "Cart [goodsNo=" + goodsNo + ", customerId=" + customerId + ", cartTotalcount=" + cartTotalcount
				+ ", cartTotalprice=" + cartTotalprice + ", updateDate=" + updateDate + ", createDate=" + createDate
				+ "]";
	}


	
}
