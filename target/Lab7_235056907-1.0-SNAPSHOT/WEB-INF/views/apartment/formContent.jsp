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
            <c:when test="${apartment.apartmentID != null}">
                Edit Apartment
            </c:when>
            <c:otherwise>
                Add New Apartment
            </c:otherwise>
        </c:choose>
    </h3>
    <form:form modelAttribute="apartment" method="post" cssClass="needs-validation">
        <form:hidden path="apartmentID" />
        <div class="mb-3">
            <form:label path="apartmentNumber" cssClass="form-label">Apartment
                Number</form:label>
            <form:input path="apartmentNumber" cssClass="form-control" required="true"/>
        </div>
        <div class="mb-3">
            <form:label path="floor" cssClass="form-label">Floor</form:label>
            <form:input path="floor" cssClass="form-control" type="number" required="true"/>
        </div>
        <div class="mb-3">
            <form:label path="area" cssClass="form-label">Area (m²)</form:label>
            <form:input path="area" cssClass="form-control" type="number" step="0.01"
                        required="true"/>
        </div>
        <div class="mb-3">
            <form:label path="status" cssClass="form-label">Status</form:label>
            <form:select path="status" cssClass="form-select">
                <form:option value="Sold">Sold</form:option>
                <form:option value="Rented">Rented</form:option>
                <form:option value="Vacant">Vacant</form:option>
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${apartment.apartmentID != null}">
                    Update
                </c:when>
                <c:otherwise>
                    Save
                </c:otherwise>
            </c:choose>
        </button>
        <a href="<c:url value='/apartments' />" class="btn btn-secondary">Cancel</a>
    </form:form>
</div>
