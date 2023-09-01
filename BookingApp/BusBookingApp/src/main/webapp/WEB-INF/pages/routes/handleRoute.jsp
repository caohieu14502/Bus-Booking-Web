<%-- 
    Document   : routes
    Created on : Aug 5, 2023, 7:39:31 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Tuyến đi</h1>
<c:url value="/admin/handleRoute" var="handleAction"/>
<form:form modelAttribute="route" method="post" action="${handleAction}">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="Nhập tên" name="name" />
        <label for="name">Tên</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="origin" name="origin" path="origin">
            <c:forEach items="${places}" var="p">
                <c:choose>
                    <c:when test="${route.origin.id == p.id}">
                        <option value="${p.id}" selected>${p.province}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${p.id}">${p.province}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="origin" class="form-label">Chọn nơi đi:</label>
        <div class="text-danger des_ori_same"></div>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="destination" name="destination" path="destination">
            <c:forEach items="${places}" var="p">
                <c:choose>
                    <c:when test="${route.destination.id == p.id}">
                        <option value="${p.id}" selected>${p.province}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${p.id}">${p.province}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="destination" class="form-label">Chọn nơi đến:</label>
        <div class="text-danger des_ori_same"></div>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="basicPrice" min="0" id="basicPrice" placeholder="Nhập giá" name="basicPrice" />
        <label for="name">Basic Price</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>    
    <!-- https://stackoverflow.com/questions/34803874/spring-mvc-http-status-400-bad-request -->
    <div class="form-floating mt-3">
        <form:input type="number" class="form-control" path="durationDays" id="hour" placeholder="Nhập thời gian" name="hour" min="0"/>
        <label for="hour">Số ngày chạy ước tính(Nếu > 1 Ngày)</label>
    </div>
    <div class="form-floating mb-3">
        <form:input type="text" class="form-control" path="durationTime" id="time" placeholder="Nhập thời gian" pattern=""  name="time" />
        <label for="time">Thời gian chạy ước tính(hh:mm:ss)</label>
        <div class="text-danger" id="timeErr"></div>

    </div>

    <div class="form-floating mb-3 mt-3">

        <button class="btn btn-success" id="btn-Route" type="submit">
            <c:choose>
                <c:when test="${route.id != null}">
                    Cập nhật Tuyến đi
                </c:when>
                <c:otherwise>
                    Thêm Tuyến đi
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
    
<script>
    const submiBtn = document.getElementById("btn-Route");

    function submitValidateRoute(event) {
        var des = document.getElementById("destination").value;
        var ori = document.getElementById("origin").value;
        if(ori === des) {
            event.preventDefault();
            document.querySelectorAll(".des_ori_same").forEach(c =>{
                c.textContent = "Nơi đi và Nơi đến không được trùng nhau!!!";
            });
        } else {
            document.querySelectorAll(".des_ori_same").forEach(c =>{
                c.textContent = "";
            });
        }
        var time= document.getElementById("time").value;
        const patternTime = /^(?:(?:([01]?\d|2[0-3]):)?([0-5]?\d):)?([0-5]?\d)$/;
        if ( !patternTime.test(time)) {
            document.getElementById("timeErr").textContent = "Vui lòng nhập theo định dạng hh:mm:ss";
            event.preventDefault();
        } else {
            document.getElementById("timeErr").textContent = "";
        }
    }
    submiBtn.onclick = submitValidateRoute;
</script>