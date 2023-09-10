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
        <a href="<c:url value="handleBus" />" class="btn btn-success">Add Bus</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Plate</th>
            <th>Number of Seats </th>
            <th>Type</th>
            <th style="width: 300px"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${bus}" var="b">
            <tr>
                <td>${b.id}</td>
                <td>${b.plate}</td>
                <td>${b.numberOfSeats}</td>
                <td>${b.busTypeId.name}</td>
                <td>
                    
                    <a href="<c:url value="handleBus/${b.id}" />" class="btn btn-primary">Cập nhật</a>
                    <a href="<c:url value="handleSeats/${b.id}" />" class="btn btn-primary">Seats</a>
                    <button class="btn btn-danger" onclick="deleteApi('<c:url value="/api/handleBus/${b.id}" />')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


