package com.shopping.service;

import com.shopping.dto.MemberDto;

public interface MemberService {
	
	// ȸ������
	void inertMember(MemberDto dto);
	
	// id�� �̿��Ͽ� pw ��������
	String getPwById(String id);
	
	// �α���üũ
	boolean loginCheck(String id,String pw);
	
	// ���̵� �ߺ�üũ
	String duplication(String id);
}
