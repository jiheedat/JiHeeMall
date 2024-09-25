<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버 로그인 callback</title>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
 		 var naver_id_login = new naver_id_login("ATvJ8VxFAAT6bQkZ3j1n", "http://localhost:9090/shoppingMall/naverLogin");
 		 // 접근 토큰 값 출력
		/*   alert(naver_id_login.oauthParams.access_token); */
		  // 네이버 사용자 프로필 조회
		  naver_id_login.get_naver_userprofile("naverSignInCallback()");
  
		  // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		  function naverSignInCallback() {
			  let email = naver_id_login.getProfileData('email');
			  let id = naver_id_login.getProfileData('id');
			  
			  alert(email);
			  alert(id);
		    
		    /* location.href로 이동... 아마도 컨트롤러? */
		  }
	</script>
	
</body>
</html>