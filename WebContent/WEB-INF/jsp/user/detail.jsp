<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="single-product full-width extended" name="product">
	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="${pageContext.request.contextPath }/home.html">Home</a> <span class="delimiter"> <i
					class="fa fa-angle-right"></i>
				</span> <a
					href="${pageContext.request.contextPath }/product/rcategory/${product.category.category.id }.html">
					${product.category.category.name} </a> <span class="delimiter">
					<i class="fa fa-angle-right"></i>
				</span> <a
					href="${pageContext.request.contextPath }/product/category/${product.category.id }.html">${product.category.name}</a>
				<span class="delimiter"><i class="fa fa-angle-right"></i> </span>${product.name}
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<div class="product">
					<div class="single-product-wrapper">
						<div class="product-images-wrapper">
							<!-- <span class="onsale">Sale!</span>  -->
							<div class="images electro-gallery">

								<div class="thumbnails-single owl-carousel">
									<c:forEach var="pt1" items="${product.photos }">
										<a
											href="${pageContext.request.contextPath }/assets/user/images/products/${pt1.url }"
											class="zoom" title="" data-rel="prettyPhoto[product-gallery]">
											<img src="assets/images/blank.gif"
											data-echo="${pageContext.request.contextPath }/assets/user/images/products/${pt1.url }"
											class="wp-post-image" alt="">
										</a>
									</c:forEach>
								</div>
								<!-- .thumbnails-single -->

								<div class="thumbnails-all columns-5 owl-carousel">
									<c:forEach var="pt2" items="${product.photos }">
										<a
											href="${pageContext.request.contextPath }/assets/user/images/products/${pt2.url }"
											class="first" title=""> <img
											src="assets/images/blank.gif"
											data-echo="${pageContext.request.contextPath }/assets/user/images/products/${pt2.url }"
											class="wp-post-image" alt="">
										</a>
									</c:forEach>
								</div>
								<!-- .thumbnails-all -->
							</div>
							<!-- .electro-gallery -->
						</div>
						<!-- /.product-images-wrapper -->

						<div class="summary entry-summary">
							<span class="loop-product-categories"> <a
								href="${pageContext.request.contextPath }/product/category/${product.category.id }.html"
								rel="tag">${product.category.name}</a>
							</span>

							<!-- .loop-product-categories -->

							<h1 itemprop="name" class="product_title entry-title">${product.name}</h1>

							<div class="brand">
								<a
									href="${pageContext.request.contextPath }/product/producer/${product.producer.id}.html"><img
									src="${pageContext.request.contextPath }/assets/user/images/producer/${product.producer.photo}" /></a> <br>
							</div>
							<!-- .brand -->

							<h5>Top Technical</h5>
							<div itemprop="description">
								<p style="white-space: pre;">${product.shortDescription}</p>

							</div>
							<!-- /description -->

						</div>
						<!-- .summary -->
						<div class="product-actions-wrapper">
							<div class="product-actions">

								<c:choose>
									<c:when test="${product.quantity == 0}">
										<div class="availability in-stock">
											Availablity: <span style="color: red;">Out of stock</span>
										</div>
									</c:when>

									<c:when test="${product.quantity >=4}">
										<div class="availability in-stock">
											Availablity: <span> ${product.quantity} in stock</span>
										</div>
									</c:when>

									<c:otherwise>
										<div class="availability in-stock">
											Availablity: <span style="color: orange;">Only
												${product.quantity} in stock</span>
										</div>
									</c:otherwise>
								</c:choose>

								<!-- /.availability -->

								<div itemprop="offers">
 
									<p class="price">
										<span class="electro-price"> <ins>
												<span class="amount">$${product.price}</span>
											</ins></span>
									</p>

								</div>

								<c:if test="${product.quantity >0 }">
									<div class="variations_form cart">

										<div class="single_variation_wrap">
											<div class="woocommerce-variation single_variation"></div>
											<div
												class="woocommerce-variation-add-to-cart variations_button">
												<a
													href="${pageContext.request.contextPath }/cart/buy/${product.id }.html">
													<button class="single_add_to_cart_button button alt">Add to cart</button>
												</a>
											</div>
										</div>
									</div>
								</c:if>
								<!-- /.variations_form -->
							</div>
							<!-- /.product-actions -->
						</div>
						<!-- /.product-actions-wrapper -->
					</div>
					<!-- /.single-product-wrapper -->


					<div class="electro-tabs electro-tabs-wrapper wc-tabs-wrapper">

						<div class="electro-tab" id="tab-description">
							<div class="container">
								<div class="tab-content">
									<ul class="ec-tabs">

										<li class="description_tab active"><a
											href="#tab-description">Description</a></li>
										<li class="specification_tab"><a
											href="#tab-specification">Specification</a></li>

									</ul>

									<div class="electro-description">
										<p style="white-space: pre-line;" >${product.description } </p>
									</div>
									<!-- /.electro-description -->
								</div>
							</div>
						</div>
						<!-- /.electro-tab -->


						<div class="electro-tab" id="tab-specification">
							<div class="container">
								<div class="tab-content" align="left"
									style="padding-bottom: 30%;">
									<ul class="ec-tabs">

										<li class="description_tab"><a href="#tab-description">Description</a>
										</li>
										<li class="specification_tab active"><a
											href="#tab-specification">Specification</a></li>

									</ul>

									<h3>Technical Specifications</h3>
									<p style="white-space: pre-line; width: 300">${product.specification}</p>
								</div>
							</div>
						</div>
						<!-- /.electro-tab -->

					</div>
					<!-- /.electro-tabs -->





					<div class="related products">
						<h2>Other Products</h2>

						<ul class="products columns-5">

							<c:forEach var="r" items="${related }">
								<li class="product">
									<div class="product-outer">
										<div class="product-inner">
											<span class="loop-product-categories"><a
												href="${pageContext.request.contextPath }/product/category/${r.category.id }.html"
												rel="tag">${r.category.name }</a></span> <a
												href="${pageContext.request.contextPath }/product/detail/${r.id }.html">
												<h3>${r.name }</h3>
												<div class="product-thumbnail">
													<c:forEach var="photo" items="${r.photos }">
														<c:if test="${photo.main == true}">
															<img
																data-echo="${pageContext.request.contextPath }/assets/user/images/products/${photo.url }"
																src="${pageContext.request.contextPath }/assets/user/images/blank.gif"
																alt="">
														</c:if>
													</c:forEach>
												</div>
											</a>

											<div class="price-add-to-cart">
												<span class="price"> <span class="electro-price">
														<ins>
															<span class="amount">$${r.price }</span>
														</ins>
												</span>
												</span> <a rel="nofollow"
													href="${pageContext.request.contextPath }/cart/buy/${r.id }.html"
													class="button add_to_cart_button">Add to cart</a>
											</div>
											<!-- /.price-add-to-cart -->
										</div>
										<!-- /.product-inner -->
									</div> <!-- /.product-outer -->
								</li>
							</c:forEach>

						</ul>

						<!-- /.products -->
					</div>

					<!-- /.related -->
				</div>
				<!-- /.product --> </main>
				<!-- /.site-main -->
			</div>
			<!-- /.content-area -->
		</div>
		<!-- /.container -->
	</div>
</div>
<!-- /.site-content -->