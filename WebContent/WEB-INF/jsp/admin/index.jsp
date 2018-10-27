<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Weather -->
<a class="weatherwidget-io"
	href="https://forecast7.com/en/10d82106d63/ho-chi-minh-city/"
	data-label_1="HO CHI MINH CITY" data-label_2="WEATHER" data-font="Jura"
	data-icons="Climacons Animated" data-days="3" data-theme="pure">HO
	CHI MINH CITY WEATHER</a>
<script>
	!function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (!d.getElementById(id)) {
			js = d.createElement(s);
			js.id = id;
			js.src = "https://weatherwidget.io/js/widget.min.js";
			fjs.parentNode.insertBefore(js, fjs);
		}
	}(document, "script", "weatherwidget-io-js");
</script>
<!--/Weather  -->
<br>
<h3 align="center" style="color: red">All staff please check all correct information before confirming and archiving, thanks !!</h3>
<br>
<h2>Order Remain</h2>
<c:set var="so" value="0"></c:set>
<c:forEach var="o" items="${orders }">
	<c:if test="${o.orderStatus.id == 1 }">
		<c:set var="so" value="${so+1 }"></c:set>
	</c:if>
</c:forEach>
<h3>
	We have ${so } new orders, <a href="${pageContext.request.contextPath }/admin/orders.html">check it</a>
</h3>
<br>
<br>
<h2>Product Notice</h2>
<c:set var="sp" value="0"></c:set>
<c:forEach var="p" items="${products }">
	<c:if test="${p.quantity <= 3}">
		<c:set var="sp" value="${sp+1 }"></c:set>
	</c:if>
</c:forEach>
<h3>
	We have ${sp } product almost sold out, <a href="${pageContext.request.contextPath }/admin/product.html">check it</a>
</h3>
<br>
<br>
<h2>Feedback Contact</h2>
<c:set var="sc" value="0"></c:set>
<c:forEach var="c" items="${contacts }">
	<c:if test="${c.seen == false}">
		<c:set var="sc" value="${sc+1 }"></c:set>
	</c:if>
</c:forEach>
<h3>
	We have ${sc } new feedback from customer, <a href="${pageContext.request.contextPath }/admin/contact.html">check it</a>
</h3>
