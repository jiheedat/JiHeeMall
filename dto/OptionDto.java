package com.shopping.dto;

// 상품 옵션 정보를 담는 Data Transfer Object (DTO) 클래스
public class OptionDto {

    // 상품 번호 (Primary Key, 상품과 연관된 번호)
    private int pNo;
    
    // 상품 옵션 (색상, 크기 등의 옵션을 저장)
    private String p_option;
    
    // 옵션 번호 (Primary Key, 옵션의 고유 번호)
    private int option_idx;

	public OptionDto() {}
	public OptionDto(int pNo, String p_option, int option_idx) {
		super();
		this.pNo = pNo;
		this.p_option = p_option;
		this.option_idx = option_idx;
	}
	
	public int getpNo() {
		return pNo;
	}
	public void setpNo(int pNo) {
		this.pNo = pNo;
	}
	public String getP_option() {
		return p_option;
	}
	public void setP_option(String p_option) {
		this.p_option = p_option;
	}
	public int getOption_idx() {
		return option_idx;
	}
	public void setOption_idx(int option_idx) {
		this.option_idx = option_idx;
	}
	
	@Override
	public String toString() {
		return "OptionDto [pNo=" + pNo + ", p_option=" + p_option + ", option_idx=" + option_idx + "]";
	}
}
