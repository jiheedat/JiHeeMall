package com.shopping.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.OrderDao;
import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

@Service
public class OrderServiceImpl implements OrderService {

    // �ֹ� ���� Dao
    @Autowired
    OrderDao dao;
    
    // ��ٱ��Ͽ� ��ǰ ���
    @Override
    public void insertCart(CartDto dto) {
        dao.insertCart(dto); 
    }

    // ��ٱ��� ��� ��ȸ (View)
    @Override
    public ArrayList<CartViewDto> getCart(String id) {
        return dao.getCart(id); 
    }

    // ���� ���� ��ȸ
    @Override
    public ArrayList<PurchaseDto> getPurchase(int order_idx, String id) {
        return dao.getPurchase(id, order_idx); 
    }

    // ��ٱ��Ͽ��� ���� ������ �̵�
    @Override
    public void insertCartToPay(String id) {
        dao.insertCartToPay(id);  
    }

    // ���� �ֱ��� �ֹ� �ε��� ��������
    @Override
    public int getMaxOrderIdx(String id) {
        return dao.getMaxOrderIdx(id);  
    }

    // ���� ���� �� ���
    @Override
    public void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx,
                                      String id) {
        dao.insertCartToPayDetail(listCartIdx, listQty, orderIdx, id);  
    }

    // ���̵�� ȸ���� �ּ� ���� ��������
    @Override
    public Map<String, Object> getAddress(String id) {
        return dao.getAddress(id);  
    }
}
