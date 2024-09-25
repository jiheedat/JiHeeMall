package com.shopping.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.ProductDao;
import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;

// ProductService�� ���� Ŭ����
@Service
public class ProductServiceImpl implements ProductService {
    
	// ��ǰ���� �ٿ�
    @Autowired
    ProductDao dao;
    
    // �˻���� ��ǰ ��� ��ȸ
    @Override
    public ArrayList<ProductDto> getSearchProduct(String search) {
        return dao.getSearchProduct(search); 
    }
    
    // ��ü ��ǰ ��� ��ȸ
    @Override
    public ArrayList<ProductDto> getProductList() {
        return dao.getProductList(); 
    }
    
    // ��ǰ ��ȣ�� Ư�� ��ǰ ���� ��ȸ
    @Override
    public ProductDto getProductByPno(int pNo) {
        return dao.getProductByPno(pNo); 
    }
    
    // ��ǰ ��ȣ�� ��ǰ �ɼ� ��� ��ȸ
    @Override
    public ArrayList<OptionDto> getOption(int pNo) {
        return dao.getProductOptionByPno(pNo); 
    }

    // �� ���� Ȯ�� (1: �� ���, 0: �� ����)
    @Override
    public int getZzim(int pNo, String id) {
        return dao.getZzim(pNo, id); 
    }

    // �� ���
    @Override
    public void zzim(int pNo, String id) {
        dao.zzimInsert(pNo, id); 
    }

    // �� ����
    @Override
    public void deleteZzim(int pNo, String id) {
        dao.deleteZzim(pNo, id); 
    }
}
