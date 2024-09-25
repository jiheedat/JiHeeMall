package com.shopping.dto;

// ��ǰ ������ ��� DTO Ŭ����
public class ProductDto {
    
    // ��ǰ ��ȣ (Primary Key)
    private int pNo;
    
    // ��ǰ �̸�
    private String pName;
    
    // ��ǰ ����
    private int price;
    
    // ���ε� ���� (���� ����)
    private int salePrice;
    
    // ��ǰ �̹��� ���
    private String pImg;
    
    // ��ǰ �� �̹��� ���
    private String pDetailImg;
    
    // ��ǰ ī�װ�
    private String category;
    
    // ���˵� ���� (õ ������ �޸��� ������ ����)
    private String setPrice;
    
	public ProductDto(){}
	public ProductDto(int pNo, String pName, int price, int salePrice, String pImg, String pDetailImg, String category,
			String setPrice) {
		super();
		this.pNo = pNo;
		this.pName = pName;
		this.price = price;
		this.salePrice = salePrice;
		this.pImg = pImg;
		this.pDetailImg = pDetailImg;
		this.category = category;
		this.setPrice = setPrice;
	}
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
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
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public String getpImg() {
		return pImg;
	}
	public void setpImg(String pImg) {
		this.pImg = pImg;
	}
	public String getpDetailImg() {
		return pDetailImg;
	}
	public void setpDetailImg(String pDetailImg) {
		this.pDetailImg = pDetailImg;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSetPrice() {
		return setPrice;
	}
	public void setSetPrice(String setPrice) {
		this.setPrice = setPrice;
	}
	
	@Override
	public String toString() {
		return "ProductDto [pNo=" + pNo + ", pName=" + pName + ", price=" + price + ", salePrice=" + salePrice
				+ ", pImg=" + pImg + ", pDetailImg=" + pDetailImg + ", category=" + category + ", setPrice=" + setPrice
				+ "]";
	}
}

	
