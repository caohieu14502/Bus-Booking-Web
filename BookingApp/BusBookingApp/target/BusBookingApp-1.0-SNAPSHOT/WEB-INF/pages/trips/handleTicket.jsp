<%-- 
    Document   : handleTickets
    Created on : Sep 1, 2023, 10:21:09 PM
    Author     : zedmo
--%>
<!--QUAN LY 1 TICKET THOI - NHAP MA VE HOAC MA CHUYEN DI DE CHINH SUA-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Chỗ Ngồi</h1>
<c:url value="/admin/handleSeats" var="handleAction"/>
<form  style="width: 300px; margin: 0 auto" action="${handleAction}" method="post">
    <input type="hidden" name="busId" value="${busId}">
    <div class="container"> 
        <div class="row0 row">
            <div class="col">Driver</div>
            <div class="col">0-2</div>
            <div class="col"></div>
            <div class="col"></div>

        </div>
        <c:forEach begin="1" end="8" step="1" var="row">
            <div class="row0 row">
                <c:forEach begin="1" end="4" step="1" var="col">
                    <div class="col">${row}-${col}
                        <input name="selectedValue" type="checkbox" value="${row}${col}">
                    </div>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
    <button type="submit">On board</button>
</form>
