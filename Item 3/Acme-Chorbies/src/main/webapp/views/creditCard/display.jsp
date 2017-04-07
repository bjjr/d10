<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<jstl:if test="${creditCard == null}">
	<spring:message code="creditcard.warning" var="warning" />
	<jstl:out value="${warning}" />
	<acme:link code="creditcard.register" href="creditCard/chorbi/create.do" />
</jstl:if>

<jstl:if test="${creditCard != null}" >
	<acme:display code="creditcard.holder" property="${creditCard.holder}"/>
	<acme:display code="creditcard.brand" property="${creditCard.brand}"/>
	<acme:display code="creditcard.number" property="${maskedNumber}"/>
	<acme:display code="creditcard.expirationDate" property="${creditCard.expirationDate}"/>
	<acme:display code="creditcard.cvv" property="${creditCard.cvv}"/>

	<acme:link code="creditcard.edit" href="creditCard/chorbi/edit.do?creditCardId=${creditCard.id}" />
</jstl:if>

