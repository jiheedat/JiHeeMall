package com.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.MemberDao;
import com.shopping.dto.MemberDto;

// MemberService�� ���� Ŭ����
@Service
public class MemberServiceImpl implements MemberService {

    // ȸ������ Dao
    @Autowired
    MemberDao dao;
    
    // ȸ�� ���� ��� (ȸ�� ����)
    @Override
    public void inertMember(MemberDto dto) {
        dao.inertMember(dto);  
    }

    // ID�� ��й�ȣ ��ȸ 
    @Override
    public String getPwById(String id) {
        return null;  
    }

    // �α��� üũ (ID�� ��й�ȣ�� ��)
    @Override
    public boolean loginCheck(String id, String pw) {
        String pwByDB = dao.getPwByDB(id);  // DAO�� ���� DB���� ��й�ȣ ��ȸ
        
        // �Էµ� ��й�ȣ�� DB�� ��й�ȣ�� ��ġ�ϴ��� Ȯ��
        if (pw != null && pw.equals(pwByDB)) {
            return true;  // ��й�ȣ ��ġ �� �α��� ����
        }
        return false;  // ��ġ���� ������ �α��� ����
    }

    // ID �ߺ� üũ
    @Override
    public String duplication(String id) {
        return dao.duplication(id);  // DAO�� ���� ID �ߺ� ���� Ȯ��
    }
}
