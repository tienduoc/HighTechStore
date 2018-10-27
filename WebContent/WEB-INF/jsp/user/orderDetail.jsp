<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page home page-template-default">

	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Order Detail
			</nav>


			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="page type-page status-publish hentry">
					<header class="entry-header">
						<h1 itemprop="name" class="entry-title">
							Order ID #${order.id } on
							<fmt:formatDate type="both" dateStyle="short" timeStyle="short"
								value="${order.timeCreation }" />
						</h1>
						<h5>
							Status: <i style="color: blue;">${order.orderStatus.name }</i>
						</h5>
					</header>
					<!-- .entry-header -->

					<form method="get">
					
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
								<c:forEach var="i" items="${detail }">
									<c:set var="s" value="${s + i.price * i.quantity }"></c:set>
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
											class="amount">$${i.price }</span></td>

										<td data-title="Quantity" class="product-quantity"><span
											class="amount">${i.quantity }</span></td>

										<td data-title="Total" class="product-subtotal"><span
											class="amount">${i.price * i.quantity }</span></td>
									</tr>
								</c:forEach>
								<td class="actions" colspan="6">

									<div class="coupon"></div> <input
										value="Go back" name="update_cart" class="button"
										type="button" formaction="${pageContext.request.contextPath }/home/myAccount.html">

									<div class="wc-proceed-to-checkout">
										<button class="checkout-button button alt wc-forward" formaction="${pageContext.request.contextPath }/home/invoice/${order.id }.html">Print Invoice</button>
									</div>
								</td>
							</tbody>
							
						</table>
					</form>

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
									<tr>
										<th><h4>Payment</h4></th>
										<td data-title="Total"><strong><span
												class="amount"><h4>${order.payment.payment }</h4></span></strong></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</article>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- .container -->
	</div>
	<!-- #content -->
</div>