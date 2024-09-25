package com.shopping.dto;

public class CartViewDto {
	// 장바구니 고유번호 
	private int cart_idx;
	
	// 구매자
	private String id;
	
	// 상품옵션 고유값
	private int option_idx;
	
	// 선택 수량
	private int qty;
	
	// 상품이미지 주소
	private String pImg;
	
	// 상품명
	private String pName;
	
	// 상품 옵션
	private String p_option;
	
	// 가격
	private int price;
	
	// 천 단위,로 구별된 가격 값
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
