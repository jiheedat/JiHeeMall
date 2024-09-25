package com.shopping.service;

import com.shopping.dto.MemberDto;

public interface MemberService {
	
	// 회원가입
	void inertMember(MemberDto dto);
	
	// id를 이용하여 pw 가져오기
	String getPwById(String id);
	
	// 로그인체크
	boolean loginCheck(String id,String pw);
	
	// 아이디 중복체크
	String duplication(String id);
}
