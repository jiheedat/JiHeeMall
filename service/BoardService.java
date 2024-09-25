package com.shopping.service;

import java.util.ArrayList;

import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

public interface BoardService {

    // 리뷰 마지막 페이지 번호 구하기 (해당 상품 번호를 기준으로)
    int getLastPageNum(int pno);
    
    // 특정 상품에 대한 리뷰 목록 가져오기 + 페이징 처리 (페이지 번호와 상품 번호 사용)
    ArrayList<ReviewDto> getReviewList(int pageNum, int pno);
    
    // 특정 리뷰의 상세 정보 가져오기 (리뷰 인덱스와 사용자 ID 사용)
    String getReviewDetail(int r_idx, String id);
    
    // 문의글 마지막 페이지 번호 구하기 (해당 상품 번호를 기준으로)
    int getInquiryLastPage(int pno);
    
    // 특정 상품에 대한 문의 목록 가져오기 + 페이징 처리 (페이지 번호와 상품 번호 사용)
    ArrayList<InquiryDto> getInquiryList(int pageNum, int pno);
}
