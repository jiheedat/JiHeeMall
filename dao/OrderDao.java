package com.shopping.dao;

import java.util.ArrayList;
import java.util.Map;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

public interface OrderDao {

    // ��ٱ��Ͽ� ��ǰ ���
    void insertCart(CartDto dto);
    
    // Ư�� ������� ��ٱ��� ��� ��ȸ
    ArrayList<CartViewDto> getCart(String id);
    
    // �ֹ� �� ���� ���� ��ȸ (����� ID�� �ֹ� ��ȣ�� ��ȸ)
    ArrayList<PurchaseDto> getPurchase(String id, int order_idx);
    
    // ��ٱ��Ͽ��� ���� �������� �̵�
    void insertCartToPay(String id);
    
    // ���� �ֱ��� �ֹ� ��ȣ (order_idx) ��ȸ
    int getMaxOrderIdx(String id);
    
    // ���� ���� ��� (��ٱ��� �׸�, ����, �ֹ� ��ȣ�� ����Ͽ� ���)
    void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id);
    
    // ��ٱ��� ��ȣ�κ��� ��ǰ �ɼ� ��ȣ ��ȸ
    int getOptionIdxFromCartIdx(int cartIdx);

    // ����� ID�� �ּ� ���� ��ȸ
    Map<String, Object> getAddress(String id);
}
