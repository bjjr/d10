<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="creditCard/chorbi/edit.do" modelAttribute="creditCard" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="creditcard.holder" path="holder" />
	<acme:textbox code="creditcard.brand" path="brand" />
	<acme:textbox code="creditcard.number" path="number"/>
	<acme:textbox code="creditcard.cvv" path="cvv" size="3" />
	
	<spring:message code="creditcard.expirationDate" var="expirationDate" />
	<h4><jstl:out value="${expirationDate}" /></h4>
	
	<acme:textbox code="creditcard.month" path="month" size="2" />
	<acme:textbox code="creditcard.year" path="year" size="4" />
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:cancel url="creditCard/chorbi/display.do" code="misc.cancel"/>
	</div>
</form:form>
