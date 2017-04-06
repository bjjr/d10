<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${action}" modelAttribute="${modelAttribute}" >
	
	<form:hidden path="id"/>
	<form:hidden path="version"/>
	
	<spring:message code="chorbi.man" var="cMan" />
	<spring:message code="chorbi.woman" var="cWoman" />
	<spring:message code="chorbi.love" var="cLove" />
	<spring:message code="chorbi.activities" var="cActivities" />
	<spring:message code="chorbi.friendship" var="cFriendship" />
	
	<jstl:if test="${isEdit}">
		<acme:textbox code="actor.email" path="email" />
		<acme:textbox code="actor.phone" path="phone" />
		<acme:textbox code="chorbi.picture" path="picture" />
		<acme:textarea code="chorbi.description" path="description"/>
	</jstl:if>
	
	<div>
		<form:label path="relationship">
			<spring:message code="chorbi.relationship" />
		</form:label>
		<form:select path="relationship">
			<form:option value="" label="----" />
			<form:option value="FRIENDSHIP" label="${cFriendship}" />
			<form:option value="LOVE" label="${cLove}" />
			<form:option value="ACTIVITIES" label="${cActivities}" />
		</form:select>
		<form:errors path="relationship" cssClass="error" />
	</div>
	
	<jstl:if test="${!isEdit}">
		<acme:textbox code="actor.name" path="name"/>
		<acme:textbox code="actor.surname" path="surname"/>
		<acme:textbox code="actor.email" path="email"/>
		<acme:textbox code="actor.phone" path="phone"/>
		<acme:textbox code="chorbi.picture" path="picture"/>
		<acme:textarea code="chorbi.description" path="name"/>
		<acme:datebox code="chorbi.birthdate" path="birthdate"/>
		<div>
			<form:label path="gender">
				<spring:message code="chorbi.gender" />
			</form:label>
			<form:select path="gender">
				<form:option value="" label="----" />
				<form:option value="MAN" label="${cMan}" />
				<form:option value="WOMAN" label="${cWoman}" />
			</form:select>
			<form:errors path="gender" cssClass="error" />
		</div>
		<acme:textbox code="coordinates.country" path="country"/>
		<acme:textbox code="coordinates.state" path="state"/>
		<acme:textbox code="coordinates.province" path="province"/>
		<acme:textbox code="coordinates.city" path="city"/>
		<br />
		<acme:textbox code="chorbi.username" path="username"/>
		<acme:textbox code="chorbi.password" path="password"/>
		<acme:textbox code="chorbi.passwdConf" path="passwdConfirmation"/>
	</jstl:if>

	<acme:submit name="save" code="misc.save"/>		
	<acme:cancel url="/" code="misc.cancel"/>
		
</form:form>
<spring:message code="misc.eula" />