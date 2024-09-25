package com.shopping.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;
import com.shopping.dto.ReviewDto;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    SqlSession sqlSession;
    
    // 검색어로 상품 목록 가져오기
    @Override
    public ArrayList<ProductDto> getSearchProduct(String search) {
        List<ProductDto> dto = sqlSession.selectList("productMapper.getSearchProduct", search); // 검색 결과 조회
        ArrayList<ProductDto> list = new ArrayList<ProductDto>();
        list.addAll(dto); // List를 ArrayList로 변환하여 반환
        
        return list;
    }
    
    // 전체 상품 목록 가져오기
    @Override
    public ArrayList<ProductDto> getProductList() {
        List<ProductDto> list = sqlSession.selectList("productMapper.getProductDtoList"); // 전체 상품 목록 조회
        ArrayList<ProductDto> list2 = new ArrayList<ProductDto>();
        list2.addAll(list); // List를 ArrayList로 변환하여 반환
        
        return list2;
    }
    
    // 상품 번호로 특정 상품 정보 가져오기
    @Override
    public ProductDto getProductByPno(int pNo) {
        ProductDto dto = sqlSession.selectOne("productMapper.getProductByPno", pNo); // 상품 정보 조회
        return dto;
    }

    // 상품 번호로 옵션 목록 가져오기
    @Override
    public ArrayList<OptionDto> getProductOptionByPno(int pNo) {
        List<OptionDto> list = sqlSession.selectList("productMapper.getOption", pNo); // 옵션 목록 조회
        ArrayList<OptionDto> option = new ArrayList<OptionDto>();
        option.addAll(list); // List를 ArrayList로 변환하여 반환
        
        return option;
    }

    // 특정 상품에 대한 찜 여부 확인
    @Override
    public int getZzim(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // 상품 번호
        map.put("id", id);   // 사용자 ID
        Integer ret = sqlSession.selectOne("productMapper.getZzim", map); // 찜 여부 확인
        
        return ret;
    }

    // 찜 등록
    @Override
    public void zzimInsert(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // 상품 번호
        map.put("id", id);   // 사용자 ID
        
        sqlSession.insert("productMapper.zzim", map); // 찜 등록
    }

    // 찜 해제
    @Override
    public void deleteZzim(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // 상품 번호
        map.put("id", id);   // 사용자 ID
        
        sqlSession.delete("productMapper.deleteZzim", map); // 찜 삭제
    }

    // 상품 리뷰 목록 가져오기
    @Override
    public ArrayList<ReviewDto> getReview(int pNo) {
        List<ReviewDto> list = sqlSession.selectList("productMapper.getReview", pNo); // 리뷰 목록 조회
        ArrayList<ReviewDto> list2 = new ArrayList<ReviewDto>();
        list2.addAll(list); // List를 ArrayList로 변환하여 반환
        
        return list2;
    }
}
