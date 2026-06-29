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
            <c:when test="${parking.parkingID != 0}">
                Edit Parking
            </c:when>
            <c:otherwise>
                Add New Parking
            </c:otherwise>
        </c:choose>
    </h3>

    <form:form modelAttribute="parking" method="post">

        <form:hidden path="parkingID" />

        <div class="mb-3">
            <form:label path="householdID" cssClass="form-label">Household ID</form:label>
            <form:input path="householdID" cssClass="form-control" type="number"/>
        </div>

        <div class="mb-3">
            <form:label path="parkingNumber" cssClass="form-label">Parking Number</form:label>
            <form:input path="parkingNumber" cssClass="form-control"/>
        </div>

        <div class="mb-3">
            <form:label path="vehicleType" cssClass="form-label">Vehicle Type</form:label>
            <form:select path="vehicleType" cssClass="form-select">
                <form:option value="Car">Car</form:option>
                <form:option value="Motorbike">Motorbike</form:option>
            </form:select>
        </div>

        <div class="mb-3">
            <form:label path="status" cssClass="form-label">Status</form:label>
            <form:select path="status" cssClass="form-select">
                <form:option value="Occupied">Occupied</form:option>
                <form:option value="Vacant">Vacant</form:option>
            </form:select>
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${parking.parkingID != 0}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/parkings"
           class="btn btn-secondary">
            Cancel
        </a>

    </form:form>
</div>