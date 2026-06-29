<%-- 
    Document   : form
    Created on : Jun 15, 2026, 5:06:35 PM
    Author     : ADMIN
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<div class="container mt-4">

    <h3 class="mb-4">
        <c:choose>
            <c:when test="${household.householdID != 0}">
                Edit Household
            </c:when>
            <c:otherwise>
                Add New Household
            </c:otherwise>
        </c:choose>
    </h3>

    <form:form modelAttribute="household" method="post">

        <form:hidden path="householdID" />

        <div class="mb-3">
            <form:label path="apartmentID" cssClass="form-label">Apartment ID</form:label>
            <form:input path="apartmentID" cssClass="form-control" type="number"/>
        </div>

        <div class="mb-3">
            <form:label path="headOfHousehold" cssClass="form-label">Head of Household</form:label>
            <form:input path="headOfHousehold" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <form:label path="contactNumber" cssClass="form-label">Contact Number</form:label>
            <form:input path="contactNumber" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <form:label path="email" cssClass="form-label">Email</form:label>
            <form:input path="email" cssClass="form-control"/>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${household.householdID != 0}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/households"
           class="btn btn-secondary">
            Cancel
        </a>

    </form:form>
</div>