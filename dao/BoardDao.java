package com.shopping.dao;

import java.util.ArrayList;

import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

public interface BoardDao {
	
	// ����count -> ������������ ���ϱ�
	int getlastPageNum(int pno);
	
	// ������ü ��� + ����¡
	ArrayList<ReviewDto> getReviewList(int pageNum,int pno);
	
	// �����
	String getReviewDetail(int r_idx,String id);
	
	// ������
	void insertReview(ReviewDto dto);
	
	// ���Ǳ�count -> ������������ ���ϱ�
	int getInquiryLastPage(int pno);
	
	// ���Ǳ� ����¡ + ���
	ArrayList<InquiryDto> getInquiryList(int pageNum,int pno);
	
	

}
