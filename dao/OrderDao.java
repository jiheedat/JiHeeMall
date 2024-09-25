package com.shopping.dao;

import java.util.ArrayList;
import java.util.Map;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

public interface OrderDao {

    // 장바구니에 상품 등록
    void insertCart(CartDto dto);
    
    // 특정 사용자의 장바구니 목록 조회
    ArrayList<CartViewDto> getCart(String id);
    
    // 주문 및 결제 내역 조회 (사용자 ID와 주문 번호로 조회)
    ArrayList<PurchaseDto> getPurchase(String id, int order_idx);
    
    // 장바구니에서 결제 과정으로 이동
    void insertCartToPay(String id);
    
    // 가장 최근의 주문 번호 (order_idx) 조회
    int getMaxOrderIdx(String id);
    
    // 결제 정보 등록 (장바구니 항목, 수량, 주문 번호를 사용하여 등록)
    void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id);
    
    // 장바구니 번호로부터 상품 옵션 번호 조회
    int getOptionIdxFromCartIdx(int cartIdx);

    // 사용자 ID로 주소 정보 조회
    Map<String, Object> getAddress(String id);
}
