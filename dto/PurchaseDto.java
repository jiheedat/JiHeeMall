package com.shopping.dto;

// 구매 정보를 담는 DTO 클래스
public class PurchaseDto {
    
    // 주문 번호 (Primary Key)
    private int order_idx;
    
    // 상품 이미지 경로
    private String pImg;
    
    // 상품 이름
    private String pName;
    
    // 상품 가격
    private int price;
    
    // 상품 수량
    private int qty;
    
    public PurchaseDto() {}
	public PurchaseDto(int order_idx, String pImg, String pName, int price, int qty) {
		super();
		this.order_idx = order_idx;
		this.pImg = pImg;
		this.pName = pName;
		this.price = price;
		this.qty = qty;
	}

	public int getOrder_idx() {
		return order_idx;
	}

	public void setOrder_idx(int order_idx) {
		this.order_idx = order_idx;
	}

	public String getpImg() {
		return pImg;
	}

	public void setpImg(String pImg) {
		this.pImg = pImg;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "PurchaseDto [order_idx=" + order_idx + ", pImg=" + pImg + ", pName=" + pName + ", price=" + price
				+ ", qty=" + qty + "]";
	}
	
}
