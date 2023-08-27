<%-- 
    Document   : handleUser
    Created on : Aug 22, 2023, 11:12:12 AM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý User</h1>
<c:url value="/admin/handleUser" var="handleAction"/>
<form:form modelAttribute="user" method="post" action="${handleAction}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="firstName" id="firstName" placeholder="Nhập tên tỉnh thành" name="firstName" />
        <label for="firstName">First Name</label>
        <form:errors path="firstName" element="div" cssClass="text-danger"/>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="lastName" id="lastName" placeholder="Nhập tên tỉnh thành" name="lastName" />
        <label for="lastName">Last Name</label>
        <form:errors path="lastName" element="div" cssClass="text-danger"/>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="phoneNumber" id="phoneNumber" placeholder="Nhập tên tỉnh thàn" name="phoneNumber" maxlength="10"/>
        <label for="phoneNumber">Phone Number</label>
        <form:errors path="phoneNumber" element="div" cssClass="text-danger"/>
        <div id="phone" class="text-danger"></div>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="email" id="email" placeholder="Nhập tên tỉnh thàn" name="email"/>
        <label for="email">Email</label>
        <form:errors path="email" element="div" cssClass="text-danger"/>
            <c:if test="${errMsg != null}">
            <div id="pswd" class="text-danger">${errMsg}</div>
        </c:if>
    </div> 
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="address" id="address" placeholder="Nhập tên tỉnh thàn" name="address"/>
        <label for="address">Address</label>
        <form:errors path="address" element="div" cssClass="text-danger"/>
    </div> 
    <c:if test="${user.id == null}">
        <div class="form-floating mb-3 mt-3">
            <form:input type="password" class="form-control" path="password" id="password" placeholder="Nhập tên tỉnh thàn" name="password"/>
            <label for="password">Password</label>
            <div id="pswd" class="text-danger"></div>
        </div> 
        <div class="form-floating mb-3 mt-3">
            <input type="password" class="form-control" id="confirmPassword" placeholder="Nhập tên tỉnh thàn" path="confirmPassword" name="confirmPassword"/>
            <label for="confirmPassword">Confirm Password</label>
            <div id="confirmPswd" class="text-danger"></div>
        </div> 
    </c:if> 
    <c:if test="${user.id != null}">
        <form:hidden path="password"/>
        <form:hidden path="id"/>
        <form:hidden path="active"/>
    </c:if> 
    <c:if test="${user.roleId.id == 1}">
        <form:hidden path="roleId"/>
    </c:if> 
    <c:if test="${user.roleId.id != 1}">
        <div class="form-floating mb-3 mt-3">
            <form:select class="form-select" id="role" name="role" path="roleId">
                <c:forEach items="${roles}" var="r">
                    <c:choose>
                        <c:when test="${user.roleId.id == r.id}">
                            <option value="${r.id}" selected>${r.roleName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${r.id}">${r.roleName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </form:select>
            <label for="role" class="form-label">Chọn Role:</label>
        </div>
    </c:if> 

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="chon hinh" name="file" />
        <label for="file">Ảnh đại diện</label>
        <div id="fileInput" class="text-danger"></div>
    </div>
    <c:if test="${user.avatar != ''}">
        <form:hidden path="avatar"/>
        <div>
            <img src="${user.avatar}" alt="${user.avatar}" width="80px" />
        </div>
    </c:if>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${user.id != null}">
                    Cập nhật User
                </c:when>
                <c:otherwise>
                    Thêm User
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>


    <script>
        const submiBtn = document.querySelector("button");
        function verifyPassword(event) {
        <c:if test="${user.id == null}">
            var pw = document.getElementById("password").value;
            const patternEmail = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
            if ( !patternEmail.test(pw) || pw.length < 8) {
                document.getElementById("pswd").textContent = "Mật khẩu ít nhất 8 kí tự, 1 kí tự hoa, 1 kí tự thường, 1 kí tự số và 1 kí tự đặc biệt";
                event.preventDefault();
            } else {
                document.getElementById("pswd").textContent = "";
            }
            var cpw = document.getElementById("confirmPassword").value;
            if (cpw !== pw) {
                document.getElementById("confirmPswd").textContent = "Mật khẩu không khớp";
                event.preventDefault();
            } else {
                document.getElementById("confirmPswd").textContent = "";
            }
        </c:if>
        <c:if test="${user.avatar == null}">
            let output = document.getElementById("file");
            if (output.value === "") {
                document.getElementById("fileInput").textContent = "Vui lòng nhập ảnh đại diện";
                event.preventDefault();
            } else {
                document.getElementById("fileInput").textContent = "";
            }
        </c:if>
        }
        submiBtn.onclick = verifyPassword;
    </script>