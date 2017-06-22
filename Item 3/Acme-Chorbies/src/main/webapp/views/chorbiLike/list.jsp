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

<!-- Listing grid -->
<display:table pagesize="5" class="displaytag" 
	name="chorbies" requestURI="${requestURI}" id="row">
	
	<spring:message code="chorbi.man" var="cMan" />
	<spring:message code="chorbi.woman" var="cWoman" />
	<spring:message code="chorbi.love" var="cLove" />
	<spring:message code="chorbi.activities" var="cActivities" />
	<spring:message code="chorbi.friendship" var="cFriendship" />
	
	<!-- Attributes -->
	
	<spring:message code="chorbi.picture" var="pic" />
	<display:column  title="${pic}">
		<img src="${row.picture}" style="max-width: 150px; max-height: 150px;" />
	</display:column>
	
	<acme:column code="actor.name" property="${row.name}"/>
	
	<acme:column code="actor.surname" property="${row.surname}"/>
	
	<spring:message code="chorbi.relationship" var="cRelationship" />
	<display:column title="${cRelationship}">
		<jstl:if test="${row.relationship eq 'LOVE'}">
			<spring:message code="chorbi.love" />
		</jstl:if>

		<jstl:if test="${row.relationship eq 'FRIENDSHIP'}">
			<spring:message code="chorbi.friendship" />
		</jstl:if>
		
		<jstl:if test="${row.relationship eq 'ACTIVITIES'}">
			<spring:message code="chorbi.activities" />
		</jstl:if>
	</display:column>
	
	<spring:message code="chorbi.gender" var="cGender" />
	<display:column title="${cGender}">
		<jstl:if test="${row.gender eq 'MAN'}">
			<spring:message code="chorbi.man" />
		</jstl:if>

		<jstl:if test="${row.gender eq 'WOMAN'}">
			<spring:message code="chorbi.woman" />
		</jstl:if>
	</display:column>
	
	<acme:column code="chorbi.birthdate" property="${row.birthdate}"/>

	<acme:column code="coordinates.country" property="${row.coordinates.country}"/>
	
	<acme:column code="coordinates.state" property="${row.coordinates.state}"/>
	
	<acme:column code="coordinates.province" property="${row.coordinates.province}"/>
	
	<acme:column code="coordinates.city" property="${row.coordinates.city}"/>
	
	<display:column>
		<acme:link href="chorbi/display.do?chorbiId=${row.id}" code="chorbi.profile"/>
	</display:column>
	
</display:table>