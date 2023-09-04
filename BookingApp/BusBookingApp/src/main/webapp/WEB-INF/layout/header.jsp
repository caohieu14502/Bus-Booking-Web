<%-- 
    Document   : header
    Created on : Aug 5, 2023, 7:15:53 PM
    Author     : zedmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 

<div class="container self_header">
    <div class="row">
        <sec:authorize access="hasRole('ROLE_Admin')">
        <div class="col-sm-10">
            <ul class="nav nav-tabs nav-justified" style="height: 100%">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/dashboard"/>">Dashboard</a>
                </li>
                <li class="nav-item">
                    <c:if test="${trip != null}">
                        <a class="nav-link active" href="<c:url value="/admin/listTrip"/>">Trips</a>
                    </c:if>
                    <c:if test="${trip == null}">
                        <a class="nav-link" href="<c:url value="/admin/listTrip" />">Trips</a>
                    </c:if>
                </li>
                                <li class="nav-item dropdown ">
                    <c:if test="${route != null || station != null}">
                        <a class="nav-link dropdown-toggle active" data-bs-toggle="dropdown" href="#">Route</a>
                        <ul class="dropdown-menu">
                            <li>
                                <c:if test="${route != null}">
                                    <a class="dropdown-item active" href="<c:url value="/admin/listRoute" />">Routes</a>
                                </c:if>
                                <c:if test="${route == null}">
                                    <a class="dropdown-item" href="<c:url value="/admin/listRoute" />">Routes</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${station != null}">
                                    <a class="dropdown-item active" href="<c:url value="/admin/listStation" />">Stations</a>
                                </c:if>
                                <c:if test="${station == null}">
                                    <a class="dropdown-item" href="<c:url value="/admin/listStation" />">Stations</a>
                                </c:if>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${route == null and station == null}">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Route</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<c:url value="/admin/listRoute" />">Routes</a></li>
                            <li><a class="dropdown-item" href="<c:url value="/admin/listStation" />">Stations</a></li>
                        </ul>
                    </c:if>
                </li>
<!--                <li class="nav-item">
                    <c:if test="${station != null}">
                        <a class="nav-link active" href="<c:url value="/admin/listStation" />">Stations</a>
                    </c:if>
                    <c:if test="${station == null}">
                        <a class="nav-link" href="<c:url value="/admin/listStation"/>">Stations</a>
                    </c:if>
                </li>-->
                <li class="nav-item dropdown ">
                    <c:if test="${bus != null || busType != null}">
                        <a class="nav-link dropdown-toggle active" data-bs-toggle="dropdown" href="#">Bus</a>
                        <ul class="dropdown-menu">
                            <li>
                                <c:if test="${bus != null}">
                                    <a class="dropdown-item active" href="<c:url value="/admin/listBus" />">List Bus</a>
                                </c:if>
                                <c:if test="${bus == null}">
                                    <a class="dropdown-item" href="<c:url value="/admin/listBus" />">List Bus</a>
                                </c:if>
                            </li>
                            <li>
                                <c:if test="${busType != null}">
                                    <a class="dropdown-item active" href="<c:url value="/admin/listBusType" />">Bus Type</a>
                                </c:if>
                                <c:if test="${busType == null}">
                                    <a class="dropdown-item" href="<c:url value="/admin/listBusType" />">Bus Type</a>
                                </c:if>
                            </li>
                        </ul>
                    </c:if>
                    <c:if test="${bus == null and busType == null}">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#">Bus</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="<c:url value="/admin/listBus" />">List Bus</a></li>
                            <li><a class="dropdown-item" href="<c:url value="/admin/listBusType" />">Bus Type</a></li>
                        </ul>
                    </c:if>
                </li>

                <li class="nav-item">
                    <c:if test="${user != null}">
                        <a class="nav-link active" href="<c:url value="/admin/listUser"/>">Users</a>
                    </c:if>
                    <c:if test="${user == null}">
                        <a class="nav-link" href="<c:url value="/admin/listUser" />">Users</a>
                    </c:if>
                </li>
            </ul>
        </div>
        </sec:authorize>

        <div class="col-sm-2" style="display: flex;justify-content: end;">
            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <div class="dropdown ">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown">
                            ${pageContext.request.userPrincipal.name}
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Link 1</a></li>
                            <li><a class="dropdown-item" href="#">Link 2</a></li>
                            <li><a class="dropdown-item" href="#">Link 3</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="<c:url value="/logout" />">Log out</a></li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <div style="hight:100%; display: flex; align-items:center;">
                        <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>