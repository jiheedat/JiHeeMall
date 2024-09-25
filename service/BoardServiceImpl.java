package com.shopping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.BoardDao;
import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

@Service
public class BoardServiceImpl implements BoardService {

    // 리뷰&문의 관련 Dao
    @Autowired
    BoardDao dao;

    // 특정 상품의 리뷰 마지막 페이지 번호를 반환
    @Override
    public int getLastPageNum(int pno) {
        return dao.getlastPageNum(pno); 
    }

    // 특정 상품의 리뷰 목록을 반환 + 페이징 처리
    @Override
    public ArrayList<ReviewDto> getReviewList(int pageNum, int pno) {
        return dao.getReviewList(pageNum, pno); 
    }

    // 특정 리뷰의 상세 정보를 반환 (리뷰 인덱스와 사용자 ID 사용)
    @Override
    public String getReviewDetail(int r_idx, String id) {
        return dao.getReviewDetail(r_idx, id);
    }

    // 특정 상품의 문의글 마지막 페이지 번호를 반환
    @Override
    public int getInquiryLastPage(int pno) {
        return dao.getInquiryLastPage(pno); 
    }

    // 특정 상품의 문의글 목록을 반환 + 페이징 처리
    @Override
    public ArrayList<InquiryDto> getInquiryList(int pageNum, int pno) {
        return dao.getInquiryList(pageNum, pno); 
    }
}
