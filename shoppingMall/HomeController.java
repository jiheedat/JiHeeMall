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

    // �α׸� ����ϴ� Logger ��ü ����
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    HttpServletRequest hs;

    // ��ǰ ���� ����
    @Autowired
    ProductService ps;

    // ȸ�� ���� ���� 
    @Autowired
    MemberService ms;

    // �ֹ� ���� ����
    @Autowired
    OrderService os;

    // ���� �� ���� ���� ����
    @Autowired
    BoardService bs;

    // ���������� -> ����� �⺻ȭ��
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpSession session, Model model, String search) {

        // ��ǰ ����� ������ ����Ʈ ����
        ArrayList<ProductDto> list = new ArrayList<ProductDto>();

        // �˻�� ���ų� �� ���ڿ��̸� ��ü ��ǰ ����� ������
        if (search == null || search.equals("")) {
            search = "";
            list = ps.getProductList();  // ��� ��ǰ ��� �ҷ�����
        } else {
            list = ps.getSearchProduct(search);  // �˻���� ���͸��� ��ǰ ��� �ҷ�����
        }

        // ���ǿ��� ����� ID�� ������ (�α��� ���� Ȯ�ο�)
        String id = (String) session.getAttribute("id");

        // ������ õ ������ �����ϴ� DecimalFormat ��ü ����
        DecimalFormat decFormat = new DecimalFormat("###,###");

        // �� ��ǰ�� ������ �����Ͽ� ǥ�õ� ����(setPrice) ����
        for (ProductDto product : list) {
            int price = product.getPrice();  // ���� ����
            String formattedPrice = decFormat.format(price);  // õ ������ �޸� �߰��� ����
            product.setSetPrice(formattedPrice);  // ��ǰ�� ���˵� ���� ����
        }
        
        model.addAttribute("list", list);
        model.addAttribute("id", id);

        // mainPage.jsp�� �̵� (��ǰ ��ϰ� ����� ������ ���� ���� ������)
        return "mainPage";
    }

    // ȸ������ �������� �̵�
    @RequestMapping("/join")
    public String join() {
        return "join";
    }

    // �α׾ƿ� ó�� �� �α��� �������� �����̷�Ʈ
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // ���� ��ȿȭ (�α׾ƿ�)
        return "redirect:login";
    }

    // �α��� �������� �̵�
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // �α��� ���� üũ
    @RequestMapping("/loginCheck")
    public String loginCheck(String id, String pw, HttpSession session, Model model) {
        boolean result = ms.loginCheck(id, pw);  // �α��� üũ
        if (result == true) {
            session.setAttribute("id", id);  // ���ǿ� ID ����
            return "redirect:/";  // ���� �������� �����̷�Ʈ
        } else {
            model.addAttribute("msg", "�α��� ������ Ȯ���� �ּ���");  // �α��� ���� �� �޽��� ����
            return "forward:/login";  
        }
    }

    // ���̹� �α��� �ݹ� ó��
    @RequestMapping("/naverLogin")
    public String naverLogin() {
        return "naver";
    }

    // ��ǰ �� ������
    @RequestMapping("/detailPage")
    public String detail(HttpServletRequest request, HttpSession session, Model model, Integer page) {
        int pNo = Integer.parseInt(request.getParameter("pNo"));  // ��ǰ ��ȣ ��������
        ProductDto productDto = ps.getProductByPno(pNo);  // ��ǰ ���� ��������
        ArrayList<OptionDto> option = ps.getOption(pNo);  // ��ǰ �ɼ� ��������

        if (page == null) {
            page = 1;
        }
        ArrayList<ReviewDto> reviewList = bs.getReviewList(page, pNo);  // ���� ��� ��������
        int lastPage = bs.getLastPageNum(pNo);  // ������ ������ ��ȣ ��������

        // ������ õ ������ �����ϴ� DecimalFormat ��ü ����
        DecimalFormat decFormat = new DecimalFormat("###,###");
        String price = decFormat.format(productDto.getPrice());  // ��ǰ ���� ������
        String salePrice = decFormat.format(productDto.getSalePrice());  // ���� ���� ������

        int endNum = page - (page % 5) + 5 - (page % 5 == 0 ? 5 : 0);  // ������ �׺���̼��� ������ ��ȣ
        int startNum = endNum - 4;  // ������ �׺���̼��� ���� ��ȣ

        String id = (String) session.getAttribute("id");  // ����� ID ��������

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

        // �� �������� �̵�
        return "detailPage";
    }

    // �ֹ� ������
    @RequestMapping("/order")
    public String order(int order_idx, Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");
        
        if (id == null) id = "ygyg12";  // ���ǿ� ID�� ���� ��� �⺻�� ����
		/*
		 * System.out.println("����"); System.out.println("order_idx: " + order_idx);
		 * System.out.println("id : " + id);
		 */

        ArrayList<PurchaseDto> dto = os.getPurchase(order_idx, id);  // �ֹ� ���� ��������

        model.addAttribute("dtoList", dto);  // �𵨿� �ֹ� ���� �߰�

        return "order";  // �ֹ� �������� �̵�
    }

    // ��ǰ ���� �ۼ� ������
    @RequestMapping("/write")
    public String write() {
        return "productInquiry";
    }

    // ȸ������ ���� ó��
    @RequestMapping("/joinSuccess")
    public String joinSuccess(MemberDto dto) {
        ms.inertMember(dto);  // ȸ�� ���� ����
        return "redirect:/";  // ���� �������� �����̷�Ʈ
    }

    // ��ٱ��Ͽ� ��ǰ �߰�
    @RequestMapping("/insertCart")
    public String insertCart(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        String btn_name = request.getParameter("btn_name");  // ��ư �̸� ��������
        int num = Integer.parseInt(request.getParameter("g_max_option_number"));  // ������ �ɼ��� ���� ��������
        if (btn_name.equals("cart")) {
            String id = (String) session.getAttribute("id");  // ����� ID ��������
            for (int i = 1; i <= num; i++) {
                int optionIdx = Integer.parseInt(request.getParameter("option" + i));  // �ɼ� ��ȣ ��������
                int qty = Integer.parseInt(request.getParameter("qty" + i));  // ���� ��������
                CartDto dto = new CartDto(0, id, optionIdx, qty);  // ��ٱ��� DTO ����
                os.insertCart(dto);  // ��ٱ��Ͽ� ��ǰ �߰�
            }
            return "redirect:/cart";  // ��ٱ��� �������� �����̷�Ʈ
        } else {
            return "redirect:/order";  // �ֹ� �������� �����̷�Ʈ
        }
    }

    // ��ٱ��� ������
    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session) {
        System.out.println("��ٱ��� ����");
        DecimalFormat decFormat = new DecimalFormat("###,###");  // ���� �������� ���� ��ü ����
        ArrayList<CartViewDto> dtoList = os.getCart("ygyg12");  // ��ٱ��� ��� ��������

        for (CartViewDto dto : dtoList) {
            int price = dto.getPrice();  // ��ǰ ���� ��������
            String setPrice = decFormat.format(price);  // ���� ������
            dto.setSetPrice(setPrice);  // �����õ� ���� ����
        }

        model.addAttribute("dtoList", dtoList);  // �𵨿� ��ٱ��� ��� �߰�
        return "cart";  // ��ٱ��� �������� �̵�
    }

    // ��ǰ ���� �ۼ� ������
    @RequestMapping("/inquiryWriteForm")
    public String writeForm() {
        return "inquiryWriteForm";
    }

    // ���� �ۼ� ������
    @RequestMapping("/review_write")
    public String reviewWriteForm(ReviewDto dto, Model model, HttpSession session, int pNo) {
        String id = (String) session.getAttribute("id");  // ����� ID ��������
        ReviewDto getReviewContent = dto;  // ���� ���� ��������

        model.addAttribute("id", id);  // �𵨿� ����� ID �߰�
        return "reviewWriteForm";  // ���� �ۼ� �������� �̵�
    }

    // ���� ������
    @RequestMapping("/pay")
    public String getPurchase(String id, int order_idx, Model model) {
        ArrayList<PurchaseDto> dto = os.getPurchase(order_idx, id);  // �ֹ� ���� ��������
       
        model.addAttribute("dtoList", dto);  // �𵨿� �ֹ� ���� �߰�
        return "order";  // ���� �������� �̵�
    }

    // ��ٱ��Ͽ��� ���� �������� �̵�
    @RequestMapping("/insertCartToPay")
    public String insertCartToPay(String cart_idx_list, String qty_list, HttpSession session, Model model) {
        String id = (String) session.getAttribute("id");  // ����� ID ��������
        if (id == null) id = "ygyg12";  // �⺻ ID ����
        String[] arrCartIdx = cart_idx_list.split("_");  // ��ٱ��� �׸� �ε��� �迭�� ����
        String[] arrQty = qty_list.split("_");  // ���� �迭�� ����
        ArrayList<Integer> listCartIdx = new ArrayList<Integer>();  // ��ٱ��� �ε��� ��� ����
        for (String s : arrCartIdx) {
            listCartIdx.add(Integer.parseInt(s));  // �ε����� ������ ��ȯ�Ͽ� ����Ʈ�� �߰�
        }
        ArrayList<Integer> listQty = new ArrayList<Integer>();  // ���� ��� ����
        for (String s : arrQty) {
            listQty.add(Integer.parseInt(s));  // ������ ������ ��ȯ�Ͽ� ����Ʈ�� �߰�
        }

        os.insertCartToPay(id);  // �ֹ� ���� �߰�
        int orderIdx = os.getMaxOrderIdx(id);  // ���� �ֱ� �ֹ� �ε��� ��������
        os.insertCartToPayDetail(listCartIdx, listQty, orderIdx, id);  // �ֹ� �� ���� �߰�

        Map<String, Object> getAddress = os.getAddress(id);  // ȸ�� �ּ� ���� ��������
        model.addAttribute("address", getAddress);  // �𵨿� �ּ� ���� �߰�
        model.addAttribute("id", id);  // �𵨿� ����� ID �߰�

        // ���� �������� ������
        return "forward:/order?order_idx=" + orderIdx;
    }
}
