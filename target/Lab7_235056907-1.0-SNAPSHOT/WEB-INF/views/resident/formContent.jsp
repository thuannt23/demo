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
            <c:when test="${resident.residentID != 0}">
                Edit Resident
            </c:when>
            <c:otherwise>
                Add New Resident
            </c:otherwise>
        </c:choose>
    </h3>

    <form:form modelAttribute="resident" method="post">

        <form:hidden path="residentID" />

        <div class="mb-3">
            <form:label path="householdID" cssClass="form-label">Household ID</form:label>
            <form:input path="householdID" cssClass="form-control" type="number"/>
        </div>

        <div class="mb-3">
            <form:label path="fullName" cssClass="form-label">Full Name</form:label>
            <form:input path="fullName" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <form:label path="dateOfBirth" cssClass="form-label">Date of Birth</form:label>
            <form:input path="dateOfBirth" cssClass="form-control" type="date"/>
        </div>

        <div class="mb-3">
            <form:label path="gender" cssClass="form-label">Gender</form:label>
            <form:select path="gender" cssClass="form-select">
                <form:option value="Male">Male</form:option>
                <form:option value="Female">Female</form:option>
            </form:select>
        </div>

        <div class="mb-3">
            <form:label path="relationship" cssClass="form-label">Relationship</form:label>
            <form:input path="relationship" cssClass="form-control"/>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${resident.residentID != 0}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/residents"
           class="btn btn-secondary">
            Cancel
        </a>

    </form:form>
</div>