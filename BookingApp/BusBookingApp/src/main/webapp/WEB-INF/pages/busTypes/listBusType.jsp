<%-- 
    Document   : listBusType
    Created on : Aug 22, 2023, 5:35:10 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--BODY-->
<div class="add_btn_position">
    <div>
        <a href="<c:url value="handleBusType" />" class="btn btn-success">Add Bus Type</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Type Cost</th>
            <th style="width: 200px"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${busType}" var="bt">
            <tr>
                <td>${bt.id}</td>
                <td>${bt.name}</td>
                <td>${bt.typeCost}</td>
                <td>
                    <a href="<c:url value="handleBusType/${b.id}" />" class="btn btn-primary">Cập nhật</a>
                    <button class="btn btn-danger" onclick="deleteApi('<c:url value="/api/handleBusType/${bt.id}" />')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
