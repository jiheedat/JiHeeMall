package com.shopping.dto;

public class CartViewDto {
	// ��ٱ��� ������ȣ 
	private int cart_idx;
	
	// ������
	private String id;
	
	// ��ǰ�ɼ� ������
	private int option_idx;
	
	// ���� ����
	private int qty;
	
	// ��ǰ�̹��� �ּ�
	private String pImg;
	
	// ��ǰ��
	private String pName;
	
	// ��ǰ �ɼ�
	private String p_option;
	
	// ����
	private int price;
	
	// õ ����,�� ������ ���� ��
	private String setPrice;
	
	public CartViewDto() {}

	public CartViewDto(int cart_idx, String id, int option_idx, int qty, String pImg, String pName, String p_option,
			int price, String setPrice) {
		super();
		this.cart_idx = cart_idx;
		this.id = id;
		this.option_idx = option_idx;
		this.qty = qty;
		this.pImg = pImg;
		this.pName = pName;
		this.p_option = p_option;
		this.price = price;
		this.setPrice = setPrice;
	}

	public int getCart_idx() {
		return cart_idx;
	}

	public void setCart_idx(int cart_idx) {
		this.cart_idx = cart_idx;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOption_idx() {
		return option_idx;
	}

	public void setOption_idx(int option_idx) {
		this.option_idx = option_idx;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

	public String getP_option() {
		return p_option;
	}

	public void setP_option(String p_option) {
		this.p_option = p_option;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getSetPrice() {
		return setPrice;
	}

	public void setSetPrice(String setPrice) {
		this.setPrice = setPrice;
	}

	@Override
	public String toString() {
		return "CartViewDto [cart_idx=" + cart_idx + ", id=" + id + ", option_idx=" + option_idx + ", qty=" + qty
				+ ", pImg=" + pImg + ", pName=" + pName + ", p_option=" + p_option + ", price=" + price + ", setPrice="
				+ setPrice + "]";
	}
}
