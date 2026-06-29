<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Danh sách căn hộ</h2>
    <a href="${pageContext.request.contextPath}/apartments/add" class="btn btnsuccess">+ Thêm mới</a>
</div>
<table class="table table-bordered table-hover">
    <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Số căn</th>
            <th>Tầng</th>
            <th>Diện tích (m²)</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="a" items="${apartments}">
            <tr>
                <td>${a.apartmentID}</td>
                <td>${a.apartmentNumber}</td>
                <td>${a.floor}</td>
                <td>${a.area}</td>
                <td>
                    <c:choose>
                        <c:when test="${a.status == 'Sold'}"><span class="badge bg-danger">Đã
                                bán</span></c:when>
                        <c:when test="${a.status == 'Rented'}"><span class="badge bg-warning
                              text-dark">Đang thuê</span></c:when>
                        <c:otherwise><span class="badge bg-success">Trống</span></c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a
                        href="${pageContext.request.contextPath}/apartments/edit/${a.apartmentID}" class="btn
                        btn-sm btn-primary">Sửa</a>
                    <a
                        href="${pageContext.request.contextPath}/apartments/delete/${a.apartmentID}" class="btn
                        btn-sm btn-danger" onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>