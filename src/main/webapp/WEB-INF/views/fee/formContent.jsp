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
            <c:when test="${fee.feeID != 0}">
                Edit Fee
            </c:when>
            <c:otherwise>
                Add New Fee
            </c:otherwise>
        </c:choose>
    </h3>

    <form:form modelAttribute="fee" method="post">

        <form:hidden path="feeID" />

        <div class="mb-3">
            <form:label path="householdID" cssClass="form-label">Household ID</form:label>
            <form:input path="householdID" cssClass="form-control" type="number"/>
        </div>

        <div class="mb-3">
            <form:label path="feeType" cssClass="form-label">Fee Type</form:label>
            <form:input path="feeType" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <form:label path="amount" cssClass="form-label">Amount</form:label>
            <form:input path="amount" cssClass="form-control" type="number" step="0.01"/>
        </div>

        <div class="mb-3">
            <form:label path="dueDate" cssClass="form-label">Due Date</form:label>
            <form:input path="dueDate" cssClass="form-control" type="date"/>
        </div>

        <div class="mb-3">
            <form:label path="status" cssClass="form-label">Status</form:label>
            <form:select path="status" cssClass="form-select">
                <form:option value="Paid">Paid</form:option>
                <form:option value="Unpaid">Unpaid</form:option>
            </form:select>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${fee.feeID != 0}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/fees"
           class="btn btn-secondary">
            Cancel
        </a>

    </form:form>
</div>