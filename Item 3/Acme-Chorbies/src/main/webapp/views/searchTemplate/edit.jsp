<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="searchTemplate/chorbi/edit.do" modelAttribute="searchTemplate" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<acme:textbox code="searchTemplate.age" path="age" />
	<acme:textbox code="searchTemplate.gender" path="gender" />
	<acme:textbox code="searchTemplate.keyword" path="keyword"/>
	<acme:textbox code="searchTemplate.relationship" path="relationship" />
	
	<acme:textbox code="searchTemplate.coordinates.country" path="coordinatesTemplate.country"/>
	<acme:textbox code="searchTemplate.coordinates.state" path="coordinatesTemplate.state"/>
	<acme:textbox code="searchTemplate.coordinates.province" path="coordinatesTemplate.province"/>
	<acme:textbox code="searchTemplate.coordinates.city" path="coordinatesTemplate.city"/>
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:cancel url="/" code="misc.cancel"/>
		<acme:cancel url="searchTemplate/chorbi/search.do" code="searchTemplate.search"/>
	</div>
</form:form>
