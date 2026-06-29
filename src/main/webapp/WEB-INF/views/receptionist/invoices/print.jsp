<%@ include file="/WEB-INF/views/shared/layout/header.jspf" %>
<div class="hero-lite"><div class="container"><h1>Print Invoice</h1><p>Printable invoice preview.</p></div></div>
<div class="hotel-section"><div class="container"><section class="panel printable-invoice">
  <h2 class="section-title"><i class="fa fa-print"></i><span>Invoice #${invoice.id}</span></h2>
  <p>Customer: ${invoice.customerName}</p>
  <p>Room: ${invoice.roomNumber} - ${invoice.roomType}</p>
  <p>Stay: ${invoice.checkInDate} to ${invoice.checkOutDate}</p>
  <p>Nights: ${invoice.nights}</p>
  <p>Room Price: <fmt:formatNumber value="${invoice.roomPricePerNight}" type="currency" currencySymbol="VND "/></p>
  <p>Amount: <fmt:formatNumber value="${invoice.amount}" type="currency" currencySymbol="VND "/></p>
  <p>Status: ${invoice.paymentStatus}</p>
  <button class="boxed-btn3 small" type="button" onclick="window.print()">Print Invoice</button>
</section></div></div>
<%@ include file="/WEB-INF/views/shared/layout/footer.jspf" %>
