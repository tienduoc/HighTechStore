<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>


<div class="page home page-template-default">

	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Cart
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="page type-page status-publish hentry">
					<header class="entry-header">
						<h1 itemprop="name" class="entry-title">Cart</h1>
					</header>
					<!-- .entry-header -->

					<form method="post" action="${pageContext.request.contextPath }/cart/update.html">

						<table class="shop_table shop_table_responsive cart">
							<thead>
								<tr>
									<th class="product-remove">&nbsp;</th>
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
									<c:set var="s" value="${s + i.product.price * i.quantity }"></c:set>
									<tr class="cart_item">

										<td class="product-remove"><a class="remove"
											href="${pageContext.request.contextPath }/cart/delete/${i.product.id }.html">×</a>
										</td>

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

										<td data-title="Quantity" class="product-quantity">
											<div class="quantity buttons_added">
												<label>Quantity:</label> <input type="number"
													max="${i.product.quantity }" min="1" value="${i.quantity }"
													class="input-text qty number" name="quantity">
											</div>
										</td>

										<td data-title="Total" class="product-subtotal"><span
											class="amount">${i.product.price * i.quantity }</span></td>
									</tr>
								</c:forEach>
								<tr>
									<td class="actions" colspan="6">

										<div class="coupon">
											
										</div> 
										
										
										<input value="Update Cart" name="update_cart" class="button"
										type="submit">
										

										<div class="wc-proceed-to-checkout">

											<a class="checkout-button button alt wc-forward"
												href="${pageContext.request.contextPath }/cart/beforeCheckOut.html">Proceed
												to Checkout</a>
										</div> <input value="1eafc42c5e" name="_wpnonce" id="_wpnonce"
										type="hidden"><input value="/electro/cart/"
										name="_wp_http_referer" type="hidden">
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<div class="cart-collaterals">

						<div class="cart_totals ">

							<h2>Cart Totals</h2>

							<table class="shop_table shop_table_responsive">

								<tbody>												

									<tr class="order-total">
										<th>Total</th>
										<td data-title="Total"><strong><span
												class="amount">$${s }</span></strong></td>
									</tr>


								</tbody>
							</table>

							<div class="wc-proceed-to-checkout">

								<a class="checkout-button button alt wc-forward"
									href="${pageContext.request.contextPath }/cart/beforeCheckOut.html">Proceed
									to Checkout</a>
							</div>
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