package vo;

public class Orders {
	private int orderNo;
	private int goodsNo;
	private String customerId;
	private int oderQuanitity;
	private int oderPrice;
	private String orderAddress;
	private String orderState;
	private String updateDate;
	private String createDate;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
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
	public int getOderQuanitity() {
		return oderQuanitity;
	}
	public void setOderQuanitity(int oderQuanitity) {
		this.oderQuanitity = oderQuanitity;
	}
	public int getOderPrice() {
		return oderPrice;
	}
	public void setOderPrice(int oderPrice) {
		this.oderPrice = oderPrice;
	}
	public String getOrderAddress() {
		return orderAddress;
	}
	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
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
		return "Orders [orderNo=" + orderNo + ", goodsNo=" + goodsNo + ", customerId=" + customerId + ", oderQuanitity="
				+ oderQuanitity + ", oderPrice=" + oderPrice + ", orderAddress=" + orderAddress + ", orderState="
				+ orderState + ", updateDate=" + updateDate + ", createDate=" + createDate + "]";
	}
	
	
}
