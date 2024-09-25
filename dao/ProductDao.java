package com.shopping.dao;

import java.util.ArrayList;

import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;
import com.shopping.dto.ReviewDto;


public interface ProductDao {
	
	// �˻���ǰ ��� ��������
	ArrayList<ProductDto> getSearchProduct(String search);
	
	// ��ǰ��ü��� ��������
	ArrayList<ProductDto> getProductList();
	
	// pno�� �ش��ϴ� ��ǰ
	ProductDto getProductByPno(int pNo);
	
	// ��ǰ�ɼ� ��������
	ArrayList<OptionDto> getProductOptionByPno(int pNo);

	// ��ǰ �� ���� Ȯ��
	int getZzim(int pNo,String id);

	// ���ϱ� 
	void zzimInsert(int pNo,String id);
	
	// �� ����
	void deleteZzim(int pNo,String id);
	
	// ��ǰ���� ��������
	ArrayList<ReviewDto> getReview(int pNo);
	
	
}
