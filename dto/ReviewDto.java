package com.shopping.dto;

// ���� ������ ��� DTO Ŭ����
public class ReviewDto {
    
    // ������ ���� ��ȣ (Primary Key)
    private int r_idx;
    
    // ���䰡 �ۼ��� ��ǰ ��ȣ
    private int pno;
    
    // ���� ����
    private String title;
    
    // ���� ����
    private String content;
    
    // ���� �ۼ��� ID
    private String id;
    
    // ���� �ۼ� ��¥
    private String writedate;
    
    // ���� ����
    private int grade;
    
    // ��ȸ�� (�ش� ������ ��ȸ�� Ƚ��)
    private int hitcount;
	
	public ReviewDto() {}
	public ReviewDto(int r_idx, int pno, String title, String content, String id, String writedate, int grade,
			int hitcount) {
		this.r_idx = r_idx;
		this.pno = pno;
		this.title = title;
		this.content = content;
		this.id = id;
		this.writedate = writedate;
		this.grade = grade;
		this.hitcount = hitcount;
	}
	
	public int getR_idx() {
		return r_idx;
	}
	public void setR_idx(int r_idx) {
		this.r_idx = r_idx;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	
	@Override
	public String toString() {
		return "ReviewDto [r_idx=" + r_idx + ", pno=" + pno + ", title=" + title + ", content=" + content + ", id=" + id
				+ ", writedate=" + writedate + ", grade=" + grade + ", hitcount=" + hitcount + "]";
	}
}
