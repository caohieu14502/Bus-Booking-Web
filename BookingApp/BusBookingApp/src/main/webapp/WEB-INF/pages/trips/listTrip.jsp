<%-- 
    Document   : index
    Created on : Jul 17, 2023, 10:03:51 PM
    Author     : zedmo
--%>
<a href="listTrip.jsp"></a>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="add_btn_position">
    <div>
        <a href="<c:url value="handleTrip" />" class="btn btn-success">Add Trip</a>
    </div>
    <div style="margin-left: 20px">
    <a href="<c:url value="setPriceTrip" />" class="btn btn-success">Holidays Pricing</a>
    </div>
</div>

<table class="table table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Route</th>
            <th>Set Off</th>
            <th>Holiday cost</th>
            <th>Driver name</th>
            <th>Bus plate</th>
            <th style="width: 200px"></th>

        </tr>
    </thead>
    <tbody>
        <c:forEach items="${trip}" var="t">
            <tr>
                <td>${t.id}</td>
                <td>${t.routeId.origin.province} - ${t.routeId.destination.province}</td>
                <td>
                    <fmt:formatDate type = "date" value = "${t.setOffDay}" />
                    <fmt:formatDate type = "time" value = "${t.setOffTime}" />
                </td>
                <td>${t.holidayCost}</td>
                <td>${t.driverId.firstName} ${t.driverId.lastName}</td>
                <td>${t.busId.plate}</td>

                <td>
                    <a href="<c:url value="/admin/handleTrip/${t.id}"/>" class="btn btn-primary">Cập nhật Trips</a>
                    <button class="btn btn-danger" onclick="deleteApi('<c:url value="/api/handleTrip/${t.id}" />')">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

