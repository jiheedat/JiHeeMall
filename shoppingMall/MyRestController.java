package com.jh.shoppingMall;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.InquiryDto;
import com.shopping.service.BoardService;
import com.shopping.service.MemberService;
import com.shopping.service.ProductService;

@RestController
@RequestMapping(value="/ajax", produces="application/json; charset=utf-8")
public class MyRestController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    
    // ��ǰ���� ����
    @Autowired
    ProductService ps;
    
    // ���� & ���� ���� ����
    @Autowired
    BoardService bs;
    
    // ȸ�� ���� ����
    @Autowired
    MemberService ms;
    
    // �� ���� Ȯ�� �� ��Ʈ ǥ��
    @ResponseBody
    @RequestMapping("zzimHeart")
    public int zzimHeart(@RequestBody Map<String, Object> param) {
        String id = (String) param.get("id"); // �����(ȸ��)
        int pNo = Integer.parseInt((String) param.get("pNo"));  // ��ǰ ��ȣ
        
        int result = ps.getZzim(pNo, id);  // �� ���� ��ȸ (1: ����, 0: ������ ����)
        
        return result;  // �� ���� ��ȯ
    }
    
    // �� ���� Ajax(�� ��� �Ǵ� ����)
    @ResponseBody
    @RequestMapping("toggleZzim")
    public int toggleZzim(@RequestBody Map<String, Object> param) throws Exception {
        String id = (String) param.get("id");
        int pNo = Integer.parseInt((String) param.get("pNo"));  // ��ǰ ��ȣ
        
        int result = ps.getZzim(pNo, id);  // ���� �� ���� Ȯ��

        if (result == 1) {
            ps.deleteZzim(pNo, id);  // �� ����
            return 0;  // ���� �� ���� ��ȯ
        }
        
        ps.zzim(pNo, id);  // �� ���
        return 1;  // ��� �� ���� ��ȯ
    }
    
    // ���� �� ���� ��������
    @ResponseBody
    @RequestMapping("getReviewDetail")
    public Map<String, Object> getInquiry(@RequestBody Map<String, Object> param) throws Exception {
        System.out.println("���� �� ���� ��ȸ ����");
        
        int r_idx = Integer.parseInt((String) param.get("r_idx"));  // ���� �ε���
        String id = (String) param.get("id");  // ����� ID
        
        String reviewDetail = bs.getReviewDetail(r_idx, id);  // ���� �� ���� ��������
        
        Map<String, Object> detail = new HashMap<>();
        detail.put("reviewDetail", reviewDetail);  // ���� ���� �ʿ� ����
        
        return detail;  // ���� ���� ��ȯ
    }
    
    // ��ǰ ���� ��� ��������
    @ResponseBody
    @RequestMapping("/getInquiryList")
    public ArrayList<InquiryDto> inquiryList(@RequestBody Map<String, Object> param) throws Exception {
        System.out.println("���� ��� ��ȸ ����");
        
        int page_num = (int) param.get("page_num");  // ������ ��ȣ
        int pNo = (int) param.get("pNo");  // ��ǰ ��ȣ
        
        ArrayList<InquiryDto> list = bs.getInquiryList(page_num, pNo);  // ���� ��� ��������
        
        return list;  // ���� ��� ��ȯ
    }
    
    // ���̵� �ߺ� üũ
    @ResponseBody
    @RequestMapping("/duplicationId")
    public Map<String, String> duplicationId(@RequestBody Map<String, String> param) throws Exception {
        System.out.println("���̵� �ߺ� üũ ����");
        
        String idFromParam = param.get("id");  // ��û���� ���� ID
        String idFromDB = ms.duplication(idFromParam);  // DB���� �ߺ� ���� Ȯ��
        
        Map<String, String> map = new HashMap<>();
        
        // �ߺ� ���ο� ���� ����� �ʿ� ����
        if (idFromDB != null && param.get("id") != "" && idFromParam.equals(idFromDB)) {
            map.put("idFromDB", "true");  // �ߺ��� ���
        } else if (!(idFromParam.equals(idFromDB))) {
            map.put("idFromDB", "false");  // �ߺ����� ���� ���
        }
        
        return map;  // ��� ��ȯ
    }
    
    // īī������ ���� ó��
    @ResponseBody
    @RequestMapping("/kakaoPay")
    public String kakaoPay() throws IOException {
        
        System.out.println("īī������ ���� ó�� ����");
        
        try {
            // īī������ ���� ��û URL
            URL url = new URL("http://open-api.kakaopay.com/online/v1/payment/ready");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  // ���� ����
            conn.setRequestMethod("POST");  // POST ��� ����
            conn.setRequestProperty("Authorization", "PRD46AC6A00721A0A85CC7DEBF07E4C1C0122117");  // ���� ��ū
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");  // ��û ��� ����
            conn.setDoOutput(true);  // ��� ��Ʈ�� ��� ����
            
            // ��û �Ķ���� ����
            String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=��������&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://localhost:9090/ajax/shoppingMall/kakaoPay&fail_url=http://localhost:9090&cancel_url=http://localhost:9090";
            
            // ��û �Ķ���� ����
            OutputStream output = conn.getOutputStream();
            DataOutputStream data = new DataOutputStream(output);
            data.writeBytes(parameter);
            data.close();
            
            int result = conn.getResponseCode();  // ���� �ڵ� Ȯ��
            
            InputStream input;
            if (result == 200) {
                input = conn.getInputStream();  // ���� �� ���� ������ �ޱ�
            } else {
                input = conn.getErrorStream();  // ���� �� ���� ������ �ޱ�
            }
            
            // ���� ������ �б�
            InputStreamReader read = new InputStreamReader(input);
            BufferedReader buffer = new BufferedReader(read);
            
            return buffer.readLine();  // ���� ��ȯ
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        return "result: NO";  // ���� �� ��� ��ȯ
    }
}
