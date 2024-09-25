package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.MemberDao;
import com.shopping.dto.MemberDto;

// MemberService의 구현 클래스
@Service
public class MemberServiceImpl implements MemberService {

    // 회원관련 Dao
    @Autowired
    MemberDao dao;
    
    // 회원 정보 등록 (회원 가입)
    @Override
    public void inertMember(MemberDto dto) {
        dao.inertMember(dto);  
    }

    // ID로 비밀번호 조회 
    @Override
    public String getPwById(String id) {
        return null;  
    }

    // 로그인 체크 (ID와 비밀번호를 비교)
    @Override
    public boolean loginCheck(String id, String pw) {
        String pwByDB = dao.getPwByDB(id);  // DAO를 통해 DB에서 비밀번호 조회
        
        // 입력된 비밀번호가 DB의 비밀번호와 일치하는지 확인
        if (pw != null && pw.equals(pwByDB)) {
            return true;  // 비밀번호 일치 시 로그인 성공
        }
        return false;  // 일치하지 않으면 로그인 실패
    }

    // ID 중복 체크
    @Override
    public String duplication(String id) {
        return dao.duplication(id);  // DAO를 통해 ID 중복 여부 확인
    }
}
