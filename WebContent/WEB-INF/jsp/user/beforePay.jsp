<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>

<div class="page home page-template-default">

	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Order Detail
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<div class="page type-page status-publish hentry">
					<header class="entry-header">
						<h1 itemprop="name" class="entry-title">Purchase</h1>
					</header>
					<!-- .entry-header -->

					<form method="post">
						<h6 align="center">Order Information</h6>
						<table align="center" width="300px" style="width: 300px">
							<tr>
								<td>Consigned Name:</td>
								<td>${acc.name }</td>
							</tr>
							<tr>
								<td>Phone Number:</td>
								<td>${acc.phoneNumber}</td>
							</tr>
							<tr>
								<td>Address:</td>
								<td><c:out value="${sessionScope.temp.address}" /></td>
							</tr>
							<tr>
								<td>Delivery Note:</td>
								<td><c:out value="${sessionScope.temp.customerNote}" /></td>
							</tr>
						</table>

						<table class="shop_table shop_table_responsive cart">
							<thead>
								<tr>
									<th class="product-thumbnail">&nbsp;</th>
									<th class="product-name">Product</th>
									<th class="product-price">Price</th>
									<th class="product-quantity">Quantity</th>
									<th class="product-subtotal">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="s" value="0"></c:set>
								<c:forEach var="i" items="${sessionScope.cart }">
									<input type="hidden" name="item_name_${p.index + 1 }"
										value="${i.product.name }" />
									<input type="hidden" name="item_number_${p.index + 1 }"
										value="${i.product.id }" />
									<input type="hidden" name="amount_${p.index + 1 }"
										value="${i.product.price }" />
									<input type="hidden" name="quantity_${p.index + 1 }"
										value="${i.quantity }" />
									<c:set var="s" value="${s + i.product.price * i.quantity }"></c:set>
									<tr class="cart_item">

										<td class="product-thumbnail"><a
											href="${pageContext.request.contextPath }/product/detail/${i.product.id }.html">
												<c:forEach var="pt" items="${i.product.photos }">
													<c:if test="${pt.main == true}">
														<img width="180" height="180"
															src="${pageContext.request.contextPath }/assets/user/images/products/${pt.url }"
															alt="">
													</c:if>
												</c:forEach>
										</a></td>
										<td data-title="Product" class="product-name"><a
											href="${pageContext.request.contextPath }/product/detail/${i.product.id }.html">${i.product.name }</a></td>

										<td data-title="Price" class="product-price"><span
											class="amount">$${i.product.price }</span></td>

										<td data-title="Quantity" class="product-quantity"><span
											class="amount">${i.quantity }</span></td>

										<td data-title="Total" class="product-subtotal"><span
											class="amount">$${i.product.price * i.quantity }</span></td>
									</tr>
								</c:forEach>
							</tbody>

						</table>
						<div class="cart-collaterals">

							<div class="cart_totals ">

								<h2>Bill Totals</h2>

								<table class="shop_table shop_table_responsive">

									<tbody>

										<tr class="order-total">
											<th><h3>Total</h3></th>
											<td data-title="Total"><strong><span
													class="amount"><h3>$${s }</h3></span></strong></td>
										</tr>


									</tbody>
								</table>
							</div>
						</div>
						<h4 align="right">Please choose your payment</h4>
						<div align="right">
							<input type="image"
								src="${pageContext.request.contextPath }/assets/user/images/cod_logo.jpg"
								formaction="${pageContext.request.contextPath }/cart/COD.html">

							<input type="hidden" name="upload" value="1"> <input
								type="hidden" name="return" value="${initParam['returnurl'] }">
							<input type="hidden" name="cmd" value="_cart"> <input
								type="hidden" name="business" value="${initParam['business'] }">
							<input type="image"
								src="${pageContext.request.contextPath }/assets/user/images/paypal-logo.jpg"
								formaction="https://www.sandbox.paypal.com/cgi-bin/webscr">
						</div>
					</form>

				</div>
			</div>
			</main>
			<!-- #main -->
		</div>
		<!-- #primary -->
	</div>
	<!-- .container -->
</div>
<!-- #content -->
