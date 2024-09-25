package com.shopping.dto;

// ���� ������ ��� DTO Ŭ����
public class PurchaseDto {
    
    // �ֹ� ��ȣ (Primary Key)
    private int order_idx;
    
    // ��ǰ �̹��� ���
    private String pImg;
    
    // ��ǰ �̸�
    private String pName;
    
    // ��ǰ ����
    private int price;
    
    // ��ǰ ����
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
