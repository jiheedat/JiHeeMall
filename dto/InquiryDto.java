package com.shopping.dto;

// ��ǰ ���� ������ ��� DTO Ŭ����
public class InquiryDto {

    // ���� ��ȣ (Primary Key, ������ ���� �ĺ���)
    private int inquiry_idx;
    
    // ���ǿ� ���� ��� ��ȣ (0�� ��� ����, ����� ��� �����ϴ� ���� ��ȣ)
    private int base_idx;
    
    // ���� ����
    private String title;
    
    // ��ǰ ��ȣ (�ش� ���ǰ� ���õ� ��ǰ�� ��ȣ)
    private int pno;
    
    // ���� ����
    private String content;
    
    // �ۼ��� ID (���� �ۼ���)
    private String id;
    
    // �ۼ��Ͻ� (���� �ۼ� �ð�)
    private String writedate;
    
    // ��ȸ�� (�ش� ���ǰ� ��ȸ�� Ƚ��)
    private int hitcount;

	public InquiryDto() {}
	public InquiryDto(int inquiry_idx, int base_idx, String title, int pno, String content, String id, String writedate,
			int hitcount) {
		this.inquiry_idx = inquiry_idx;
		this.base_idx = base_idx;
		this.title = title;
		this.pno = pno;
		this.content = content;
		this.id = id;
		this.writedate = writedate;
		this.hitcount = hitcount;
	}
	
	public int getInquiry_idx() {
		return inquiry_idx;
	}
	public void setInquiry_idx(int inquiry_idx) {
		this.inquiry_idx = inquiry_idx;
	}
	public int getBase_idx() {
		return base_idx;
	}
	public void setBase_idx(int base_idx) {
		this.base_idx = base_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	
	@Override
	public String toString() {
		return "InquiryDto [inquiry_idx=" + inquiry_idx + ", base_idx=" + base_idx + ", title=" + title + ", pno=" + pno
				+ ", content=" + content + ", id=" + id + ", writedate=" + writedate + ", hitcount=" + hitcount + "]";
	}
	
}
