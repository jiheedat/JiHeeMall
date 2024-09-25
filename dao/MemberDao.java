package com.shopping.dao;

import com.shopping.dto.MemberDto;

public interface MemberDao {
	
	// 회원가입
	void inertMember(MemberDto dto);
	
	// id를 이용하여 pw 가져오기
	String getPwByDB(String id);
	
	// pw를 이용하여 id가져오기
	String getIdByDB(String pw);

	// 아이디 중복체크
	String duplication(String id);
}