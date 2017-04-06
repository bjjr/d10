<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<img src="${chorbi.picture}" style="max-height: 50%; max-width: 50;" />
<br/>

<acme:display code="actor.name" property="${chorbi.name}" />
<br/>

<acme:display code="actor.surname" property="${chorbi.surname}" />
<br/>

<acme:display code="chorbi.description" property="${maskedDesc}" />
<br/>

<acme:display code="chorbi.birthdate" property="${chorbi.birthdate}" /><h4>(<jstl:out value="${age}" />)</h4>
<br/>

<jstl:if test="${row.gender eq 'MAN'}">
	<spring:message code="chorbi.man" var="gender" />
</jstl:if>

<jstl:if test="${row.gender eq 'WOMAN'}">
	<spring:message code="chorbi.woman" var="gender" />
</jstl:if>

<acme:display code="chorbi.gender" property="${gender}" />
<br/>

<jstl:if test="${row.relationship eq 'LOVE'}">
	<spring:message code="chorbi.love" var="relationship" />
</jstl:if>

<jstl:if test="${row.relationship eq 'FRIENDSHIP'}">
	<spring:message code="chorbi.friendship" var="relationship" />
</jstl:if>

<jstl:if test="${row.relationship eq 'ACTIVITIES'}">
	<spring:message code="chorbi.activities" var="relationship" />
</jstl:if>

<acme:display code="chorbi.relationship" property="${relationship}" />
<br/>

<acme:display code="coordinates.country" property="${chorbi.coordinates.country}" />
<br/>

<acme:display code="coordinates.state" property="${chorbi.coordinates.state}" />
<br/>

<acme:display code="coordinates.province" property="${chorbi.coordinates.province}" />
<br/>

<acme:display code="coordinates.city" property="${chorbi.coordinates.city}" />
