package com.shopping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.ProductDao;
import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;

// ProductService의 구현 클래스
@Service
public class ProductServiceImpl implements ProductService {
    
	// 상품관련 다오
    @Autowired
    ProductDao dao;
    
    // 검색어로 상품 목록 조회
    @Override
    public ArrayList<ProductDto> getSearchProduct(String search) {
        return dao.getSearchProduct(search); 
    }
    
    // 전체 상품 목록 조회
    @Override
    public ArrayList<ProductDto> getProductList() {
        return dao.getProductList(); 
    }
    
    // 상품 번호로 특정 상품 정보 조회
    @Override
    public ProductDto getProductByPno(int pNo) {
        return dao.getProductByPno(pNo); 
    }
    
    // 상품 번호로 상품 옵션 목록 조회
    @Override
    public ArrayList<OptionDto> getOption(int pNo) {
        return dao.getProductOptionByPno(pNo); 
    }

    // 찜 상태 확인 (1: 찜 등록, 0: 찜 없음)
    @Override
    public int getZzim(int pNo, String id) {
        return dao.getZzim(pNo, id); 
    }

    // 찜 등록
    @Override
    public void zzim(int pNo, String id) {
        dao.zzimInsert(pNo, id); 
    }

    // 찜 삭제
    @Override
    public void deleteZzim(int pNo, String id) {
        dao.deleteZzim(pNo, id); 
    }
}
