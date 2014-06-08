<%-- 
    Document   : sqlReportList
    Created on : 9-lug-2013, 12.26.20
    Author     : jolab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><fmt:message key="sqlReportList.title"/></title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
</head>	
    <body>

<div class="container">
	<h1>
		<fmt:message key="sqlReportList.intestazione"/>
	</h1>
                <h4 style="text-align: right"><a href="<c:url value="/" />"><fmt:message key="menu.torna.principale"/></a></h4>
	<div class="span-24 last">	        
            <table>
                <caption><fmt:message key="sqlReportList.result.caption" /></caption>

                <thead>
                    <th>id</th>
                    <th>Nome</th>
                    <th>Data inserimento</th>
                    <th>Commento</th>
                    <th></th>
                </thead>

                <tbody>

                    <c:forEach var="riga" items="${listaSqlReport}" >
                        <tr>
                            <td><c:out value="${riga.idSqlReport}"/></td>
                            <td><c:out value="${riga.name}"/></td>
                            <td><fmt:formatDate value="${riga.insDate}" pattern="dd/MM/yyyy"/></td>
                            <td title="<c:out value="${riga.sql}"/>"><pre><c:out value="${riga.comment}"/></pre></td>
                            <td><a href="<c:url value="sqlReport/${riga.idSqlReport}"/>"/>visualizza</td>
                        </tr>
                    </c:forEach>        

                </tbody>
            </table>        
            <hr>
            <h4 style="text-align: right"><a href="<c:url value="/" />"><fmt:message key="menu.torna.principale"/></a></h4>    
            
            
        </div>        
        
    </body>
</html>
