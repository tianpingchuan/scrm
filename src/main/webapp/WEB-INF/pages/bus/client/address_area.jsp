<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<c:if test="${!empty theAreaList}">
		<c:forEach items="${theAreaList}" var="theAddress">
			<option value="${theAddress.areaCode}">${theAddress.areaName}</option>
		</c:forEach>
	</c:if>
