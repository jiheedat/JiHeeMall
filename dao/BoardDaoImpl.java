package com.shopping.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dto.InquiryDto;
import com.shopping.dto.ReviewDto;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    SqlSession session;

    // 리뷰 마지막 페이지 구하기 (페이징 처리)
    @Override
    public int getlastPageNum(int pno) {
        int reviewCount = session.selectOne("boardMapper.reviewCount", pno);  // 해당 상품의 리뷰 개수 조회
        int lastPage = reviewCount / 5;  // 한 페이지에 5개의 리뷰를 표시
        lastPage += (reviewCount % 5 > 0 ? 1 : 0);  // 나머지 리뷰가 있으면 마지막 페이지 추가
        
        return lastPage;
    }

    // 리뷰 목록 가져오기 + 페이징 처리
    @Override
    public ArrayList<ReviewDto> getReviewList(int pageNum, int pno) {
        // 페이지 범위 계산 (한 페이지에 5개의 리뷰)
        int end = pageNum * 5;
        int start = end - 4;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("end", end);  // 페이징 끝 번호
        params.put("start", start);  // 페이징 시작 번호
        params.put("pno", pno);  // 상품 번호

        List<ReviewDto> list = session.selectList("boardMapper.getReviewList", params);  // 리뷰 목록 조회
        ArrayList<ReviewDto> list2 = new ArrayList<>();
        list2.addAll(list);  // List를 ArrayList로 변환하여 반환

        return list2;
    }

    // 상세 리뷰 정보 가져오기
    @Override
    public String getReviewDetail(int r_idx, String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("r_idx", r_idx);  // 리뷰 번호
        params.put("id", id);  // 사용자 ID

        String reviewDetail = session.selectOne("boardMapper.getReviewDetail", params);  // 리뷰 상세 정보 조회
        return reviewDetail;
    }

    // 리뷰 등록
    @Override
    public void insertReview(ReviewDto dto) {
        session.insert("boardMapper.insertReview", dto);  
    }

    // 문의글 마지막 페이지 구하기 (페이징 처리)
    @Override
    public int getInquiryLastPage(int pno) {
        int inquiryCount = session.selectOne("boardMapper.inquiryCount", pno);  // 해당 상품의 문의글 개수 조회
        int inquiryLastPage = inquiryCount / 3;  // 한 페이지에 3개의 문의글 표시
        inquiryLastPage += (inquiryCount % 3 > 0 ? 1 : 0);  // 나머지 문의글이 있으면 마지막 페이지 추가

        return inquiryLastPage;
    }

    // 문의글 목록 가져오기 + 페이징 처리
    @Override
    public ArrayList<InquiryDto> getInquiryList(int pageNum, int pno) {
        // 페이지 범위 계산 (한 페이지에 4개의 문의글)
        int end = pageNum * 4;
        int start = end - 3;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("end", end);  // 페이징 끝 번호
        params.put("start", start);  // 페이징 시작 번호
        params.put("pno", pno);  // 상품 번호

        List<InquiryDto> list = session.selectList("boardMapper.getInquiryList", params);  // 문의글 목록 조회
        ArrayList<InquiryDto> list2 = new ArrayList<>();
        list2.addAll(list);  // List를 ArrayList로 변환하여 반환

        return list2;
    }
}
