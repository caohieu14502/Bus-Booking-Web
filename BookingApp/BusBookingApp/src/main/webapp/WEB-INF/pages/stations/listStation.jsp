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
        <a href="<c:url value="handleStation" />" class="btn btn-success">Add Station</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
        <tr>
            <th style="width: 150px"></th>
            <th>Id</th>
            <th>Province</th>
            <th>Location </th>
            <th style="width: 200px"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${station}" var="s">
            <tr>
                <td>
                    <img src="${s.picture}" alt="${s.province}" width="80px"  />
                </td>
                <td>${s.id}</td>
                <td>${s.province}</td>
                <td>${s.location}</td>

                <td>
                    <a href="<c:url value="handleStation/${s.id}"/>" class="btn btn-primary">Cập nhật</a>
                    <button class="btn btn-danger" onclick="deleteApi('<c:url value="/api/handleStation/${s.id}"/>')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>


