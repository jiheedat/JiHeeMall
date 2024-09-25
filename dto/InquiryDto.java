package com.shopping.dto;

// 상품 문의 정보를 담는 DTO 클래스
public class InquiryDto {

    // 문의 번호 (Primary Key, 고유한 문의 식별자)
    private int inquiry_idx;
    
    // 문의에 대한 답글 번호 (0일 경우 원글, 답글일 경우 참조하는 문의 번호)
    private int base_idx;
    
    // 문의 제목
    private String title;
    
    // 상품 번호 (해당 문의가 관련된 상품의 번호)
    private int pno;
    
    // 문의 내용
    private String content;
    
    // 작성자 ID (문의 작성자)
    private String id;
    
    // 작성일시 (문의 작성 시간)
    private String writedate;
    
    // 조회수 (해당 문의가 조회된 횟수)
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
