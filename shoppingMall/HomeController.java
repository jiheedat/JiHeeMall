package com.jh.shoppingMall;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopping.dto.CartDto;
import com.shopping.dto.CartViewDto;
import com.shopping.dto.MemberDto;
import com.shopping.dto.OptionDto;
import com.shopping.dto.ProductDto;
import com.shopping.dto.PurchaseDto;
import com.shopping.dto.ReviewDto;
import com.shopping.service.BoardService;
import com.shopping.service.MemberService;
import com.shopping.service.OrderService;
import com.shopping.service.ProductService;

@Controller
public class HomeController {

    // 로그를 기록하는 Logger 객체 생성
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    HttpServletRequest hs;

    // 상품 관련 서비스
    @Autowired
    ProductService ps;

    // 회원 관련 서비스 
    @Autowired
    MemberService ms;

    // 주문 관련 서비스
    @Autowired
    OrderService os;

    // 리뷰 및 문의 관련 서비스
    @Autowired
    BoardService bs;

    // 메인페이지 -> 실행시 기본화면
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String search) {

        // 상품 목록을 저장할 리스트 생성
        ArrayList<ProductDto> list = new ArrayList<ProductDto>();

        // 검색어가 없거나 빈 문자열이면 전체 상품 목록을 가져옴
        if (search == null || search.equals("")) {
            search = "";
            list = ps.getProductList();  // 모든 상품 목록 불러오기
        } else {
            list = ps.getSearchProduct(search);  // 검색어로 필터링된 상품 목록 불러오기
        }

        // 세션에서 사용자 ID를 가져옴 (로그인 여부 확인용)
        String id = (String) session.getAttribute("id");

        // 가격을 천 단위로 포맷하는 DecimalFormat 객체 생성
        DecimalFormat decFormat = new DecimalFormat("###,###");

        // 각 상품의 가격을 포맷하여 표시될 가격(setPrice) 설정
        for (ProductDto product : list) {
            int price = product.getPrice();  // 원래 가격
            String formattedPrice = decFormat.format(price);  // 천 단위로 콤마 추가된 가격
            product.setSetPrice(formattedPrice);  // 상품에 포맷된 가격 설정
        }
        
        model.addAttribute("list", list);
        model.addAttribute("id", id);

        // mainPage.jsp로 이동 (상품 목록과 사용자 정보를 가진 메인 페이지)
        return "mainPage";
    }

    // 회원가입 페이지로 이동
    @RequestMapping("/join")
    public String join() {
        return "join";
    }

    // 로그아웃 처리 후 로그인 페이지로 리다이렉트
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // 세션 무효화 (로그아웃)
        return "redirect:login";
    }

    // 로그인 페이지로 이동
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // 로그인 정보 체크
    @RequestMapping("/loginCheck")
    public String loginCheck(String id, String pw, HttpSession session, Model model) {
        boolean result = ms.loginCheck(id, pw);  // 로그인 체크
        if (result == true) {
            session.setAttribute("id", id);  // 세션에 ID 저장
            return "redirect:/";  // 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("msg", "로그인 정보를 확인해 주세요");  // 로그인 실패 시 메시지 전달
            return "forward:/login";  
        }
    }

    // 네이버 로그인 콜백 처리
    @RequestMapping("/naverLogin")
    public String naverLogin() {
        return "naver";
    }

    // 상품 상세 페이지
    @RequestMapping("/detailPage")
    public String detail(HttpServletRequest request, HttpSession session, Model model, Integer page) {
        int pNo = Integer.parseInt(request.getParameter("pNo"));  // 상품 번호 가져오기
        ProductDto productDto = ps.getProductByPno(pNo);  // 상품 정보 가져오기
        ArrayList<OptionDto> option = ps.getOption(pNo);  // 상품 옵션 가져오기

        if (page == null) {
            page = 1;
        }
        ArrayList<ReviewDto> reviewList = bs.getReviewList(page, pNo);  // 리뷰 목록 가져오기
        int lastPage = bs.getLastPageNum(pNo);  // 마지막 페이지 번호 가져오기

        // 가격을 천 단위로 포맷하는 DecimalFormat 객체 생성
        DecimalFormat decFormat = new DecimalFormat("###,###");
        String price = decFormat.format(productDto.getPrice());  // 상품 가격 포맷팅
        String salePrice = decFormat.format(productDto.getSalePrice());  // 세일 가격 포맷팅

        int endNum = page - (page % 5) + 5 - (page % 5 == 0 ? 5 : 0);  // 페이지 네비게이션의 마지막 번호
        int startNum = endNum - 4;  // 페이지 네비게이션의 시작 번호

        String id = (String) session.getAttribute("id");  // 사용자 ID 가져오기

        model.addAttribute("id", id);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("page", page);
        model.addAttribute("dto", productDto);
        model.addAttribute("option", option);
        model.addAttribute("price", price);
        model.addAttribute("salePrice", salePrice);
        model.addAttribute("endNum", endNum);
        model.addAttribute("startNum", startNum);
        model.addAttribute("pNo", pNo);

        // 상세 페이지로 이동
        return "detailPage";
    }

    // 주문 페이지
    @RequestMapping("/order")
    public String order(int order_idx, Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");
        
        if (id == null) id = "ygyg12";  // 세션에 ID가 없을 경우 기본값 설정
		/*
		 * System.out.println("들어옴"); System.out.println("order_idx: " + order_idx);
		 * System.out.println("id : " + id);
		 */

        ArrayList<PurchaseDto> dto = os.getPurchase(order_idx, id);  // 주문 정보 가져오기

        model.addAttribute("dtoList", dto);  // 모델에 주문 정보 추가

        return "order";  // 주문 페이지로 이동
    }

    // 상품 문의 작성 페이지
    @RequestMapping("/write")
    public String write() {
        return "productInquiry";
    }

    // 회원가입 성공 처리
    @RequestMapping("/joinSuccess")
    public String joinSuccess(MemberDto dto) {
        ms.inertMember(dto);  // 회원 정보 삽입
        return "redirect:/";  // 메인 페이지로 리다이렉트
    }

    // 장바구니에 상품 추가
    @RequestMapping("/insertCart")
    public String insertCart(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String btn_name = request.getParameter("btn_name");  // 버튼 이름 가져오기
        int num = Integer.parseInt(request.getParameter("g_max_option_number"));  // 선택한 옵션의 개수 가져오기
        if (btn_name.equals("cart")) {
            String id = (String) session.getAttribute("id");  // 사용자 ID 가져오기
            for (int i = 1; i <= num; i++) {
                int optionIdx = Integer.parseInt(request.getParameter("option" + i));  // 옵션 번호 가져오기
                int qty = Integer.parseInt(request.getParameter("qty" + i));  // 수량 가져오기
                CartDto dto = new CartDto(0, id, optionIdx, qty);  // 장바구니 DTO 생성
                os.insertCart(dto);  // 장바구니에 상품 추가
            }
            return "redirect:/cart";  // 장바구니 페이지로 리다이렉트
        } else {
            return "redirect:/order";  // 주문 페이지로 리다이렉트
        }
    }

    // 장바구니 페이지
    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        System.out.println("장바구니 들어옴");
        DecimalFormat decFormat = new DecimalFormat("###,###");  // 가격 포맷팅을 위한 객체 생성
        ArrayList<CartViewDto> dtoList = os.getCart("ygyg12");  // 장바구니 목록 가져오기

        for (CartViewDto dto : dtoList) {
            int price = dto.getPrice();  // 상품 가격 가져오기
            String setPrice = decFormat.format(price);  // 가격 포맷팅
            dto.setSetPrice(setPrice);  // 포맷팅된 가격 설정
        }

        model.addAttribute("dtoList", dtoList);  // 모델에 장바구니 목록 추가
        return "cart";  // 장바구니 페이지로 이동
    }

    // 상품 문의 작성 페이지
    @RequestMapping("/inquiryWriteForm")
    public String writeForm() {
        return "inquiryWriteForm";
    }

    // 리뷰 작성 페이지
    @RequestMapping("/review_write")
    public String reviewWriteForm(ReviewDto dto, Model model, HttpSession session, int pNo) {
        String id = (String) session.getAttribute("id");  // 사용자 ID 가져오기
        ReviewDto getReviewContent = dto;  // 리뷰 내용 가져오기

        model.addAttribute("id", id);  // 모델에 사용자 ID 추가
        return "reviewWriteForm";  // 리뷰 작성 페이지로 이동
    }

    // 결제 페이지
    @RequestMapping("/pay")
    public String getPurchase(String id, int order_idx, Model model) {
        ArrayList<PurchaseDto> dto = os.getPurchase(order_idx, id);  // 주문 정보 가져오기
       
        model.addAttribute("dtoList", dto);  // 모델에 주문 정보 추가
        return "order";  // 결제 페이지로 이동
    }

    // 장바구니에서 결제 페이지로 이동
    @RequestMapping("/insertCartToPay")
    public String insertCartToPay(String cart_idx_list, String qty_list, HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");  // 사용자 ID 가져오기
        if (id == null) id = "ygyg12";  // 기본 ID 설정
        String[] arrCartIdx = cart_idx_list.split("_");  // 장바구니 항목 인덱스 배열로 분할
        String[] arrQty = qty_list.split("_");  // 수량 배열로 분할
        ArrayList<Integer> listCartIdx = new ArrayList<Integer>();  // 장바구니 인덱스 목록 생성
        for (String s : arrCartIdx) {
            listCartIdx.add(Integer.parseInt(s));  // 인덱스를 정수로 변환하여 리스트에 추가
        }
        ArrayList<Integer> listQty = new ArrayList<Integer>();  // 수량 목록 생성
        for (String s : arrQty) {
            listQty.add(Integer.parseInt(s));  // 수량을 정수로 변환하여 리스트에 추가
        }

        os.insertCartToPay(id);  // 주문 정보 추가
        int orderIdx = os.getMaxOrderIdx(id);  // 가장 최근 주문 인덱스 가져오기
        os.insertCartToPayDetail(listCartIdx, listQty, orderIdx, id);  // 주문 상세 정보 추가

        Map<String, Object> getAddress = os.getAddress(id);  // 회원 주소 정보 가져오기
        model.addAttribute("address", getAddress);  // 모델에 주소 정보 추가
        model.addAttribute("id", id);  // 모델에 사용자 ID 추가

        // 결제 페이지로 포워드
        return "forward:/order?order_idx=" + orderIdx;
    }
}
