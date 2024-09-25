package com.shopping.service;

import java.util.ArrayList;
import java.util.Map;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

public interface OrderService {

    // ��ٱ��Ͽ� ��ǰ ���
    void insertCart(CartDto dto);
    
    // Ư�� ������� ��ٱ��� ��� ��ȸ
    ArrayList<CartViewDto> getCart(String id);
    
    // �ֹ� ���� ���� ��ȸ (�ֹ� ��ȣ�� ����� ID�� ��ȸ)
    ArrayList<PurchaseDto> getPurchase(int order_idx, String id);
    
    // ��ٱ��ϸ� ���� ó���� �̵�
    void insertCartToPay(String id);
    
    // ���� �ֱ��� �ֹ� �ε��� �������� (����� ID�� ���� ��ȸ)
    int getMaxOrderIdx(String id);
    
    // ���� ���� �� ��� (��ٱ��� �ε���, ����, �ֹ� �ε���, ����� ID�� ���� ó��)
    void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id);
    
    // ����� ID�� �ּ� ���� ��ȸ
    Map<String, Object> getAddress(String id);
}
