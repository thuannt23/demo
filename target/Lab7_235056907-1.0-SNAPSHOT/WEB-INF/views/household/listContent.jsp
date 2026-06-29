<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Danh sách hộ gia đình</h2>

    <a href="${pageContext.request.contextPath}/households/add"
       class="btn btn-success">
        + Thêm mới
    </a>
</div>

<table class="table table-bordered table-hover">
    <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Chủ hộ</th>
            <th>Số điện thoại</th>
            <th>Email</th>
            <th>Apartment ID</th>
            <th>Hành động</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="h" items="${households}">
            <tr>
                <td>${h.householdID}</td>
                <td>${h.headOfHousehold}</td>
                <td>${h.contactNumber}</td>
                <td>${h.email}</td>
                <td>${h.apartmentID}</td>

                <td>
                    <a href="${pageContext.request.contextPath}/households/edit/${h.householdID}"
                       class="btn btn-sm btn-primary">
                        Sửa
                    </a>

                    <a href="${pageContext.request.contextPath}/households/delete/${h.householdID}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Bạn chắc chắn muốn xóa?')">
                        Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>