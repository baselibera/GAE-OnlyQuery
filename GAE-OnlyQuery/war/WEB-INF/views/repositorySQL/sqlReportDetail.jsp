<%-- 
    Document   : sqlReportDetail
    Created on : 9-lug-2013, 14.23.44
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
                <fmt:message key="sqlReportDetail.intestazione"/>
        </h1>
        <h4 style="text-align: right"><a href="<c:url value="/sqlReport" />"><fmt:message key="menu.torna.listareport"/></a></h4>
        <div class="span-24 last">
            <form:form modelAttribute="sqlReport" action="${sqlReport.idSqlReport}" method="post">
            <fieldset>
                <legend>
                    <fmt:message key="sqlReportDetail.legend" />&nbsp;'<c:out value="${sqlReport.name}"/>'
                </legend>

                <p>
                    <form:label	for="idSqlReport" path="name"><fmt:message key="sqlReportDetail.label.id"/></form:label>
                    <form:input path="idSqlReport" readonly="true"/>
                </p>                     
                <p>
                    <form:label	for="name" path="name"><fmt:message key="sqlReportDetail.label.nome"/></form:label>
                    <form:input path="name" readonly="true"/>	
                </p>                     
                <p>
                    <form:label	for="insDate" path="insDate"><fmt:message key="sqlReportDetail.label.data"/></form:label><br/>
                    <form:input path="insDate" readonly="true"/>	
                </p>                     
                <p>
                    <form:label	for="sql" path="sql"><fmt:message key="sqlReportDetail.label.sql"/></form:label><br/>
                    <form:textarea cssClass="span-23" path="sql" readonly="true"/>	
                </p>                     
                <p>
                    <form:label	for="comment" path="comment"><fmt:message key="sqlReportDetail.label.commento"/></form:label><br/>
                    <form:textarea cssClass="span-23" path="comment" readonly="true"/>	
                </p>                 
            </fieldset>
            </form:form>
        </div>
    </body>
</html>
