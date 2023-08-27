<%-- 
    Document   : users
    Created on : Aug 6, 2023, 10:23:41 AM
    Author     : zedmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="add_btn_position">
    <div>
        <a href="<c:url value="handleUser" />" class="btn btn-success">Add User</a>
    </div>
</div>
<table class="table table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th></th>
            <th>Name</th>
            <th>Email </th>
            <th style="width: 200px">Phone Number</th>
            <th>Role</th>
            <th ></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="u">
            
            <c:if test="${u.active}">
                <tr>
            </c:if>
            <c:if test="${!u.active}">
                <tr class="table-light text-secondary">
            </c:if>
            
                <td>${u.id}</td>
                <td>
                    <img src="${u.avatar}" alt="${u.lastName}" width="32px"  />
                </td>
                <td>${u.firstName} ${u.lastName}</td>
                <td>${u.email}</td>
                <td>${u.phoneNumber}</td>
                <td>${u.roleId.roleName}</td>

                <td>
                    <c:url value="/api/handleUser/${u.id}" var="actionHandle"/>
                    <a href="<c:url value="/admin/handleUser/${u.id}"/>" class="btn btn-primary">Cập nhật</a>
                    <c:if test="${u.active}">
                        <button class="btn btn-warning" style="color:white" onclick="lockOrUnlockUser('${actionHandle}')">Khóa</button>
                    </c:if>
                    <c:if test="${!u.active}">
                        <button class="btn btn-warning" style="color:white" onclick="lockOrUnlockUser('${actionHandle}')">Bỏ Khóa</button>
                    </c:if>
                    <button class="btn btn-danger" onclick="deleteApi('${actionHandle}')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>



