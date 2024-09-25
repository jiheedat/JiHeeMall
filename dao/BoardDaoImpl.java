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

    // ���� ������ ������ ���ϱ� (����¡ ó��)
    @Override
    public int getlastPageNum(int pno) {
        int reviewCount = session.selectOne("boardMapper.reviewCount", pno);  // �ش� ��ǰ�� ���� ���� ��ȸ
        int lastPage = reviewCount / 5;  // �� �������� 5���� ���並 ǥ��
        lastPage += (reviewCount % 5 > 0 ? 1 : 0);  // ������ ���䰡 ������ ������ ������ �߰�
        
        return lastPage;
    }

    // ���� ��� �������� + ����¡ ó��
    @Override
    public ArrayList<ReviewDto> getReviewList(int pageNum, int pno) {
        // ������ ���� ��� (�� �������� 5���� ����)
        int end = pageNum * 5;
        int start = end - 4;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("end", end);  // ����¡ �� ��ȣ
        params.put("start", start);  // ����¡ ���� ��ȣ
        params.put("pno", pno);  // ��ǰ ��ȣ

        List<ReviewDto> list = session.selectList("boardMapper.getReviewList", params);  // ���� ��� ��ȸ
        ArrayList<ReviewDto> list2 = new ArrayList<>();
        list2.addAll(list);  // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ

        return list2;
    }

    // �� ���� ���� ��������
    @Override
    public String getReviewDetail(int r_idx, String id) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("r_idx", r_idx);  // ���� ��ȣ
        params.put("id", id);  // ����� ID

        String reviewDetail = session.selectOne("boardMapper.getReviewDetail", params);  // ���� �� ���� ��ȸ
        return reviewDetail;
    }

    // ���� ���
    @Override
    public void insertReview(ReviewDto dto) {
        session.insert("boardMapper.insertReview", dto);  
    }

    // ���Ǳ� ������ ������ ���ϱ� (����¡ ó��)
    @Override
    public int getInquiryLastPage(int pno) {
        int inquiryCount = session.selectOne("boardMapper.inquiryCount", pno);  // �ش� ��ǰ�� ���Ǳ� ���� ��ȸ
        int inquiryLastPage = inquiryCount / 3;  // �� �������� 3���� ���Ǳ� ǥ��
        inquiryLastPage += (inquiryCount % 3 > 0 ? 1 : 0);  // ������ ���Ǳ��� ������ ������ ������ �߰�

        return inquiryLastPage;
    }

    // ���Ǳ� ��� �������� + ����¡ ó��
    @Override
    public ArrayList<InquiryDto> getInquiryList(int pageNum, int pno) {
        // ������ ���� ��� (�� �������� 4���� ���Ǳ�)
        int end = pageNum * 4;
        int start = end - 3;

        HashMap<String, Integer> params = new HashMap<>();
        params.put("end", end);  // ����¡ �� ��ȣ
        params.put("start", start);  // ����¡ ���� ��ȣ
        params.put("pno", pno);  // ��ǰ ��ȣ

        List<InquiryDto> list = session.selectList("boardMapper.getInquiryList", params);  // ���Ǳ� ��� ��ȸ
        ArrayList<InquiryDto> list2 = new ArrayList<>();
        list2.addAll(list);  // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ

        return list2;
    }
}
