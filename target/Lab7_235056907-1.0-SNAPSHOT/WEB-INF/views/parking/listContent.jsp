<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Danh sách bãi đỗ xe</h2>

    <a href="${pageContext.request.contextPath}/parkings/add"
       class="btn btn-success">
        + Thêm mới
    </a>
</div>

<table class="table table-bordered table-hover">
    <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Household ID</th>
            <th>Số xe</th>
            <th>Loại xe</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="p" items="${parkings}">
            <tr>
                <td>${p.parkingID}</td>
                <td>${p.householdID}</td>
                <td>${p.parkingNumber}</td>

                <td>
                    <c:choose>
                        <c:when test="${p.vehicleType == 'Car'}">Ô tô</c:when>
                        <c:otherwise>Xe máy</c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <c:choose>
                        <c:when test="${p.status == 'Occupied'}">
                            <span class="badge bg-danger">Đang sử dụng</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-success">Trống</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/parkings/edit/${p.parkingID}"
                       class="btn btn-sm btn-primary">
                        Sửa
                    </a>

                    <a href="${pageContext.request.contextPath}/parkings/delete/${p.parkingID}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Bạn chắc chắn muốn xóa?')">
                        Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>