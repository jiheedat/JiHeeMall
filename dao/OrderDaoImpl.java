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

    // ��ٱ��Ͽ� ��ǰ ���
    @Override
    public void insertCart(CartDto dto) {
        session.insert("OrderMapper.insertCart", dto);  
    }

    // Ư�� ������� ��ٱ��� ��� ��ȸ
    @Override
    public ArrayList<CartViewDto> getCart(String id) {
        List<CartViewDto> list = session.selectList("OrderMapper.getCart", id);  
        ArrayList<CartViewDto> list2 = new ArrayList<CartViewDto>();
        list2.addAll(list);  // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ

        return list2;
    }

    // Ư�� ������� ���� ���� ��ȸ
    @Override
    public ArrayList<PurchaseDto> getPurchase(String id, int order_idx) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);            // ����� ID
        map.put("order_idx", order_idx);  // �ֹ� ��ȣ
        
        List<PurchaseDto> list = session.selectList("OrderMapper.getPurchase", map);  
        ArrayList<PurchaseDto> list2 = new ArrayList<PurchaseDto>();
        list2.addAll(list);  // List�� ArrayList�� ��ȯ�Ͽ� ��ȯ

        return list2;
    }

    // ���� �������� ��ٱ��� ���� ���
    @Override
    public void insertCartToPay(String id) {
        session.insert("OrderMapper.insertCartToPay", id);  
    }

    // ������� �ֱ� �ֹ� ��ȣ ��ȸ
    @Override
    public int getMaxOrderIdx(String id) {
        return session.selectOne("OrderMapper.getMaxOrderIdx", id);  
    }

    // ��ٱ����� �� ��ǰ ������ ���� ������ ���
    @Override
    public void insertCartToPayDetail(ArrayList<Integer> listCartIdx, ArrayList<Integer> listQty, int orderIdx, String id) {
        for (int i = 0; i <= listCartIdx.size() - 1; i++) {
            int cartIdx = listCartIdx.get(i);  // ��ٱ��� �׸� ��ȣ
            int qty = listQty.get(i);  // ����
            int optionIdx = getOptionIdxFromCartIdx(cartIdx);  // �ɼ� ��ȣ ��ȸ
            Map<String, Integer> map1 = new HashMap<String, Integer>();
            map1.put("order_idx", orderIdx);  // �ֹ� ��ȣ
            map1.put("option_idx", optionIdx);  // �ɼ� ��ȣ
            map1.put("qty", qty);  // ����

            session.insert("OrderMapper.insertCartToPayDetail", map1); 
        }
    }

    // ��ٱ��� �׸� ��ȣ�κ��� ��ǰ �ɼ� ��ȣ ��ȸ
    @Override
    public int getOptionIdxFromCartIdx(int cartIdx) {
        return session.selectOne("OrderMapper.getOptionIdxFromCartIdx", cartIdx); 
    }

    // ����� ID�� �ּ� ���� ��ȸ
    @Override
    public Map<String, Object> getAddress(String id) {
        return session.selectOne("OrderMapper.getAddress", id); 
    }
}
