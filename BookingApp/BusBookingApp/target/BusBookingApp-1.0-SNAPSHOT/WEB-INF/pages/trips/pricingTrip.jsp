<%-- 
    Document   : pricingTrip
    Created on : Aug 25, 2023, 10:00:50 AM
    Author     : zedmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errMsg != null}">
    <div class="text-danger alert text-center">${errMsg}</div>
</c:if>
<c:url value="/admin/setPriceTrip" var="actionPrice"/>
<form action="${actionPrice}" method="post">
    <div class="form-floating mb-3 mt-3" >
        <input type="date" class="form-control dateSelector" id="setOffDay"  placeholder="" name="setOffDay" required="">
        <label for="setOffDay">Ngày bắt đầu:</label>
    </div>
    <div class="form-floating mt-3 mb-3">
        <input type="date" class="form-control dateSelector" id="endTime" placeholder="" name="endTime" required="">
        <label for="endTime">Ngày kết thúc</label>
    </div>
    <div class="form-floating mt-3 mb-3">
        <input type="number" class="form-control" id="cost" placeholder="" name="cost" step="0.01" min="1" required="">
        <label for="cost">Hệ số giá tăng</label>
    </div>
    <button class="btn btn-primary" style="height: 50px; width: 100px; padding: 8px" type="submit" >Apply</button>
</form>

    <script>
            var today = new Date().toISOString().slice(0, 16);
//            var dateInputs = document.querySelectorAll(".dateSelector")
//            dateInputs.forEach(i => {
//                i.min = today;
//            });
            document.getElementById("setOffDay").min = today;
            document.getElementById("endTime").min = today;

    </script>