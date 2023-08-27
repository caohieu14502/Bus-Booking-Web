<%-- 
    Document   : pagination
    Created on : Aug 10, 2023, 1:03:02 PM
    Author     : zedmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${counter > 1}">
    <ul class="pagination">
        <c:if test="${pageActive == null}">
            <c:if test="${trip != null}">
                <li class="page-item active"><a class="page-link" href="<c:url value="/admin/listTrip" />">Tất cả</a></li>
                </c:if>
                <c:if test="${station != null}">    
                <li class="page-item active"><a class="page-link" href="<c:url value="/admin/listStation" />">Tất cả</a></li>
                </c:if>
                <c:if test="${route != null}">
                <li class="page-item active"><a class="page-link" href="<c:url value="/admin/listRoute" />">Tất cả</a></li>
            </c:if>
        </c:if>
        <c:if test="${pageActive != null}">
        <c:if test="${trip != null}">
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/listTrip" />">Tất cả</a></li>
                </c:if>
                <c:if test="${station != null}">    
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/listStation" />">Tất cả</a></li>
                </c:if>
                <c:if test="${route != null}">
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/listRoute" />">Tất cả</a></li>
            </c:if>
        </c:if>
            
        <c:forEach begin="1" end="${counter}" var="i">
            <c:url value="${action}" scope="request" var="pageAction">
                <c:param name="page" value="${i}"/>
            </c:url>
            <c:if test="${pageActive == i}">
                <li class="page-item active"><a class="page-link" href="${pageAction}">${i}</a></li>
            </c:if>
            <c:if test="${pageActive != i}">
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
            </c:if>
        </c:forEach>
    </ul>
</c:if>


