package com.shopping.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.PurchaseDto;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    SqlSession session;

    // 장바구니에 상품 등록
    @Override
    public void insertCart(CartDto dto) {
        session.insert("OrderMapper.insertCart", dto);  
    }

    // 특정 사용자의 장바구니 목록 조회
    @Override
    public ArrayList<CartViewDto> getCart(String id) {
        List<CartViewDto> list = session.selectList("OrderMapper.getCart", id);  
        ArrayList<CartViewDto> list2 = new ArrayList<CartViewDto>();
        list2.addAll(list);  // List를 ArrayList로 변환하여 반환

        return list2;
    }

    // 특정 사용자의 구매 정보 조회
    @Override
    public ArrayList<PurchaseDto> getPurchase(String id, int order_idx) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);            // 사용자 ID
        map.put("order_idx", order_idx);  // 주문 번호
        
        List<PurchaseDto> list = session.selectList("OrderMapper.getPurchase", map);  
        ArrayList<PurchaseDto> list2 = new ArrayList<PurchaseDto>();
        list2.addAll(list);  // List를 ArrayList로 변환하여 반환

        return list2;
    }

    // 결제 과정에서 장바구니 정보 등록
    @Override
    public void insertCartToPay(String id) {
        session.insert("OrderMapper.insertCartToPay", id);  
    }

    // 사용자의 최근 주문 번호 조회
    @Override
    public int getMaxOrderIdx(String id) {
        return session.selectOne("OrderMapper.getMaxOrderIdx", id);  
    }

    // 장바구니의 상세 상품 정보를 결제 정보로 등록
    @Override
    public void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id) {
        for (int i = 0; i <= listCartIdx.size() - 1; i++) {
            int cartIdx = listCartIdx.get(i);  // 장바구니 항목 번호
            int qty = listQty.get(i);  // 수량
            int optionIdx = getOptionIdxFromCartIdx(cartIdx);  // 옵션 번호 조회
            Map<String, Integer> map1 = new HashMap<String, Integer>();
            map1.put("order_idx", orderIdx);  // 주문 번호
            map1.put("option_idx", optionIdx);  // 옵션 번호
            map1.put("qty", qty);  // 수량

            session.insert("OrderMapper.insertCartToPayDetail", map1); 
        }
    }

    // 장바구니 항목 번호로부터 상품 옵션 번호 조회
    @Override
    public int getOptionIdxFromCartIdx(int cartIdx) {
        return session.selectOne("OrderMapper.getOptionIdxFromCartIdx", cartIdx); 
    }

    // 사용자 ID로 주소 정보 조회
    @Override
    public Map<String, Object> getAddress(String id) {
        return session.selectOne("OrderMapper.getAddress", id); 
    }
}
