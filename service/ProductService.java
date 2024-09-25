package com.shopping.service;

import java.util.ArrayList;

import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;

public interface ProductService {
	
	// 검색상품 목록 가져오기
	ArrayList<ProductDto> getSearchProduct(String search);
	
	// 상품전체목록
	ArrayList<ProductDto> getProductList();
	
	// 상품정보
	ProductDto getProductByPno(int pNo);
	
	// 상품옵션
	ArrayList<OptionDto> getOption(int pNo);
	
	// 상품 찜 여부 확인
	int getZzim(int pNo,String id);
	
	//상품 찜
	void zzim(int pNo, String id); 
	
	// 찜 삭제
	void deleteZzim(int pNo,String id);
}
