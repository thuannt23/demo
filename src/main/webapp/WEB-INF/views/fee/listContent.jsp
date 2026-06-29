<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="d-flex justify-content-between align-items-center mb-3">
    <h2>Danh sách phí</h2>

    <a href="${pageContext.request.contextPath}/fees/add"
       class="btn btn-success">
        + Thêm mới
    </a>
</div>

<table class="table table-bordered table-hover">
    <thead class="table-primary">
        <tr>
            <th>ID</th>
            <th>Household ID</th>
            <th>Loại phí</th>
            <th>Số tiền</th>
            <th>Hạn thanh toán</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
    </thead>

    <tbody>
        <c:forEach var="f" items="${fees}">
            <tr>
                <td>${f.feeID}</td>
                <td>${f.householdID}</td>
                <td>${f.feeType}</td>
                <td>${f.amount}</td>
                <td>${f.dueDate}</td>

                <td>
                    <c:choose>
                        <c:when test="${f.status == 'Paid'}">
                            <span class="badge bg-success">Đã thanh toán</span>
                        </c:when>
                        <c:otherwise>
                            <span class="badge bg-danger">Chưa thanh toán</span>
                        </c:otherwise>
                    </c:choose>
                </td>

                <td>
                    <a href="${pageContext.request.contextPath}/fees/edit/${f.feeID}"
                       class="btn btn-sm btn-primary">Sửa</a>

                    <a href="${pageContext.request.contextPath}/fees/delete/${f.feeID}"
                       class="btn btn-sm btn-danger"
                       onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>