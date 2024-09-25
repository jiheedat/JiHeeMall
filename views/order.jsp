<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>주문서 작성</title>
    
    <!-- 외부 CSS 파일 -->
    <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
    
    <!-- Daum 주소 API 및 카카오페이 API -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script type="text/javascript" src="https://service.iamport.kr/js/iamport.payment-1.1.5.js"></script>
    
    <script>
        $(function(){ 
            // 주소 API
            $("#address").click(function(){
                new daum.Postcode({
                    oncomplete: function(data) {
                        var addr = ''; // 주소 변수
                        var extraAddr = ''; // 참고항목 변수
    
                        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                        if (data.userSelectedType === 'R') {
                            addr = data.roadAddress; // 도로명 주소
                        } else {
                            addr = data.jibunAddress; // 지번 주소
                        }

                        // 도로명 주소일 때 참고항목 추가
                        if(data.userSelectedType === 'R'){
                            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                                extraAddr += data.bname;
                            }
                            if(data.buildingName !== '' && data.apartment === 'Y'){
                                extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                            }
                            if(extraAddr !== ''){
                                extraAddr = ' (' + extraAddr + ')';
                            }
                            document.getElementById("sample6_extraAddress").value = extraAddr;
                        } else {
                            document.getElementById("sample6_extraAddress").value = '';
                        }

                        // 우편번호와 주소 정보를 해당 필드에 넣기
                        document.getElementById('sample6_postcode').value = data.zonecode;
                        document.getElementById("sample6_address").value = addr;
                        document.getElementById("sample6_detailAddress").focus();
                    }
                }).open();
            });
            
            // 카카오페이 API 호출
            function do_kakao() {
                var IMP = window.IMP; 
                IMP.init('imp26315802'); 
                IMP.request_pay({
                    pg: 'kakaopay.TC0ONETIME',
                    pay_method: 'card',
                    merchant_uid: 'merchant_' + new Date().getTime(),
                    name: 'JiHeeMall PRODUCT',
                    amount: 10350, // 금액 설정
                    buyer_name: '이름',
                    buyer_postcode: '123-456',
                }, function (rsp) {
                    console.log(rsp);
                    var msg = '';
                    if (rsp.success) {
                        msg = '결제가 완료되었습니다. 결제 금액: ' + rsp.paid_amount;
                    } else {
                        msg = '결제에 실패하였습니다. 에러 내용: ' + rsp.error_msg;
                    }
                    alert(msg);
                });
            }

            // 결제하기 버튼 클릭 시 카카오페이 연동
            $("#kakaoPay").click(function(){
                do_kakao();
            });
        });
    </script>

    <!-- 주문 페이지 관련 CSS 파일 -->
    <link href="${pageContext.request.contextPath}/resources/css/order.css" rel="stylesheet">	
</head>
<body>
    <!-- 헤더 포함 -->
    <jsp:include page="header/header.jsp"/>
    
    <div id="wrap">
        <div id="titleArea">
            <h1>주문서 작성</h1>
        </div>
        <div id="orderList">
            <div>
                <h3 id="orderHeader">상품 주문내역</h3>
            </div>
            <div>
                <table class="order-table">
                    <thead>
                        <tr id="orderTitle">
                            <th><input type="checkbox"/></th>
                            <th>이미지</th>
                            <th>상품정보</th>
                            <th>판매가</th>
                            <th>수량</th>
                            <th>합계</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${dtoList}">
                            <tr class="order_item">
                                <td><input type="checkbox" name="checkbox"></td>
                                <td><img style="width:90px;" src="${dto.pImg}"/></td>
                                <td>${dto.pName}</td>
                                <td><fmt:formatNumber value="${dto.price}" type="number" groupingUsed="true"/></td>
                                <td>${dto.qty}</td>
                                <td><fmt:formatNumber value="${dto.price*dto.qty}" groupingUsed="true"/></td>
                                <td>
                                    <input type="hidden" name="order_idx" value="${dto.order_idx}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="8" id="totalPrice"></td>
                        </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <div id="delivery_head">
            <div class="fl">배송정보</div>
            <div class="fl">
                <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/ico_required.gif"/>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div id="delivery">
            <table id="delivery_t">
                <tr>
                    <th>배송지 선택</th>
                    <td>
                        <div>
                            <span><input type="radio" name="address" value="회원정보와 동일" checked="checked"> 회원 정보와 동일</span>
                            <span><input type="radio" name="address" value="새로운 주소지"> 새로운 주소지</span>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th>받으시는 분</th>
                    <td><input type="text" value="${id}"/></td>
                </tr>
                <tr>
                    <th>주소</th>
                    <td>
                        <input type="text" id="sample6_postcode" placeholder="우편번호" value="${address.POSTCODE}">
                        <img id="address" src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/btn_zipcode.gif"/><br/>
                        <input type="text" id="sample6_address" placeholder="주소" value="${address.ADDRESS1}"><br/>
                        <input type="text" id="sample6_detailAddress" placeholder="상세주소" value="${address.ADDRESS2}">
                        <input type="text" id="sample6_extraAddress" placeholder="참고항목">
                    </td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td><input type="text" name="number" placeholder="번호만 입력해 주세요"/></td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td>
                        <input type="email" name="email" placeholder="이메일 입력"/> @ <input type="text" name="email2"/>
                    </td>
                </tr>
                <tr>
                    <th>배송메세지</th>
                    <td>
                        <textarea></textarea>
                    </td>
                </tr>
            </table>
        </div>
        <div id="pay_title">결제수단</div>
        <div id="pay_area">
            <div class="fl kakao">
                <input type="radio" name="pay"/>
                <span>카카오페이</span>
            </div>
            <div class="fr">
                <div>최종결제 금액</div>
                <div>23,000<span>원</span></div>
                <div>
                    <img id="kakaoPay" src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/btn_place_order.gif"/>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>

    <jsp:include page="footer/footer.jsp"/>
</body>
</html>
