<%-- 
    Document   : handleBusType
    Created on : Aug 22, 2023, 5:35:22 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Loại Xe</h1>
<c:url value="/admin/handleBusType" var="handleAction"/>
<form:form modelAttribute="busType" method="post" action="${handleAction}">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="" name="name"/>
        <label for="name">Name</label>
    </div>    
    <div class="form-floating mb-3 mt-3">
        <form:input type="number"  step="0.01" class="form-control" path="typeCost" id="typeCost" placeholder="" name="typeCost" min="1"/>
        <label for="typeCost">Type Cost</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${busType.id != null}">
                    Cập nhật Loại Xe
                </c:when>
                <c:otherwise>
                    Thêm Loại Xe
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

