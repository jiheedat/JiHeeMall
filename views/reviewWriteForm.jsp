<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/resources/css/reviewWriteForm.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">

    <script>
        $(function(){
            // 굵게 버튼 클릭 이벤트
            $("#btn-bold").click(function(){
                document.execCommand('bold'); 
                $("#editor").focus(); 
            });

            // 기울임 버튼 클릭 이벤트
            $("#btn-italic").click(function(){
                document.execCommand('italic'); 
                $("#editor").focus(); 
            });
            
            // 밑줄 버튼 클릭 이벤트
            $("#btn-underline").click(function(){
                document.execCommand('underline'); 
                $("#editor").focus(); 
            });
            
            // 취소선 버튼 클릭 이벤트
            $("#btn-strike").click(function(){
                document.execCommand('strikethrough'); 
                $("#editor").focus(); 
            });
            
            // 편집기의 입력이 변경될 때 콘솔에 출력
            $("#editor").on('input', function(){
                console.log($(this).html());
            });
            
            // contenteditable의 내용을 숨겨진 input에 업데이트
            $("#editor").on('input', function() {
                $("input[name='content']").val($(this).html()); 
            });
        });
    </script>
</head>
<body>
    <!-- 헤더 포함 -->
    <jsp:include page="header/header.jsp"/>
    
    <div id="wrap">
        <div id="container">
            <div id="contents">
                <div class="xans-element">
                    <div class="board-title">
                        <div class="path">
                            <ol class="ol">
                                <li style="background:none; color:#757575;">
                                    <a>홈</a>
                                </li>
                                <li style="color:#757575;">
                                    <a>게시판</a>
                                </li>
                                <li title="현재위치">
                                    <strong>상품 후기</strong>
                                </li>
                            </ol>
                            <div style="clear:both;"></div>
                        </div>
                        <div class="title">
                            <h2>
                                <font color="333333">상품 후기</font>
                            </h2>
                            <p>상품 후기 게시판입니다.</p>
                        </div>
                    </div>
                    <form id="boardWriteForm" action="${pageContext.request.contextPath}/insertReview" method="post">
                        <div class="board-write">
                            <div class="typeWrite">
                                <table class="line" border="1">
                                    <tbody>
                                        <tr>
                                            <th scope="row">제목</th>
                                            <td>
                                                <input type="text" name="title"/>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">작성자</th>
                                            <td id="writer">${id}</td>
                                        </tr>
                                        <tr>
                                            <th scope="row">평점</th>
                                            <td>
                                                <label for="grade"></label>
                                                <select name="grade" id="grade">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" class="clear">
                                                <div>
                                                    <div class="editor-menu">
                                                        <button type="button" id="btn-bold"><i class="fas fa-bold"></i></button>
                                                        <button type="button" id="btn-italic"><i class="fas fa-italic"></i></button>
                                                        <button type="button" id="btn-underline"><i class="fas fa-underline"></i></button>
                                                        <button type="button" id="btn-strike"><i class="fas fa-strikethrough"></i></button>
                                                    </div>
                                                    <div id="editor" contenteditable="true"></div>
                                                    <input type="hidden" name="content" />
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div id="bottom-btn">
                            <div class="fl">
                                <img class="bottom-btn" src="https://img.echosting.cafe24.com/skin/base_ko_KR/board/btn_list.gif"/>
                            </div>
                            <div class="fr">
                                <button><img class="bottom-btn" src="https://img.echosting.cafe24.com/skin/base_ko_KR/board/btn_register.gif"/></button>
                                <img class="bottom-btn" src="https://img.echosting.cafe24.com/skin/base_ko_KR/board/btn_cancel.gif"/>
                            </div>
                            <div style="clear:both;"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="footer/footer.jsp"/>
</body>
</html>
