<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<META http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title><fmt:message key="query.title"/></title>
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/screen.css" />" type="text/css" media="screen, projection">
	<link rel="stylesheet" href="<c:url value="/resources/blueprint/print.css" />" type="text/css" media="print">
	<!--[if lt IE 8]>
		<link rel="stylesheet" href="<c:url value="/resources/blueprint/ie.css" />" type="text/css" media="screen, projection">
	<![endif]-->
</head>	
<body>
<div class="container">
	<h2>
		<fmt:message key="query.title"/>
	</h2>
        <h4 style="text-align: right"><a href="."><fmt:message key="menu.torna.principale"/></a></h4>
	<div class="span-24 last">	
            <form:form modelAttribute="interrogazione" action="query" method="post">
                <fieldset style="margin-bottom: 1.0em;">		
                        <legend><fmt:message key="query.form.legend"/></legend>
                        <div>
                            <form:label for="sql" path="sql" cssErrorClass="error"><fmt:message key="query.form.sql.label"/></form:label><br/>
                            <form:textarea cssClass="span-23" path="sql" /> <form:errors path="sql" />
                            <input name="pagina" type="hidden" value="1" />
                        </div>

                        <table>
                            <tr>
                                <td>
                                    <div style="text-align: left">
                                        <fmt:message key="query.form.righeperpagina"/>
                                        <form:select path="rowsPerPage">
                                           <form:options items="${rowsPerPageOptions}" />
                                        </form:select></td>                                                 
                                    </div>                                            
                                </td>
                                <td>
                                    <div style="text-align: right">
                                    <button type="submit" class="button positive">
                                        Invia&nbsp;
                                        <img src="<c:url value="/resources/blueprint/plugins/buttons/icons/tick.png" />" title="Esegue il comando SQL" />
                                    </button>
                                    </div>
                                </td>
                            </tr>
                            <tr> <td colspan="2">
                                <c:if test="${SqlError != null}">
                                    <div class="span-23"><p/>
                                        <p class="error">
                                            <fmt:message key="error.message.intro"/>
                                            <br /><c:out value="${SqlError}" />
                                    </p>
                                    </div>
                                </c:if>        
                                </td>
                            </tr>
                        </table>

                </fieldset>


            </form:form>
	</div>

        
        <fmt:message key="query.result.caption" />&nbsp;
        <c:if test="${righeVisualizzate eq -1}">
            Tutte
        </c:if>
        <c:if test="${righeVisualizzate != -1}">
            <c:out value="${righeVisualizzate}" />
        </c:if>
        di <c:out value="${righeTotali}" /> righe totali
        
        
        <c:set var="tdWidth" value="${100/fn:length(interrogazione.nomiColonne)}" />

        <table>
            <caption>
                <table style="margin-bottom: 0">
                    <tr>
                        <td style="text-align: left; width: 30%"> 
                            <c:if test="${interrogazione.pagina>1}" >
                                <form:form modelAttribute="interrogazione" action="query" method="post">
                                    <input name="sql" type="hidden" value="${interrogazione.sql}" />
                                    <input name="rowsPerPage" type="hidden" value="${righeVisualizzate}" />
                                    <input name="pagina" type="hidden" value="${interrogazione.pagina-1}" />
                                    <button type="submit" class="button positive">Pagina Precedente</button>
                                </form:form>
                            </c:if>
                        </td>

                        <td style="text-align: center">
                            <c:if test="${interrogazione.results != null}">
                            Pagina <c:out value="${paginaCorrente}" /> di <c:out value="${ultimaPagina}" />
                            </c:if>
                        </td>               

                        <td style="text-align: right; width: 30%">
                            <c:if test="${interrogazione.pagina < ultimaPagina}" >
                                <form:form modelAttribute="interrogazione" action="query" method="post">
                                    <input name="sql" type="hidden" value="${interrogazione.sql}" />
                                    <input name="rowsPerPage" type="hidden" value="${righeVisualizzate}" />
                                    <input name="pagina" type="hidden" value="${interrogazione.pagina+1}" />
                                    <button type="submit" class="button positive">Pagina Successiva</button>
                                </form:form>
                            </c:if>                    
                        </td>
                    </tr>
                </table>                
            </caption>

            <thead>
                <c:forEach var="nomeColonna" items="${interrogazione.nomiColonne}" >
                <th style="padding: 10px 10px 10px 5px;"><c:out value="${nomeColonna}"/></th>                    
                </c:forEach>
            </thead>

            <tbody>
                
                <c:forEach var="riga" items="${interrogazione.results}" >
                    <tr>
                    <c:forEach var="colonna" items="${riga.celle}" >
                        <td style="width: ${tdWidth}%">
                        <c:out value="${colonna.value}"/>
                        </td>
                    </c:forEach>
                    
                    </tr>
                </c:forEach>        
                
            </tbody>
        </table>        
        <hr>
        <h4 style="text-align: right"><a href="."><fmt:message key="menu.torna.principale"/></a></h4>    
</div>
            
</body>
</html>