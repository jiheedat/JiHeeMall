package com.shopping.dao;

import java.util.ArrayList;

import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

public interface BoardDao {
	
	// 리뷰count -> 마지막페이지 구하기
	int getlastPageNum(int pno);
	
	// 리뷰전체 출력 + 페이징
	ArrayList<ReviewDto> getReviewList(int pageNum,int pno);
	
	// 리뷰상세
	String getReviewDetail(int r_idx,String id);
	
	// 리뷰등록
	void insertReview(ReviewDto dto);
	
	// 문의글count -> 마지막페이지 구하기
	int getInquiryLastPage(int pno);
	
	// 문의글 페이징 + 출력
	ArrayList<InquiryDto> getInquiryList(int pageNum,int pno);
	
	

}
