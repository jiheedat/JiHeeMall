<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>장바구니</title>
    <!-- CSS 파일 연결 -->
    <link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/cart.css" rel="stylesheet">

    <!-- JavaScript -->
    <script>
        // 천 단위 콤마를 찍는 함수
        function formatNumberWithComma(num) {
            return num.toLocaleString('en'); // 천 단위 콤마 추가
        }

        // 합계를 계산하는 함수
        function calculateTotal() {
            let sum = 0;

            // 각 상품의 합계를 계산
            $(".order_item").each(function() {
                let price = $(this).find(".price").data("price"); // 단가 가져오기
                let qty = $(this).find("input[name='qty']").val(); // 수량 가져오기
                var itemTotal = price * qty; // 각 상품의 총액 계산
                sum += itemTotal; // 전체 합계에 더하기

                // 각 상품의 합계에 콤마 추가해서 업데이트
                $(this).find(".item-total").text(formatNumberWithComma(sum) + '원');
                $(this).find(".price").text(formatNumberWithComma(itemTotal) + '원');
            });

            // 합계 표시 업데이트
            $(".item-total").text("합계 : " + formatNumberWithComma(sum) + '원');
        }

        // 페이지 로드 시 초기화
        $(document).ready(function() {
            calculateTotal(); // 처음 로드할 때 합계 계산

            // 수량 증가 버튼 클릭 시
            $(".qty_up").click(function() {
                let plus = parseInt($(this).prev('input').val());
                plus++;
                $(this).prev("input").val(plus);
                calculateTotal();
            });

            // 수량 감소 버튼 클릭 시
            $(".qty_down").click(function() {
                let minus = $(this).siblings("input[name='qty']").val(); // 현재 수량 값
                if (minus >= 1) {
                    minus--;
                    $(this).siblings("input[name='qty']").val(minus);
                    calculateTotal();
                }
            });

            // 전체 선택 여부를 확인하는 함수
            function checkAllSelected() {
                let totalCheckboxes = $('input[type="checkbox"]').length; // 총 체크박스 개수
                let checkedCheckboxes = $('input[type="checkbox"]:checked').length; // 선택된 체크박스 개수

                // 전체 체크 여부 확인
                if (totalCheckboxes === checkedCheckboxes) {
                    //console.log("모든 체크박스가 선택되었습니다.");
                    return true; // 전체 선택
                } else {
                   // console.log("모든 체크박스가 선택되지 않았습니다.");
                    return false; // 일부 선택
                }
            }

            // 선택된 항목들의 총합을 계산하는 함수
            function totalPrice() {
                var total = 0; // 총합 초기화

                // 선택된 체크박스의 가격 합산
                $('input[type="checkbox"]:checked').each(function() {
                    let price = $(this).closest('tr').find('.price').text();
                    price = parseInt(price.replace(/,/g, ''), 10); // 쉼표 제거 후 숫자로 변환
                    total += price; // 합산
                });

                // 총합 출력
                //console.log("Total: " + total + "원");
                $('#totalPrice').text(total.toLocaleString() + "원"); // HTML 요소에 출력
            }

            // 체크박스 변경 시 합계 재계산
            $('input[type="checkbox"]').on('change', function() {
                if (checkAllSelected()) {
                    let item_total = $(".item-total").text();
                    $("#totalPrice").text(item_total);
                } else {
                    totalPrice();
                }
            });

            // 초기 로드 시 합계 계산
            totalPrice();

            // 상품 가격에 콤마 적용
            $('.price').each(function() {
                var qty = $(this).closest('tr').find('input[name="qty"]').val(); // 수량 가져오기
                var price = $(this).data('price'); // 가격 가져오기
                var total = price * qty; // 가격 * 수량 계산
                $(this).text(formatNumberWithComma(total) + '원'); // 콤마 추가 후 표시
                $(this).next().text(total);
            });
        });

        // 전체 선택 체크박스 클릭 이벤트
        $(function() {
            $("#orderTitle input[type='checkbox']").click(function() {
                let yn = $(this).is(":checked");
                if (yn == true) { // 전체 선택
                    $('.order-table input[type="checkbox"]').prop("checked", true);
                } else { // 전체 선택 해제
                    $('.order-table input[type="checkbox"]').prop("checked", false);
                }
            });

            // 개별 체크박스 클릭 시 전체 선택 해제
            $('.order-table tbody input[type="checkbox"]').click(function() {
                let yn = $(this).is(":checked");
                if (yn == false) {
                    $("#orderTitle input[type='checkbox']").prop("checked", false);
                }
            });

            // 결제 버튼 클릭 시
            $("#btnOrder").click(function() {
                let list_cart_idx = "";
                let list_qty = "";

                $('.order-table tbody input[type="checkbox"]').each(function(index, item) {
                    let yn = $(item).is(":checked");
                    if (yn == true) {
                        let cart_idx = $(this).parents(".order_item").prev().val();
                        list_cart_idx += (list_cart_idx.length > 0 ? "_" : "") + cart_idx;
                        let qty = $(this).parents(".order_item").find('input[name="qty"]').val();
                        list_qty += (list_qty.length > 0 ? "_" : "") + qty;
                    }
                });

                //console.log("cart_idx list = " + list_cart_idx);
                location.href = "${pageContext.request.contextPath}/insertCartToPay?cart_idx_list=" + list_cart_idx + "&qty_list=" + list_qty;
            });
        });
    </script>
</head>
<body>
    <jsp:include page="header/header.jsp" />
    <div id="wrap">
        <div id="titleArea">
            <h1>장바구니</h1>
        </div>
        <div id="orderList">
            <div>
                <h3 id="orderHeader">상품 주문내역</h3>
            </div>
            <div>
                <table class="order-table">
                    <thead>
                        <tr id="orderTitle">
                            <th><input type="checkbox" /></th>
                            <th>이미지</th>
                            <th>상품정보</th>
                            <th>판매가</th>
                            <th>수량</th>
                            <th>합계</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${dtoList}">
                            <input type="hidden" name="cart_idx" value="${dto.cart_idx}" />
                            <tr class="order_item">
                                <td><input type="checkbox"></td>
                                <td><img style="width:90px;" src="${dto.pImg}" /></td>
                                <td>
                                    <div>${dto.pName}</div>
                                    <div class="option">(${dto.p_option})</div>
                                </td>
                                <td>${dto.setPrice}원</td>
                                <td style="display:none;">${dto.price}</td>
                                <td class="qty_btn">
                                    <input type='text' value='${dto.qty}' name='qty' />
                                    <img class='qty_up' src='https://img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif' />
                                    <img class='qty_down' src='https://img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif' />
                                </td>
                                <td class="price" data-price="${dto.price}">${dto.price * dto.qty}원</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="8" class="item-total">
                                <span>합계 :</span> 원
                            </td>
                        </tr>
                    </tfoot>
                </table>
            </div>
            <div id="item_delete">
                <span>
                    <strong>선택상품을 </strong>
                    <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/btn_delete2.gif">
                </span>
            </div>
        </div>
        <div id="pay_title">결제수단</div>
        <div id="pay_area">
            <div class="fl">
                <input type="radio" name="pay" />
                <span>카카오페이</span>
            </div>
            <div class="fr">
                <div>최종결제 금액</div>
                <div id="totalPrice"><span></span></div>
                <div id="btnOrder">
                    <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/btn_place_order.gif" />
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
    </div>
</body>
</html>
