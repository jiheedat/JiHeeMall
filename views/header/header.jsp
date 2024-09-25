<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
<script>
	$(function(){
		// 로그인 상태에 따라 로그인, 로그아웃, 회원가입, 정보수정 버튼을 변경
		var sessionUserId = "${sessionScope.id}";  // 세션에서 id 값 가져오기
     	if (sessionUserId === null || sessionUserId === "") {
      		// 로그인 상태가 아닐 때
      		$(".glyphicon-user").text(" 로그인");
      		$(".glyphicon-log-in").text(" 회원가입");
      	} else {
      		// 로그인 상태일 때
      		$(".glyphicon-user").text(" 로그아웃");
      	 	$(".glyphicon-log-in").text(" 정보수정");
      	}
		
		// 로그인 또는 로그아웃 버튼 클릭 시
		$(".glyphicon-user").click(function(){
			let sessionIdText = $(".glyphicon-user").text(); // 로그인 or 로그아웃 판별 변수 
			
			if(sessionIdText == " 로그인") {
				// 로그인 클릭 시 로그인 페이지로 이동
				alert("로그인 클릭");
				location.href="${pageContext.request.contextPath}/login";
			} else if(sessionIdText == " 로그아웃") {
				// 로그아웃 클릭 시 로그아웃 여부를 확인하고 로그아웃 처리
				if(confirm("로그아웃 하시겠습니까?")) {
					location.href="${pageContext.request.contextPath}/logout";
				} 
			}
		});
		
		// 회원가입 또는 정보수정 버튼 클릭 시
		$(".glyphicon-log-in").click(function(){
			let joinOrModify = $(".glyphicon-log-in").text();
			if(joinOrModify == " 회원가입") {
				// 회원가입 클릭 시 회원가입 페이지로 이동
				location.href="${pageContext.request.contextPath}/join";
			} else if(joinOrModify == " 정보수정") {
				// 정보수정 클릭 시 정보수정 페이지로 이동 (현재는 alert만 표시)
				alert("정보수정으로 이동");
			}
		});
	});
</script>	
</head>
<body>
	<!-- 네비게이션 바 -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- 로고 -->
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/" id="mall"> 
			    	JiHee Mall
			    </a>
			</div>
			<!-- 상품 카테고리 -->
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">mall</a></li>
			    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> 동물인형<span class="caret"></span></a>
			      <ul class="dropdown-menu">
			        <li><a href="${pageContext.request.contextPath}/?search=쿼카">쿼카</a></li>
			        <li><a href="${pageContext.request.contextPath}/?search=강아지">강아지</a></li>
			        <li><a href="${pageContext.request.contextPath}/?search=토끼">토끼</a></li>
			      </ul>
			    </li>
			    <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#"> 캐릭터인형<span class="caret"></span></a>
			    	<ul class="dropdown-menu">
			        <li><a href="${pageContext.request.contextPath}/?search=짱구">짱구</a></li>
			        <li><a href="${pageContext.request.contextPath}/?search=디즈니">디즈니</a></li>
			      </ul>
			   </li>
			</ul>
			<!-- 검색 폼 -->
		  <form action="${pageContext.request.contextPath}/" class="navbar-form navbar-left">
		    <div class="input-group">
		      <input type="text" class="form-control" placeholder="Search" name="search">
		      <div class="input-group-btn">
		        <button style="margin-left:5px;" class="btn btn-default" type="submit">
		          <i class="glyphicon glyphicon-search"></i>
		        </button>
		      </div>
		    </div>
		  </form>
		  <!-- 우측 네비게이션 버튼들 (로그인, 회원가입/정보수정, 장바구니) -->
		  <ul class="nav navbar-nav navbar-right">
		    <li><a href="#"><span class="glyphicon glyphicon-user"></span></a></li>
		    <li><a href="#"><span class="glyphicon glyphicon-log-in"></span></a></li>
		    <li><a href="${pageContext.request.contextPath}/cart"><span class="far fa-smile"></span> 장바구니</a></li>
		  </ul>
		</div>
	</nav>
</body>
</html>
