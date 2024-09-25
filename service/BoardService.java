package com.shopping.service;

import java.util.ArrayList;

import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

public interface BoardService {

    // ���� ������ ������ ��ȣ ���ϱ� (�ش� ��ǰ ��ȣ�� ��������)
    int getLastPageNum(int pno);
    
    // Ư�� ��ǰ�� ���� ���� ��� �������� + ����¡ ó�� (������ ��ȣ�� ��ǰ ��ȣ ���)
    ArrayList<ReviewDto> getReviewList(int pageNum, int pno);
    
    // Ư�� ������ �� ���� �������� (���� �ε����� ����� ID ���)
    String getReviewDetail(int r_idx, String id);
    
    // ���Ǳ� ������ ������ ��ȣ ���ϱ� (�ش� ��ǰ ��ȣ�� ��������)
    int getInquiryLastPage(int pno);
    
    // Ư�� ��ǰ�� ���� ���� ��� �������� + ����¡ ó�� (������ ��ȣ�� ��ǰ ��ȣ ���)
    ArrayList<InquiryDto> getInquiryList(int pageNum, int pno);
}
