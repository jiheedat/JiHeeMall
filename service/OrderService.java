package com.shopping.service;

import java.util.ArrayList;
import java.util.Map;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

public interface OrderService {

    // 장바구니에 상품 등록
    void insertCart(CartDto dto);
    
    // 특정 사용자의 장바구니 목록 조회
    ArrayList<CartViewDto> getCart(String id);
    
    // 주문 결제 내역 조회 (주문 번호와 사용자 ID로 조회)
    ArrayList<PurchaseDto> getPurchase(int order_idx, String id);
    
    // 장바구니를 결제 처리로 이동
    void insertCartToPay(String id);
    
    // 가장 최근의 주문 인덱스 가져오기 (사용자 ID를 통해 조회)
    int getMaxOrderIdx(String id);
    
    // 결제 정보 상세 등록 (장바구니 인덱스, 수량, 주문 인덱스, 사용자 ID를 통해 처리)
    void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id);
    
    // 사용자 ID로 주소 정보 조회
    Map<String, Object> getAddress(String id);
}
