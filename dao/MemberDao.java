package com.shopping.dao;

import com.shopping.dto.MemberDto;

public interface MemberDao {
	
	// ȸ������
	void inertMember(MemberDto dto);
	
	// id�� �̿��Ͽ� pw ��������
	String getPwByDB(String id);
	
	// pw�� �̿��Ͽ� id��������
	String getIdByDB(String pw);

	// ���̵� �ߺ�üũ
	String duplication(String id);
}