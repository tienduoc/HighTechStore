<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="page home page-template-default">
	<div id="content" class="site-content" tabindex="-1">
		<div class="container">


			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Checkout
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="page type-page status-publish hentry">
					<header class="entry-header">
						<h1 itemprop="name" class="entry-title">Checkout</h1>
					</header>
					<!-- .entry-header -->

					<form class="checkout woocommerce-checkout" method="get" action="${pageContext.request.contextPath }/cart/check.html">

						<div id="customer_details" class="col2-set">
							<div class="col-1">
								<div class="woocommerce-billing-fields">

									<h3>Shipping Infomation</h3>

									<p id="billing_first_name_field"
										class="form-row form-row form-row-first validate-required">
										<label class="" for="billing_first_name">Consignee
											Name <abbr title="required" class="required">*</abbr>
										</label><input type="text" readonly="readonly"
											value="${account.name }" placeholder="" readonly="readonly"
											id="billing_first_name" name="billing_first_name"
											class="input-text ">
									</p>

									<p id="billing_last_name_field"
										class="form-row form-row form-row-last validate-required">
										<label class="" for="billing_last_name">Phone <abbr
											title="required" class="required">*</abbr></label><input type="text"
											value="${account.phoneNumber }" readonly="readonly"
											placeholder="" id="billing_last_name"
											name="billing_last_name" class="input-text ">
									</p>
									<div class="clear"></div>



									<p id="billing_country_field"
										class="form-row form-row form-row-wide validate-required validate-email">
										<label class="" for="billing_country">Address <abbr
											title="required" class="required">*</abbr></label><input type="text"
											value="${account.address }" placeholder="" name="address"
											id="billing_country" class="input-text ">
									</p>
									<div class="clear"></div>

									<div class="clear"></div>

								</div>
							</div>

							<div class="col-2">
								<h3>Note Details</h3>
								<div class="woocommerce-shipping-fields">
									<p id="order_comments_field" class="form-row form-row notes">
										<label class="" for="order_comments">Order Notes</label>
										<textarea cols="5" rows="2"
											placeholder="Notes about your order, e.g. special notes for delivery."
											id="order_comments" class="input-text " name="customerNote"></textarea>
									</p>
								</div>
							</div>
						</div>

						<h3 id="order_review_heading">Your order</h3>

						<div class="woocommerce-checkout-review-order" id="order_review">
							<table class="shop_table woocommerce-checkout-review-order-table">
								<thead>
									<tr>
										<th class="product-name">Product</th>
										<th class="product-total">Total</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="s" value="0"></c:set>
									<c:forEach var="i" items="${sessionScope.cart }" varStatus="p">
									
										<c:set var="s" value="${s + i.product.price * i.quantity }"></c:set>

										<tr class="cart_item">
											<td class="product-name">${i.product.name }&nbsp;<strong
												class="product-quantity">× ${i.quantity }</strong>
											</td>
											<td class="product-total"><span class="amount">$${i.product.price
													* i.quantity }</span></td>
										</tr>
									</c:forEach>
								</tbody>
								<tfoot>

									<tr class="order-total">
										<th>Subtotal</th>
										<td><span class="amount">$${s }</span></td>
										<div hidden="hidden">
											<input name="total" value="${s }" />
										</div>
									</tr>

								</tfoot>
							</table>

							<div class="woocommerce-checkout-payment" id="payment">
								<div class="form-row place-order">

									<p class="form-row terms wc-terms-and-conditions">
										<input type="checkbox" id="terms" name="terms"
											class="input-checkbox" required="required" value="0">
										<label class="checkbox" for="terms">I've read and
											accept the <a target="_blank"
											href="${pageContext.request.contextPath }/home/temp.html">terms
												&amp; conditions</a> <span class="required">*</span>
										</label> <input type="hidden" value="1" name="terms-field">
									</p>

									<input value="Choose Payment" class="button alt" type="submit">
								</div>								
							</div>
						</div>
					</form>
				</article>
				</main>
				<!-- #main -->
			</div>
			<!-- #primary -->
		</div>
		<!-- .container -->
	</div>
</div>