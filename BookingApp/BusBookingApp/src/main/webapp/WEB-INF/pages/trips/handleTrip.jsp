<%-- 
    Document   : handleTrip
    Created on : Aug 15, 2023, 12:50:55 PM
    Author     : zedmo
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info mt-1">Quản lý Chuyến đi</h1>
<c:url value="/admin/handleTrip" var="handleAction"/>
<c:if test="${existErr != null}">
    <div class="text-center text-danger alert alert-danger">${existErr}</div>
</c:if>
<form:form modelAttribute="trip" method="post" action="${handleAction}">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="id" />
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="route" name="route" path="routeId">
            <c:forEach items="${routes}" var="r">
                <c:choose>
                    <c:when test="${trip.routeId.id == r.id}">
                        <option value="${r.id}" selected>${r.origin.province} to ${r.destination.province}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${r.id}">${r.origin.province} to ${r.destination.province}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="route" class="form-label">Chọn tuyến đi:</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="setOffDayString" id="setOffDayString" placeholder="Nhập hệ số giá" name="setOffDayString" min=""/>
        <label for="setOffDayString">Set Off Day</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="time" class="form-control" path="setOffTimeString" id="setOffTimeString" placeholder="Nhập hệ số giá" name="setOffTimeString" min=""/>
        <label for="setOffTimeString">Set Off Time</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="holidayCost" id="holidayCost" placeholder="Nhập hệ số giá" name="holidayCost" min="1" step="0.01"/>
        <label for="holidayCost">Holiday Cost</label>
        <%--<form:errors path="name" element="div" cssClass="text-danger"/>--%>
    </div>    
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="bus" name="bus" path="busId">
            <c:forEach items="${buses}" var="b">
                <c:choose>
                    <c:when test="${trip.busId.id == b.id}">
                        <option value="${b.id}" selected>${b.plate}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${b.id}">${b.plate}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="route" class="form-label">Chọn xe:</label>
    </div>
    
    <div class="form-floating mb-3 mt-3">
        <form:select class="form-select" id="driver" name="driver" path="driverId">
            <c:forEach items="${userss}" var="s">
                <c:choose>
                    <c:when test="${trip.driverId.id == s.id}">
                        <option value="${s.id}" selected>${s.firstName} ${s.lastName}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${s.id}">${s.firstName} ${s.lastName}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="route" class="form-label">Chọn Tài xế:</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-success" type="submit">
            <c:choose>
                <c:when test="${trip.id != null}">
                    Cập nhật Chuyến đi
                </c:when>
                <c:otherwise>
                    Thêm Chuyến đi
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>

<script>
    var today = new Date().toISOString().slice(0, 16);
    document.getElementById("setOffDayString").min = today;
    if('${trip.id}' === null || '${trip.setOffDay}' === "") {
        document.getElementById("setOffDayString").value = today;
    } else {
        document.getElementById("setOffDayString").value = '${trip.setOffDay}';
    }
    
    if(${trip.id} === null || '${trip.setOffTime}' === "") {
        document.getElementById("setOffTimeString").value = today;
    } else {
        document.getElementById("setOffTimeString").value = '${trip.setOffTime}';
    }

</script>