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
    
    // 상품관련 서비스
    @Autowired
    ProductService ps;
    
    // 문의 & 리뷰 관련 서비스
    @Autowired
    BoardService bs;
    
    // 회원 관련 서비스
    @Autowired
    MemberService ms;
    
    // 찜 상태 확인 후 하트 표시
    @ResponseBody
    @RequestMapping("zzimHeart")
    public int zzimHeart(@RequestBody Map<String, Object> param) {
        String id = (String) param.get("id"); // 사용자(회원)
        int pNo = Integer.parseInt((String) param.get("pNo"));  // 상품 번호
        
        int result = ps.getZzim(pNo, id);  // 찜 상태 조회 (1: 찜함, 0: 찜하지 않음)
        
        return result;  // 찜 상태 반환
    }
    
    // 찜 상태 Ajax(찜 등록 또는 삭제)
    @ResponseBody
    @RequestMapping("toggleZzim")
    public int toggleZzim(@RequestBody Map<String, Object> param) throws Exception {
        String id = (String) param.get("id");
        int pNo = Integer.parseInt((String) param.get("pNo"));  // 상품 번호
        
        int result = ps.getZzim(pNo, id);  // 현재 찜 상태 확인

        if (result == 1) {
            ps.deleteZzim(pNo, id);  // 찜 삭제
            return 0;  // 삭제 후 상태 반환
        }
        
        ps.zzim(pNo, id);  // 찜 등록
        return 1;  // 등록 후 상태 반환
    }
    
    // 리뷰 상세 정보 가져오기
    @ResponseBody
    @RequestMapping("getReviewDetail")
    public Map<String, Object> getInquiry(@RequestBody Map<String, Object> param) throws Exception {
        System.out.println("리뷰 상세 정보 조회 들어옴");
        
        int r_idx = Integer.parseInt((String) param.get("r_idx"));  // 리뷰 인덱스
        String id = (String) param.get("id");  // 사용자 ID
        
        String reviewDetail = bs.getReviewDetail(r_idx, id);  // 리뷰 상세 정보 가져오기
        
        Map<String, Object> detail = new HashMap<>();
        detail.put("reviewDetail", reviewDetail);  // 리뷰 정보 맵에 저장
        
        return detail;  // 리뷰 정보 반환
    }
    
    // 상품 문의 목록 가져오기
    @ResponseBody
    @RequestMapping("/getInquiryList")
    public ArrayList<InquiryDto> inquiryList(@RequestBody Map<String, Object> param) throws Exception {
        System.out.println("문의 목록 조회 들어옴");
        
        int page_num = (int) param.get("page_num");  // 페이지 번호
        int pNo = (int) param.get("pNo");  // 상품 번호
        
        ArrayList<InquiryDto> list = bs.getInquiryList(page_num, pNo);  // 문의 목록 가져오기
        
        return list;  // 문의 목록 반환
    }
    
    // 아이디 중복 체크
    @ResponseBody
    @RequestMapping("/duplicationId")
    public Map<String, String> duplicationId(@RequestBody Map<String, String> param) throws Exception {
        System.out.println("아이디 중복 체크 들어옴");
        
        String idFromParam = param.get("id");  // 요청에서 받은 ID
        String idFromDB = ms.duplication(idFromParam);  // DB에서 중복 여부 확인
        
        Map<String, String> map = new HashMap<>();
        
        // 중복 여부에 따라 결과를 맵에 저장
        if (idFromDB != null && param.get("id") != "" && idFromParam.equals(idFromDB)) {
            map.put("idFromDB", "true");  // 중복된 경우
        } else if (!(idFromParam.equals(idFromDB))) {
            map.put("idFromDB", "false");  // 중복되지 않은 경우
        }
        
        return map;  // 결과 반환
    }
    
    // 카카오페이 결제 처리
    @ResponseBody
    @RequestMapping("/kakaoPay")
    public String kakaoPay() throws IOException {
        
        System.out.println("카카오페이 결제 처리 시작");
        
        try {
            // 카카오페이 결제 요청 URL
            URL url = new URL("http://open-api.kakaopay.com/online/v1/payment/ready");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  // 연결 설정
            conn.setRequestMethod("POST");  // POST 방식 설정
            conn.setRequestProperty("Authorization", "PRD46AC6A00721A0A85CC7DEBF07E4C1C0122117");  // 인증 토큰
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");  // 요청 헤더 설정
            conn.setDoOutput(true);  // 출력 스트림 사용 설정
            
            // 요청 파라미터 설정
            String parameter = "cid=TC0ONETIME&partner_order_id=partner_order_id&partner_user_id=partner_user_id&item_name=초코파이&quantity=1&total_amount=2200&vat_amount=200&tax_free_amount=0&approval_url=http://localhost:9090/ajax/shoppingMall/kakaoPay&fail_url=http://localhost:9090&cancel_url=http://localhost:9090";
            
            // 요청 파라미터 전송
            OutputStream output = conn.getOutputStream();
            DataOutputStream data = new DataOutputStream(output);
            data.writeBytes(parameter);
            data.close();
            
            int result = conn.getResponseCode();  // 응답 코드 확인
            
            InputStream input;
            if (result == 200) {
                input = conn.getInputStream();  // 성공 시 응답 데이터 받기
            } else {
                input = conn.getErrorStream();  // 실패 시 에러 데이터 받기
            }
            
            // 응답 데이터 읽기
            InputStreamReader read = new InputStreamReader(input);
            BufferedReader buffer = new BufferedReader(read);
            
            return buffer.readLine();  // 응답 반환
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        
        return "result: NO";  // 실패 시 결과 반환
    }
}
