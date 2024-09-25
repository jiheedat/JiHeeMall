package com.shopping.dto;

public class CartDto {
	// ��ٱ����� ���� �ε���
    private int cart_idx;
    
    // �������� ID
    private String id;
    
    // ������ �ɼ��� �ε���
    private int option_idx; 

    // ������ ��ǰ�� ����
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
