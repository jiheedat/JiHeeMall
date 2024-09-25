<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>메인페이지</title>
<link href="${pageContext.request.contextPath}/resources/css/mainPage.css" rel ="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/footer.css" rel ="stylesheet">	
<script>
	$(function(){
		
		// 아이템 클릭 -> 상세 화면으로 이동
		$(".item").click(function(){
			let pNo = $(this).attr("pno"); // 상품번호
			location.href="detailPage?pNo="+pNo;
		});
		
		// 아이템위 마우스오버 -> 찜화면 나타남 
		$(".item").mouseover(function(){
			
			let pNo = $(this).attr('pno');
			let id = "${id}";
			let _this =$(this); 
			
			// 찜 -> 하트 채우기 OR 공백
		 	$.ajax({
				type: 'post',
				data: JSON.stringify({"pNo":pNo,"id":id}),
				contentType: "application/json; charset=utf-8",
				datatype: "json", 
				url: '${pageContext.request.contextPath}/ajax/zzimHeart',
				success: function(obj) {
					
					_this.find($('.heart')).css('display','block');
					if(obj==0) {
						_this.find(".zzim_img").attr('src','https://ezendolls.com/web/upload/icon_201611291648197400.png');
					} else {
						_this.find(".zzim_img").attr('src','https://ezendolls.com/web/upload/icon_201611291648335300.png');
					}
		 		},
				error: function(r,s,e) {
					 alert("[에러] code:" + r.status
							+ "message:" + r.responseText
							+ "error:" + e); 
				}
			});
		});
		
		// 이벤트 전파를 막아서 부모 요소로의 전파를 차단
	    $(".zzim_img").click(function(event) {
	        event.stopPropagation(); 
	    });
		
		// 찜 버튼 사라짐
		$(".item").mouseout(function(){
			$(this).find(".heart").css("display","none");
		});
		

		// 찜 버튼 클릭
		$(".zzim_img").click(function(){
			let pNo = $(this).parents(".item").attr('pno'); // 상품번호
			let id = "${id}"; // 구매자 아이디
			
			let _this =$(this); 
			$.ajax({
				type: 'post',
				data: JSON.stringify({"pNo":pNo,"id":id}),
				contentType: "application/json; charset=utf-8",
				datatype: "json", 
				url: '${pageContext.request.contextPath}/ajax/toggleZzim',
				success: function(obj) {
					
 					if(obj==0) { 
 						_this.attr('src','https://ezendolls.com/web/upload/icon_201611291648197400.png'); 
 					} else {
 						_this.attr('src','https://ezendolls.com/web/upload/icon_201611291648335300.png');  
 					} 
		 		},
				error: function(r,s,e) {
					 alert("[에러] code:" + r.status
							+ "message:" + r.responseText
							+ "error:" + e); 
				}
			});
 		});
	});
</script>
</head>
<body>
	<!--  header -->
	<jsp:include page="header/header.jsp"/>
	<div> <!-- 상단배너 슬라이스 -->
		<img id="banner_img" src="resources/img/main.jpg"/>
	</div>
	<div id="contents">
		<div id="title" class="fl">PRODUCT</div>
		<div style="clear:both;"></div>
		<div id="items">
			<div id="product">
				<c:forEach var="dto" items="${list}">
					<div class="item fl" pno="${dto.pNo}">
						<div><img class="item_img" src="${dto.pImg }"/></div>
						<div class="description">
							<div class="item_name">${dto.pName}</div>
							<div class="currency">${dto.price}<span>원</span></div>
							<div class="price">
								${dto.setPrice}<span>원</span>
							</div>
						</div>
					<div class="heart">
						<img src="" class="zzim_img"/>
					</div>
					</div>
				</c:forEach>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<jsp:include page="footer/footer.jsp"/>
</body>
</html>