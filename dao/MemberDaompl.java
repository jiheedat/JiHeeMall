package com.shopping.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dto.MemberDto;

@Repository
public class MemberDaompl implements MemberDao {

    @Autowired
    SqlSession session;

    // ȸ������: ȸ�� ������ DB�� ����
    @Override
    public void inertMember(MemberDto dto) {
        session.insert("memberMapper.insertMember", dto);  
    }

    // ID�� �̿��Ͽ� ��й�ȣ ��������
    @Override
    public String getPwByDB(String id) {
        String getPwByid = session.selectOne("memberMapper.getPwById", id);  
        return getPwByid;
    }

    // ��й�ȣ�� �̿��Ͽ� ID ��������
    @Override
    public String getIdByDB(String pw) {
        String getIdByDB = session.selectOne("memberMapper.getIdByPw", pw);  
        return getIdByDB;
    }

    // ���̵� �ߺ� üũ: ���̵� DB�� �����ϴ��� Ȯ��
    @Override
    public String duplication(String id) {
        return session.selectOne("memberMapper.duplication", id);  
    }
}
