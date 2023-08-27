<%-- 
    Document   : base
    Created on : Aug 5, 2023, 7:15:39 PM
    Author     : zedmo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title"/>
        </title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="<c:url value="/css/style.css"/>" />
    </head>
    <body>
        <div class="self_header_container">
            <tiles:insertAttribute name="header"/>
        </div>
        <div style="display: flex; justify-content: center;">
            <div class="headingName" >
                <h1>
                    <tiles:insertAttribute name="headingName"/>
                </h1>
            </div>
        </div>
        <c:if test="${trip != null}">
            <c:url value="/admin/listTrip" var="action" />
        </c:if>
        <c:if test="${station != null}">
            <c:url value="/admin/listStation" var="action" />
        </c:if>
        <c:if test="${route != null}">
            <c:url value="/admin/listRoute" var="action" />
        </c:if>
        <div class="container self_body">
            <div style="width: 90%; margin: 0 auto">
                <tiles:insertAttribute name="searchBox"/>
                <div style="position: relative; top: 77px; width: 50%;">
                    <tiles:insertAttribute name="pagination"/>
                </div>
                <tiles:insertAttribute name="content"/>
            </div>
        </div>
        <tiles:insertAttribute name="footer"/>
    <script src="<c:url value="/js/main.js"/>"></script>
    </body>
</html>
