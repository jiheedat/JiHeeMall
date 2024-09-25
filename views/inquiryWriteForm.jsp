<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/resources/css/inquiryWriteForm.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/footer.css" rel ="stylesheet">

    <script>
        $(function(){
            $("#btn-bold").click(function(){
                document.execCommand('bold'); 
                $("#editor").focus(); 
            });

            $("#btn-italic").click(function(){
                document.execCommand('italic'); 
                $("#editor").focus(); 
            });
            
            $("#btn-underline").click(function(){
                document.execCommand('underline'); 
                $("#editor").focus(); 
            });
            
            $("#btn-strike").click(function(){
                document.execCommand('strikethrough'); 
                $("#editor").focus(); 
            });
            
            $("#editor").on('input', function(){
                console.log($(this).html());
            });
        });
    </script>
</head>
<body>
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
                                    <strong>상품 문의</strong>
                                </li>
                            </ol>
                            <div style="clear:both;"></div>
                        </div>
                        <div class="title">
                            <h2>
                                <font color="333333">상품 문의</font>
                            </h2>
                            <p>상품 문의 게시판입니다.</p>
                        </div>
                    </div>
                    <form id="boardWriteForm" action="" method="post" enctype="multipart/form-data">
                        <div class="board-write">
                            <div class="typeWrite">
                                <table class="line" border="1">
                                    <colgroup>
                                        <col style="width: 130px;">
                                        <col style="width: auto;">
                                    </colgroup>
                                    <tbody>
                                        <tr>
                                            <th scope="row">제목</th>
                                            <td>
                                                <select id="subject" name="subject" rw-filter="isFill" fw-label="제목">
                                                    <option value="배송문의">배송문의</option>
                                                    <option value="상품문의">상품문의</option>
                                                </select>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">작성자</th>
                                            <td>
                                                <input id="writer" name="writer" fw-filter="isFill" fw-label="작성자" fw-msg="" class="inputTypeText" placeholder="" maxlength="50" value="" type="text">
                                            </td>
                                        </tr>
                                        <tr>
                                            <th scope="row">이메일</th>
                                            <td>
                                                <input id="email1" name="email1" fw-filter="" fw-label="이메일" fw-alone="N" fw-msg="" class="mailId" value="" type="text"/>
                                                @
                                                <input id="email2" name="email2" fw-filter="" fw-label="이메일" fw-alone="N" fw-msg="" class="mailAddress" value="" type="text"/>
                                                <select id="email3" fw-filter="" fw-label="이메일" fw-alone="N" fw-msg="">
                                                    <option value="" selected="selected">- 이메일 선택 -</option>
                                                    <option value="naver.com">naver.com</option>
                                                    <option value="daum.net">daum.net</option>
                                                    <option value="nate.com">nate.com</option>
                                                    <option value="hotmail.com">hotmail.com</option>
                                                    <option value="yahoo.com">yahoo.com</option>
                                                    <option value="empas.com">empas.com</option>
                                                    <option value="korea.com">korea.com</option>
                                                    <option value="dreamwiz.com">dreamwiz.com</option>
                                                    <option value="gmail.com">gmail.com</option>
                                                    <option value="etc">직접입력</option>
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
                        		<img class="bottom-btn" src="https://img.echosting.cafe24.com/skin/base_ko_KR/board/btn_register.gif"/>
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
