package com.shopping.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dto.MemberDto;

@Repository
public class MemberDaompl implements MemberDao {

    @Autowired
    SqlSession session;

    // 회원가입: 회원 정보를 DB에 삽입
    @Override
    public void inertMember(MemberDto dto) {
        session.insert("memberMapper.insertMember", dto);  
    }

    // ID를 이용하여 비밀번호 가져오기
    @Override
    public String getPwByDB(String id) {
        String getPwByid = session.selectOne("memberMapper.getPwById", id);  
        return getPwByid;
    }

    // 비밀번호를 이용하여 ID 가져오기
    @Override
    public String getIdByDB(String pw) {
        String getIdByDB = session.selectOne("memberMapper.getIdByPw", pw);  
        return getIdByDB;
    }

    // 아이디 중복 체크: 아이디가 DB에 존재하는지 확인
    @Override
    public String duplication(String id) {
        return session.selectOne("memberMapper.duplication", id);  
    }
}
