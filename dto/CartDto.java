package com.shopping.dto;

public class CartDto {
	// 장바구니의 고유 인덱스
    private int cart_idx;
    
    // 구매자의 ID
    private String id;
    
    // 선택한 옵션의 인덱스
    private int option_idx; 

    // 선택한 상품의 수량
    private int qty; 
    
	public CartDto() {}
	public CartDto(int cart_idx, String id, int option_idx, int qty) {
		super();
		this.cart_idx = cart_idx;
		this.id = id;
		this.option_idx = option_idx;
		this.qty = qty;
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
	
	@Override
	public String toString() {
		return "CartDto [cart_idx=" + cart_idx + ", id=" + id + ", option_idx=" + option_idx + ", qty=" + qty + "]";
	}
}
