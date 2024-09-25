<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입 Page</title>
    <!-- Daum 주소 API를 로드하는 스크립트 -->
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <link href="${pageContext.request.contextPath}/resources/css/join.css" rel="stylesheet"/>
    
    <script>
        // jQuery를 이용한 이벤트 처리
        $(function(){
            // 주소 입력 클릭 이벤트 처리
            $("#address").click(function(){
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 주소 변수 선언
                        var addr = ''; // 주소 변수
                        var extraAddr = ''; // 참고항목 변수

                        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                        if (data.userSelectedType === 'R') { 
                            // 사용자가 도로명 주소를 선택했을 경우
                            addr = data.roadAddress;
                        } else { 
                            // 사용자가 지번 주소를 선택했을 경우
                            addr = data.jibunAddress;
                        }

                        // 참고항목이 있는 경우 조합하여 넣기
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
            
            // 회원가입 버튼 클릭 이벤트 처리
            $("#btn1").click(function(){
                checkSubmit();
            });
            
            // 회원가입 취소 버튼 클릭 이벤트 처리
            $("#btn2").click(function(){
                if(confirm("회원가입 작성을 취소하시겠습니까?")){
                    location.href="${pageContext.request.contextPath}/login";
                }
            });

            // 아이디 중복 체크 이벤트 처리
            $("#duplication").click(function(){
                let id = $("#id").val();
                
                // 아이디 유효성 검사
                if(id==null && id=="") {
                    event.stopPropagation();
                }

                // Ajax 요청을 통해 서버에서 중복 확인
                $.ajax({
                    type: 'post',
                    data: JSON.stringify({"id":id}),
                    contentType: "application/json; charset=utf-8",
                    datatype: "json", 
                    url: '${pageContext.request.contextPath}/ajax/duplicationId',
                    success: function(item) {
                        let result = item.idFromDB;
                        if(result=="false") {
                            alert("사용가능한 아이디 입니다.");
                        } else if(result=="true") {
                            alert("중복된 아이디 입니다.");
                        }   
                    },
                    error: function(r,s,e) {
                        console.log("[에러] code:" + r.status
                                + "message:" + r.responseText
                                + "error:" + e);
                    }
                });
            });

            // 입력 폼 유효성 검사 함수
            function checkSubmit() {
                let id = $("#id").val();
                let pw = $("#pw").val();
                let pw2 = $("#pwCheck").val();
                let name = $("#name").val();
                let email = $("#email").val();

                let idCheck = /^[a-z0-9]{4,16}$/;
                let pwCheck = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{10,16}$/;
                let nameCheck = /^[가-힣]+$/;
                let emailCheck = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

                if(!idCheck.test(id)){
                    alert("아이디 형식에 맞게 입력해주세요 (영문소문자/숫자, 4~16자)");
                    $("#id").focus();
                    return false;
                }
                if(!pwCheck.test(pw)){
                    alert("비밀번호 형식에 맞게 입력해주세요 (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)");
                    $("#pw").focus();
                    return false;
                }
                if(pw != pw2){
                    alert("비밀번호를 확인해주세요");
                    $("#pwCheck").focus();
                    return false;
                }
                if(!nameCheck.test(name)){
                    alert("이름을 확인해주세요 (한글만 허용)");
                    $("#name").focus();
                    return false;
                }
                if(!emailCheck.test(email)){
                    alert("이메일 형식을 확인해주세요");
                    $("#email").focus();
                    return false;
                }
                alert("회원가입이 완료되었습니다.");
                // 폼을 서버로 제출
                $("form").submit();
            }

        });
    </script>  
</head>
<body>
    <!-- 헤더 포함 -->
    <jsp:include page="header/header.jsp"/>  
    
    <!-- 메인 콘텐츠 영역 -->
    <div id="contents">
        <div id="titleArea">
            <h1>회원가입</h1>
        </div>
        <div id="join">
            <form action="joinSuccess" method="post" >
                <table>
                    <tr>
                        <th> 
                            아이디
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="text" name="id" id="id"/>
                            <span> (영문소문자/숫자, 4~16자)</span>
                            <input type="button" name="duplication" id="duplication" value="아이디 중복확인"/>
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            비밀번호
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="password" name="pw" id="pw" />
                            <span> (영문 대소문자/숫자/특수문자 중 2가지 이상 조합, 10자~16자)</span>
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            비밀번호 확인
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="password" name="pwCheck" id="pwCheck"/>
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            주소
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="text" id="sample6_postcode" placeholder="우편번호" name="postCode" id="postcode">
                            <img id="address" src="https://img.echosting.cafe24.com/skin/base_ko_KR/order/btn_zipcode.gif">
                            <br/>
                            <input type="text" id="sample6_address" placeholder="주소" name="address1" id="address1"><br>
                            <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="address2" id="address2">
                            <input type="text" id="sample6_extraAddress" placeholder="참고항목">
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            이름
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="text" name="name" id="name" />
                        </td>
                    </tr>
                    <tr>
                        <th>
                            연락처
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="text" name="phone" placeholder="숫자만 입력해주세요" id="phone"/>
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            이메일
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="email" name="email" id="email" />
                        </td>
                    </tr>
                    <tr>
                        <th> 
                            이메일 수신여부
                            <img src="https://img.echosting.cafe24.com/skin/base/common/ico_required.gif"/>
                        </th>
                        <td>
                            <input type="checkbox" name="emailCheck"/> 
                            동의함
                            <p>
                                쇼핑몰에서 제공하는 유익한 이벤트 소식을 이메일로 받으실 수 있습니다.
                            </p>
                        </td>
                    </tr>
                </table>
                
                <!-- 버튼 영역 -->
                <div id="btn">
                    <input type="button" id="btn1" />
                    <input type="button" id="btn2">
                </div>
            </form>
        </div>
    </div>
</body>
</html>
