package com.shopping.dto;

// ȸ�� ������ ��� DTO Ŭ����
public class MemberDto {

    // ȸ�� ID (Primary Key)
    private String id;
    
    // ȸ�� ��й�ȣ
    private String pw;
    
    // �����ȣ
    private String postCode;
    
    // �⺻ �ּ� (�ּ� 1)
    private String address1;
    
    // �� �ּ� (�ּ� 2)
    private String address2;
    
    // ȸ�� �̸�
    private String name;
    
    // ȸ�� ��ȭ��ȣ
    private String phone;
    
    // ȸ�� �̸��� �ּ�
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
