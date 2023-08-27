<%-- 
    Document   : index
    Created on : Jul 17, 2023, 10:03:51 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--BODY-->
<div class="add_btn_position">
    <div>
        <a href="<c:url value="/admin/handleRoute" />" class="btn btn-success">Add Route</a>
    </div>
</div>

<table class="table table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Origin</th>
            <th>Destination</th>
            <th>Estimate Time</th>
            <th>Basic Price</th>
            <th style="width: 200px"></th>
        </tr>
    </thead>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <tbody>
        <c:forEach items="${route}" var="r">
            <tr>
                <td>${r.id}</td>
                <td>${r.origin.province}</td>
                <td>${r.destination.province}</td>
                <td>${r.durationDays} days and ${r.durationTime}</td>
                <td>
                    <fmt:formatNumber value="${r.basicPrice}" maxFractionDigits="0"/> VNĐ
                </td>

                <td>
                    <c:url value="/admin/handleRoute/${r.id}" var="deleteApi" />
                    <a href="<c:url value="/admin/handleRoute/${r.id}"/>" class="btn btn-primary">Cập nhật</a>
                    <button class="btn btn-danger" onclick="deleteApi('<c:url value="/api/handleRoute/${r.id}"/>')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

