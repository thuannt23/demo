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
            <c:when test="${complaint.complaintID != 0}">
                Edit Complaint
            </c:when>
            <c:otherwise>
                Add New Complaint
            </c:otherwise>
        </c:choose>
    </h3>

    <form:form modelAttribute="complaint" method="post">

        <form:hidden path="complaintID" />

        <div class="mb-3">
            <form:label path="householdID" cssClass="form-label">Household ID</form:label>
            <form:input path="householdID" cssClass="form-control" type="number"/>
        </div>

        <div class="mb-3">
            <form:label path="description" cssClass="form-label">Description</form:label>
            <form:textarea path="description" cssClass="form-control" rows="4"/>
        </div>

        <div class="mb-3">
            <form:label path="submissionDate" cssClass="form-label">Submission Date</form:label>
            <form:input path="submissionDate" cssClass="form-control" type="date"/>
        </div>

        <div class="mb-3">
            <form:label path="status" cssClass="form-label">Status</form:label>
            <form:select path="status" cssClass="form-select">
                <form:option value="Pending">Pending</form:option>
                <form:option value="Resolved">Resolved</form:option>
            </form:select>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${complaint.complaintID != 0}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/complaints"
           class="btn btn-secondary">
            Cancel
        </a>

    </form:form>
</div>