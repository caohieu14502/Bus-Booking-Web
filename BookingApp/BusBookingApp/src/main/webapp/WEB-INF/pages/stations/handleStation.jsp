<%-- 
    Document   : handleStation
    Created on : Aug 15, 2023, 3:47:44 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Chuyến đi</h1>
<c:url value="/admin/handleStation" var="handleAction"/>
<form:form modelAttribute="station" method="post" action="${handleAction}" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="province" id="province" required="required" placeholder="Nhập tên tỉnh thành" name="province"/>
        <label for="province">Province</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="location" id="location" placeholder="Nhập địa chỉ" name="location" />
        <label for="location">Location</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" placeholder="Nhập tên tỉnh thành" name="file" />
        <label for="file">Ảnh đại diện</label>
    </div>
    <c:if test="${station.picture != ''}">
        <form:hidden path="picture"/>
        <div>
            <img src="${station.picture}" alt="${station.province}" width="80px" />
        </div>
    </c:if>
    <div class="form-floating mb-3 mt-3">
        
        <button class="btn btn-success" type="submit">
            <c:choose>
                    <c:when test="${station.id != null}">
                        Cập nhật Tuyến đi
                    </c:when>
                    <c:otherwise>
                        Thêm Tuyến đi
                    </c:otherwise>
                </c:choose>
        </button>
    </div>
</form:form>
