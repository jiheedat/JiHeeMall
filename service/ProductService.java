package com.shopping.service;

import java.util.ArrayList;

import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;

public interface ProductService {
	
	// �˻���ǰ ��� ��������
	ArrayList<ProductDto> getSearchProduct(String search);
	
	// ��ǰ��ü���
	ArrayList<ProductDto> getProductList();
	
	// ��ǰ����
	ProductDto getProductByPno(int pNo);
	
	// ��ǰ�ɼ�
	ArrayList<OptionDto> getOption(int pNo);
	
	// ��ǰ �� ���� Ȯ��
	int getZzim(int pNo,String id);
	
	//��ǰ ��
	void zzim(int pNo, String id); 
	
	// �� ����
	void deleteZzim(int pNo,String id);
}
