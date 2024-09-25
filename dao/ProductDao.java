package com.shopping.dao;

import java.util.ArrayList;

import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;
import com.shopping.dto.ReviewDto;


public interface ProductDao {
	
	// 검색상품 목록 가져오기
	ArrayList<ProductDto> getSearchProduct(String search);
	
	// 상품전체목록 가져오기
	ArrayList<ProductDto> getProductList();
	
	// pno에 해당하는 상품
	ProductDto getProductByPno(int pNo);
	
	// 상품옵션 가져오기
	ArrayList<OptionDto> getProductOptionByPno(int pNo);

	// 상품 찜 여부 확인
	int getZzim(int pNo,String id);

	// 찜하기 
	void zzimInsert(int pNo,String id);
	
	// 찜 삭제
	void deleteZzim(int pNo,String id);
	
	// 상품리뷰 가져오기
	ArrayList<ReviewDto> getReview(int pNo);
	
	
}
