package com.shopping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.BoardDao;
import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

@Service
public class BoardServiceImpl implements BoardService {

    // ����&���� ���� Dao
    @Autowired
    BoardDao dao;

    // Ư�� ��ǰ�� ���� ������ ������ ��ȣ�� ��ȯ
    @Override
    public int getLastPageNum(int pno) {
        return dao.getlastPageNum(pno); 
    }

    // Ư�� ��ǰ�� ���� ����� ��ȯ + ����¡ ó��
    @Override
    public ArrayList<ReviewDto> getReviewList(int pageNum, int pno) {
        return dao.getReviewList(pageNum, pno); 
    }

    // Ư�� ������ �� ������ ��ȯ (���� �ε����� ����� ID ���)
    @Override
    public String getReviewDetail(int r_idx, String id) {
        return dao.getReviewDetail(r_idx, id);
    }

    // Ư�� ��ǰ�� ���Ǳ� ������ ������ ��ȣ�� ��ȯ
    @Override
    public int getInquiryLastPage(int pno) {
        return dao.getInquiryLastPage(pno); 
    }

    // Ư�� ��ǰ�� ���Ǳ� ����� ��ȯ + ����¡ ó��
    @Override
    public ArrayList<InquiryDto> getInquiryList(int pageNum, int pno) {
        return dao.getInquiryList(pageNum, pno); 
    }
}
