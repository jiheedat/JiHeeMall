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

    // 주문 관련 Dao
    @Autowired
    OrderDao dao;
    
    // 장바구니에 상품 등록
    @Override
    public void insertCart(CartDto dto) {
        dao.insertCart(dto); 
    }

    // 장바구니 목록 조회 (View)
    @Override
    public ArrayList<CartViewDto> getCart(String id) {
        return dao.getCart(id); 
    }

    // 구매 내역 조회
    @Override
    public ArrayList<PurchaseDto> getPurchase(int order_idx, String id) {
        return dao.getPurchase(id, order_idx); 
    }

    // 장바구니에서 결제 정보로 이동
    @Override
    public void insertCartToPay(String id) {
        dao.insertCartToPay(id);  
    }

    // 가장 최근의 주문 인덱스 가져오기
    @Override
    public int getMaxOrderIdx(String id) {
        return dao.getMaxOrderIdx(id);  
    }

    // 결제 정보 상세 등록
    @Override
    public void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx,
                                      String id) {
        dao.insertCartToPayDetail(listCartIdx, listQty, orderIdx, id);  
    }

    // 아이디로 회원의 주소 정보 가져오기
    @Override
    public Map<String, Object> getAddress(String id) {
        return dao.getAddress(id);  
    }
}
