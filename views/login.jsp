<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!-- JSP 페이지에서 msg 값이 존재하면 경고창을 띄우는 부분 -->
<c:if test="${not empty msg}">
    <script>
        alert('${msg}');
    </script>
</c:if>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/login.css" rel="stylesheet">
    <title>Login Page</title>
    <jsp:include page="common.jsp"/>
    <!-- 네이버 로그인 관련 스크립트 -->
    <script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
    <!-- Bootstrap 관련 스크립트 -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
    <div class="login-container">
        <p>아이디와 비밀번호 입력하기 귀찮으시죠?<br>네이버 1초 만에 로그인 하세요.</p>
        
        <!-- 네이버 로그인 버튼 노출 영역 -->
        <div id="naver_id_login"></div>

        <!-- 네이버 로그인 스크립트 -->
        <script type="text/javascript">
            var naver_id_login = new naver_id_login("ATvJ8VxFAAT6bQkZ3j1n", "http://localhost:9090/shoppingMall/naverLogin");
            var state = naver_id_login.getUniqState();
            naver_id_login.setButton("green", 2, 40);  // 네이버 로그인 버튼 설정
            naver_id_login.setDomain("http://localhost:9090");
            naver_id_login.setState(state);
            naver_id_login.setPopup();  // 팝업 방식으로 로그인 처리
            naver_id_login.init_naver_id_login();
        </script>
        
        <!-- 기존 회원 로그인 폼 -->
        <form action="${pageContext.request.contextPath}/loginCheck" method="post">
            <div class="divider"><span>기존 회원 로그인</span></div>
            <input type="text" name="id" placeholder="아이디" required>
            <input type="password" name="pw" placeholder="패스워드" required>
            <button type="submit">기존 회원 로그인</button>
        </form>
        <div class="security-notice">🔒 보안접속</div>
        <div class="links">
            <a href="#">아이디찾기</a> |
            <a href="#">비밀번호찾기</a> |
            <a href="#">회원가입</a>
        </div>
    </div>
</body>
</html>
