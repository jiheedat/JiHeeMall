package com.shopping.dto;

// ��ǰ �ɼ� ������ ��� Data Transfer Object (DTO) Ŭ����
public class OptionDto {

    // ��ǰ ��ȣ (Primary Key, ��ǰ�� ������ ��ȣ)
    private int pNo;
    
    // ��ǰ �ɼ� (����, ũ�� ���� �ɼ��� ����)
    private String p_option;
    
    // �ɼ� ��ȣ (Primary Key, �ɼ��� ���� ��ȣ)
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
