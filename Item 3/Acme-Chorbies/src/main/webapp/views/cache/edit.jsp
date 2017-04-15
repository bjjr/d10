<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="cache/administrator/edit.do" modelAttribute="cacheForm" >

	<acme:textbox code="cache.timeout" path="timeout" size="5" />
	
	<div>
		<acme:submit name="save" code="misc.save"/>
		<acme:cancel url="cache/administrator/display.do" code="misc.cancel"/>
	</div>
</form:form>
