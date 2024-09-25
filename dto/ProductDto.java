package com.shopping.dto;

// 상품 정보를 담는 DTO 클래스
public class ProductDto {
    
    // 상품 번호 (Primary Key)
    private int pNo;
    
    // 상품 이름
    private String pName;
    
    // 상품 가격
    private int price;
    
    // 할인된 가격 (세일 가격)
    private int salePrice;
    
    // 상품 이미지 경로
    private String pImg;
    
    // 상품 상세 이미지 경로
    private String pDetailImg;
    
    // 상품 카테고리
    private String category;
    
    // 포맷된 가격 (천 단위로 콤마를 포함한 가격)
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

	
