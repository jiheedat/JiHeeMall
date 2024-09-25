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
    
    // �˻���� ��ǰ ��� ��������
    @Override
    public ArrayList<ProductDto> getSearchProduct(String search) {
        List<ProductDto> dto = sqlSession.selectList("productMapper.getSearchProduct", search); // �˻� ��� ��ȸ
        ArrayList<ProductDto> list = new ArrayList<ProductDto>();
        list.addAll(dto); // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ
        
        return list;
    }
    
    // ��ü ��ǰ ��� ��������
    @Override
    public ArrayList<ProductDto> getProductList() {
        List<ProductDto> list = sqlSession.selectList("productMapper.getProductDtoList"); // ��ü ��ǰ ��� ��ȸ
        ArrayList<ProductDto> list2 = new ArrayList<ProductDto>();
        list2.addAll(list); // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ
        
        return list2;
    }
    
    // ��ǰ ��ȣ�� Ư�� ��ǰ ���� ��������
    @Override
    public ProductDto getProductByPno(int pNo) {
        ProductDto dto = sqlSession.selectOne("productMapper.getProductByPno", pNo); // ��ǰ ���� ��ȸ
        return dto;
    }

    // ��ǰ ��ȣ�� �ɼ� ��� ��������
    @Override
    public ArrayList<OptionDto> getProductOptionByPno(int pNo) {
        List<OptionDto> list = sqlSession.selectList("productMapper.getOption", pNo); // �ɼ� ��� ��ȸ
        ArrayList<OptionDto> option = new ArrayList<OptionDto>();
        option.addAll(list); // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ
        
        return option;
    }

    // Ư�� ��ǰ�� ���� �� ���� Ȯ��
    @Override
    public int getZzim(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // ��ǰ ��ȣ
        map.put("id", id);   // ����� ID
        Integer ret = sqlSession.selectOne("productMapper.getZzim", map); // �� ���� Ȯ��
        
        return ret;
    }

    // �� ���
    @Override
    public void zzimInsert(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // ��ǰ ��ȣ
        map.put("id", id);   // ����� ID
        
        sqlSession.insert("productMapper.zzim", map); // �� ���
    }

    // �� ����
    @Override
    public void deleteZzim(int pNo, String id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("pNo", pNo); // ��ǰ ��ȣ
        map.put("id", id);   // ����� ID
        
        sqlSession.delete("productMapper.deleteZzim", map); // �� ����
    }

    // ��ǰ ���� ��� ��������
    @Override
    public ArrayList<ReviewDto> getReview(int pNo) {
        List<ReviewDto> list = sqlSession.selectList("productMapper.getReview", pNo); // ���� ��� ��ȸ
        ArrayList<ReviewDto> list2 = new ArrayList<ReviewDto>();
        list2.addAll(list); // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ
        
        return list2;
    }
}
