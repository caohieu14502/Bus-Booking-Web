<%-- 
    Document   : handleBus
    Created on : Aug 15, 2023, 6:27:55 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Xe đi</h1>
<c:url value="/admin/handleBus" var="handleAction"/>
<form:form modelAttribute="bus" method="post" action="${handleAction}">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="plate" id="plate" placeholder="" name="plate" maxlength="11"/>
        <label for="plate">Plate</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>    
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="numberOfSeats" id="numberOfSeats" placeholder="" name="numberOfSeats" min="2"/>
        <label for="numberOfSeats">Number of Seats</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="bus" name="bus" path="busTypeId">
            <c:forEach items="${busTypes}" var="bt">
                <c:choose>
                    <c:when test="${bus.busTypeId.id == bt.id}">
                        <option value="${bt.id}" selected>${bt.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${bt.id}">${bt.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="route" class="form-label">Chọn loại xe:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${bus.id != null}">
                    Cập nhật Xe
                </c:when>
                <c:otherwise>
                    Thêm Xe
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

