<%-- 
    Document   : login
    Created on : Aug 12, 2023, 7:29:46 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${param.accessDenied != null}">
    <div class="alert text-center text-danger">
        Bạn không có quyền truy cập vào đây!
    </div>
</c:if>
<div class="self_login_page container">
    <div class="login_picture_container">
        <img src="<c:url value="/img/login_picture.jpg"/>" alt="nothing" />
        <div class="login_form_container">
            <c:url value="/login" var="loginAction"/>
            <form method="post" action="${loginAction}">
                <div class="wave-group">
                    <input required="" type="text" class="input" name="mail">
                    <span class="bar"></span>
                    <label class="label">
                        <span class="label-char" style="--index: 0">M</span>
                        <span class="label-char" style="--index: 1">a</span>
                        <span class="label-char" style="--index: 2">i</span>
                        <span class="label-char" style="--index: 3">l</span>
                    </label>
                </div>
                <div class="wave-group">
                    <input required="" type="password" class="input" name="password">
                    <span class="bar"></span>
                    <label class="label">
                        <span class="label-char" style="--index: 0">P</span>
                        <span class="label-char" style="--index: 1">a</span>
                        <span class="label-char" style="--index: 2">s</span>
                        <span class="label-char" style="--index: 3">s</span>
                    </label>
                </div>
                <button class="button-64" role="button"><span class="text">Đăng nhập</span></button>
                <a class="pass_forget">Quên mật khẩu?</a>
            </form>
        </div>
    </div> 
</div>