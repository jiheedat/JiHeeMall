package com.shopping.dto;

// 회원 정보를 담는 DTO 클래스
public class MemberDto {

    // 회원 ID (Primary Key)
    private String id;
    
    // 회원 비밀번호
    private String pw;
    
    // 우편번호
    private String postCode;
    
    // 기본 주소 (주소 1)
    private String address1;
    
    // 상세 주소 (주소 2)
    private String address2;
    
    // 회원 이름
    private String name;
    
    // 회원 전화번호
    private String phone;
    
    // 회원 이메일 주소
    private String email;

	public MemberDto() {}

	public MemberDto(String id, String pw, String postCode, String address1, String address2, String name, String phone,
			String email) {
		super();
		this.id = id;
		this.pw = pw;
		this.postCode = postCode;
		this.address1 = address1;
		this.address2 = address2;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", postCode=" + postCode + ", address1=" + address1
				+ ", address2=" + address2 + ", name=" + name + ", phone=" + phone + ", email=" + email + "]";
	}
	
	
	
	
	
	
	
}
