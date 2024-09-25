<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품상세 화면</title>
    <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/detailPage.css" rel="stylesheet">

    <script>
        // 최대 옵션 번호 변수 선언
        let g_max_option_number = 0; 
        $(function(){
            // 상품 옵션 선택 시 이벤트 처리
            $("#choice").change(function(){ 
                // 옵션을 선택하면 상품 정보 표시 영역을 보이도록 처리
                $("#totalProducts_body").css('display', 'block');
                let option = $("#choice :selected").text();  // 선택된 옵션 텍스트
                let option_value =  $("#choice option:selected").val();  // 선택된 옵션 값
                g_max_option_number++;  // 옵션 번호 증가
                $("input[name='g_max_option_number']").val(g_max_option_number);

                // 이미 선택된 옵션이 있는지 확인 (중복 옵션 방지)
                if($("#totalProducts_body").find(".optionSel:contains('" + option + "')").length === 0){
                    let str = "";
                    str += "<tr>"+
                            "<td>"+
                            "<p>"+
                            "${dto.pName}"+
                            "<br/>"+
                            "<span>-</span>"+
                            "<span id='option_span'>"+
                            "<span class='optionSel'>"+option+"</span>"+
                            "<input type='hidden' name='option"+g_max_option_number+"'+ value='"+option_value+"'/>"+
                            "</span>"+
                            "</p>"+
                            "</td>"+
                            "<td id='td'>"+
                            "<span class='qty_btn'>"+
                            "<input type='text' name='qty"+g_max_option_number+"' value='1'/>"+
                            "<img class='qty_up' src='https://img.echosting.cafe24.com/design/skin/default/product/btn_count_up.gif'/>"+
                            "<img class='qty_down' src='https://img.echosting.cafe24.com/design/skin/default/product/btn_count_down.gif'/> "+
                            "</span>"+
                            "</td>"+
                            "<td id='clickPay'>${salePrice}원</td>"+
                            "</tr>";
                    // 선택된 옵션을 화면에 추가
                    $("#totalProducts_body").append(str);
                } else {
                    // 이미 선택된 옵션이 있을 경우 경고
                    alert("아래 선택 사항에서 수량을 체크해주세요");
                }
            });

            // 수량 증가 버튼 클릭 시 처리
            $("#totalProducts_body").on('click', '.qty_up', function(){
                let plus = parseInt($(this).prev('input').val()); // 현재 선택된 수량
                const clickPay = ${dto.salePrice};  // 상품 단가
                plus++;  // 수량 증가

                $(this).prev('input').val(plus);  // 변경된 수량 업데이트

                let price = plus * clickPay;  // 총 가격 계산
                let formattedNumber = Number(price).toLocaleString();  // 천 단위 콤마 추가
                $(this).parents("#td").next("#clickPay").text(formattedNumber + "원");  // 총 가격 표시 업데이트

                updateTotalPrice();  // 총 금액 업데이트 함수 호출
            });

            // 수량 감소 버튼 클릭 시 처리
            $("#totalProducts_body").on('click', '.qty_down', function(){
                let minus = parseInt($(this).prevAll('input').val());  // 현재 선택된 수량
                const clickPay = ${dto.salePrice};  // 상품 단가
                minus--;  // 수량 감소
                if(minus < 1) {
                    return false;  // 수량이 1 미만이 되지 않도록 처리
                }
                $(this).prevAll('input').val(minus);  // 변경된 수량 업데이트

                let price = minus * clickPay;  // 총 가격 계산
                let formattedNumber = Number(price).toLocaleString();  // 천 단위 콤마 추가
                $(this).parents("#td").next("#clickPay").text(formattedNumber + "원");  // 총 가격 표시 업데이트

                updateTotalPrice();  // 총 금액 업데이트 함수 호출
            });

            // 선택된 상품들의 총 가격을 업데이트하는 함수
            function updateTotalPrice() {
                let total = 0;
                $("#totalProducts_body tr").each(function() {
                    let priceText = $(this).find("#clickPay").text().replace(/[^0-9]/g, '');  // 가격에서 숫자만 추출
                    total += parseInt(priceText);  // 총 금액 계산
                });
                $("#totalPay").text(total.toLocaleString() + "원");  // 총 금액 표시 업데이트
            }

            // 장바구니 버튼 클릭 시 처리
            $("#cart").click(function(){
                let option = $("#choice :selected").val();  // 선택된 옵션 값 확인
                if(option == "none") {
                    alert("필수 옵션을 선택해주세요.");  // 옵션이 선택되지 않았을 경우 경고
                } else {
                    let str = "<input type='hidden' name='btn_name' value='cart'/>";  // 장바구니에 추가할 정보를 히든 인풋에 저장
                    $("#totalProducts_body").append(str);  // 폼에 추가
                    $("form[name='submit']").submit();  // 폼 제출
                }
            });

            // 리뷰 글쓰기 버튼 클릭 시 처리
            $("#review_write").click(function(){
                let pNo = ${pNo};  // 상품 번호
                location.href = "${pageContext.request.contextPath}/review_write?pNo=" + pNo;  // 리뷰 작성 페이지로 이동
            });

            // 바로 구매 버튼 클릭 시 처리
            $("#pay").click(function(){
                let str = "<input type='hidden' name='btn_name' value='pay'/>";  // 바로 구매할 정보를 히든 인풋에 저장
                $("#totalProducts_body").append(str);  // 폼에 추가
                $("form[name='submit']").submit();  // 폼 제출
            });
        });

        // 문의글을 출력하고 무한스크롤 적용
        $(document).ready(function() {
            var page_num = 1;  // 페이지 번호
            var count = 0;  // 글 번호 카운트
            function draw_inquiry(page_num) {
                if(page_num == null) { page_num = 1; }  // 페이지 번호가 없을 경우 1로 설정

                $.ajax({
                    type: 'post',
                    data: JSON.stringify({"page_num": page_num, "pNo": ${dto.pNo}}),  // 요청 데이터
                    contentType: "application/json; charset=utf-8",
                    datatype: "json", 
                    url: '${pageContext.request.contextPath}/ajax/getInquiryList',  // 문의글 리스트 가져오는 URL
                    success: function(item) {
                        let str = "";
                        for(let i = 0; i < item.length; i++) {
                            count++;  // 글 번호 증가
                            str += "<tr id='inquiry'>" +
                                   "<td> No." + count + "</td>" +
                                   "<td>";
                            if(item[i].title == "답변드립니다.") {
                                str += "<img src='https://img.echosting.cafe24.com/design/skin/admin/ko_KR/ico_re.gif'>" + item[i].title;  // 답변글 아이콘
                            } else {
                                str += "<img src='https://img.echosting.cafe24.com/design/skin/admin/ko_KR/ico_lock.gif'>" + item[i].title;  // 비밀글 아이콘
                            }
                            str += "</td>" +
                                   "<td>" + item[i].id + "</td>" +
                                   "<td>" + item[i].writedate + "</td>" +
                                   "<td>" + item[i].hitcount + "</td>" +
                                   "</tr>";
                        } 
                        $("#inquiry").append(str);  // 문의글 리스트 추가
                    },
                    error: function(r, s, e) {
                        console.log("[에러] code:" + r.status +
                                    "message:" + r.responseText +
                                    "error:" + e);  // 오류 로그 출력
                    }
                });
            }
            draw_inquiry();  // 첫 번째 문의글 리스트 출력

            // 문의글 무한 스크롤 적용
            $(".inquirySet").scroll(function() {
                if($(window).scrollTop() + $(window).height() >= $(document).height()) {
                    ++page_num;  // 페이지 번호 증가
                    draw_inquiry(page_num);  // 다음 페이지의 문의글 리스트 출력
                }
            });

            // 리뷰 탭의 제목 클릭 시 상세 내용 펼치기
            $('.title').click(function() {
                $(this).closest("tr").next(".details").slideToggle();  // 리뷰 상세 내용 토글
            });

            // 상품후기 제목에 마우스오버 시 밑줄 표시
            $(".title").mouseover(function(){
                $(this).css('text-decoration', 'underline');
            });

            // 상품후기 제목에 마우스아웃 시 밑줄 제거
            $('.title').mouseout(function() {
                $(this).css('text-decoration', 'none');
            });

         // sessionStorage에 스크롤 위치가 저장되어 있는 경우, 그 위치로 이동
            if (sessionStorage.getItem('scrollPosition') !== null) {
                $(window).scrollTop(sessionStorage.getItem('scrollPosition'));
            }

            // 페이지네이션 클릭 시 스크롤 위치 저장
            $("#pagination a").click(function() {
                sessionStorage.setItem('scrollPosition', $(window).scrollTop());
            });
        });
    </script>                        
</head>
<body>
    <jsp:include page="header/header.jsp"/>
    <!-- header -->
    <div id="content">
        <div id="detail">
            <div id="detail_img" class="fl">
                <img src="${dto.pImg}"/>  <!-- 상품 이미지 -->
            </div>
            <div id="detail_item" class="fr">
                <h1 id="detail_name" class="">${dto.pName}</h1>  <!-- 상품명 -->
                <div id="detail_info">
                    <table>
                        <tr>
                            <th>판매가</th>
                            <td id="price">${salePrice}원</td>  <!-- 판매가 -->
                        </tr>
                        <tr>
                            <th>소비자가</th>
                            <td><strike>${price}</strike>원</td>  <!-- 소비자가 -->
                        </tr>
                        <tr>
                            <th>배송방법</th>
                            <td>택배</td>  <!-- 배송방법 -->
                        </tr>
                        <tr>
                            <th>배송비</th>
                            <td>3,000원 (50,000원 이상 구매 시 무료)</td>  <!-- 배송비 -->
                        </tr>
                    </table>
                </div>
                <div id="option">
                    <table>
                        <tr>
                            <th>선택</th>
                            <td>
                                <select id="choice">  <!-- 옵션 선택 -->
                                    <option value="none">- [필수] 옵션을 선택해 주세요 -</option>
                                    <c:forEach var="item" items="${option}">
                                        <option value="${item.option_idx}">${item.p_option}</option>  <!-- 옵션 목록 -->
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="minOrder">
                    <p>(최소주문수량은 1개 이상입니다.)</p>  <!-- 최소 주문 수량 안내 -->
                </div>
                <div id="totalProducts">
                    <form action="${pageContext.request.contextPath}/insertCart" name="submit">
                        <input type="hidden" name="g_max_option_number" value="0"/>  <!-- 옵션 번호 숨김 필드 -->
                        <table>
                            <thead>
                                <tr id="product_head">
                                    <th>상품명</th>
                                    <th>상품수</th>
                                    <th>가격</th>
                                </tr>
                            </thead>
                            <tbody id="totalProducts_body">  <!-- 선택된 옵션 리스트 -->
                            
                            </tbody>							
                        </table>
                    </form>
                    <div style="text-align: right; padding: 10px;">
                        총 상품금액(수량): <strong id="totalPay">${salePrice}원</strong> (1개)  <!-- 총 상품 금액 표시 -->
                    </div>
                </div>
                <div id="purchase">
                    <div>
                        <button id="pay">바로구매하기</button>  <!-- 바로 구매 버튼 -->
                        <button id="cart">장바구니</button>  <!-- 장바구니 버튼 -->
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <!-- 상품 상세 정보 및 리뷰, 문의 -->
        <div id="tabs">
            <div class="anchor">
                <div class="fl" id="tab_special">상품상세정보</div>  
                <div class="fl">상품후기</div>  
                <div class="fl">상품문의</div>  
                <div style="clear:both;"></div>
            </div>
            <div id="tab_img">
                <img src="${dto.pDetailImg}"/>  <!-- 상품 상세 이미지 -->
            </div>
            <div class="anchor">
                <div class="fl">상품상세정보</div>  
                <div class="fl" id="tab_special">상품후기</div>  
                <div class="fl">상품문의</div>  
                <div style="clear:both;"></div>
            </div>
            <div>
                <div class="board">
                    <table>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회수</th>
                            <th>평점</th>
                        </tr>
                        <c:forEach var="item" items="${reviewList}"> 
                            <tr class="inquiry">
                                <td>${item.r_idx}</td>  <!-- 리뷰 번호 -->
                                <td class="title">${item.title}</td>  <!-- 리뷰 제목 -->
                                <td>${item.id}</td>  <!-- 작성자 -->
                                <td>${item.writedate}</td>  <!-- 작성일 -->
                                <td>${item.hitcount}</td>  <!-- 조회수 -->
                                <td>
                                    <img src="https://img.echosting.cafe24.com/skin/base/board/ico_point${item.grade}.gif">  <!-- 평점 이미지 -->
                                </td>
                            </tr>
                            <tr class="details" style="display: none;">
                                <td colspan="6">
                                    ${item.content}  <!-- 리뷰 내용 -->
                                    <div>(${item.writedate})에 작성된 후기입니다.</div>
                                    <div>
                                        <img src="https://img.echosting.cafe24.com/skin/base_ko_KR/product/btn_board_modify.gif"/>  <!-- 수정 버튼 이미지 -->
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div id="pagination">
                    <c:forEach var="i" begin="${startNum}" end="${endNum}">
                        <c:if test="${i <= lastPage}">
                            <c:if test="${startNum == i && startNum - 5 > 0}">
                                <span><a href="${pageContext.request.contextPath}/detailPage?pNo=${pNo}&page=${startNum-5}">&lt;</a></span>  <!-- 이전 페이지 이동 -->
                            </c:if>
                            <c:choose>
                                <c:when test="${page == i}">
                                    <span>${i}</span>  <!-- 현재 페이지 -->
                                </c:when>
                                <c:otherwise>
                                    <span><a href="${pageContext.request.contextPath}/detailPage?pNo=${pNo}&page=${i}">${i}</a></span>  <!-- 다른 페이지 이동 -->
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${endNum == i && lastPage > endNum}">
                                <span><a href="${pageContext.request.contextPath}/detailPage?pNo=${pNo}&page=${endNum + 1}">&gt;</a></span>  <!-- 다음 페이지 이동 -->
                            </c:if>
                        </c:if>
                    </c:forEach>
                </div> 
                <div id="review_write">
                    <button>글쓰기</button>  <!-- 리뷰 글쓰기 버튼 -->
                </div>
            </div>
            <div class="anchor">
                <div class="fl">상품상세정보</div> 
                <div class="fl">상품후기</div>  
                <div class="fl" id="tab_special">상품문의</div> 
                <div style="clear:both;"></div>
            </div>
            
            <!-- 상품 문의 -->
            <div>
                <div class="inquirySet">
                    <table id="inquiry">
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>조회</th>
                        </tr>
                    </table>
                </div>
            </div>
            <button>글쓰기</button>  <!-- 문의 글쓰기 버튼 -->
        </div>
    </div>
    <jsp:include page="footer/footer.jsp"/>
</body>
</html>
