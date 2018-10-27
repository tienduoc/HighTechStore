<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="content" class="site-content" tabindex="-1">
	<div class="container">


		<nav class="woocommerce-breadcrumb">
			<a href="${pageContext.request.contextPath }/home.html">Home</a><span
				class="delimiter"><i class="fa fa-angle-right"></i></span>${producer.name }
		</nav>

		<div id="primary" class="content-area">
			<main id="main" class="site-main"> <header class="page-header">
				<h1 class="page-title">${producer.name }</h1>
			</header>

			<div class="shop-control-bar">
				<ul class="shop-view-switcher nav nav-tabs" role="tablist">
					<li class="nav-item"><a class="nav-link active"
						data-toggle="tab" title="Grid View" href="#grid"><i
							class="fa fa-th"></i></a></li>
					<li class="nav-item"><a class="nav-link " data-toggle="tab"
						title="List View" href="#list-view"><i class="fa fa-list"></i></a></li>

				</ul>

			</div>

			<div class="tab-content">

				<div role="tabpanel" class="tab-pane active" id="grid"
					aria-expanded="true">
					<ul class="products columns-4">
						<c:forEach var="p" items="${products }">
							<li class="product ">
								<div class="product-outer">
									<div class="product-inner">
										<span class="loop-product-categories"><a
											href="${pageContext.request.contextPath }/product/category/${p.category.id }.html"
											rel="tag">${p.category.name }</a></span> <a
											href="${pageContext.request.contextPath }/product/detail/${p.id }.html">
											<h3>${p.name }</h3>
											<div class="product-thumbnail">
												<c:forEach var="photo" items="${p.photos }">
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
														<span class="amount">$${p.price }</span>
													</ins>
											</span>
											</span> <a rel="nofollow"
												href="${pageContext.request.contextPath }/cart/buy/${p.id }.html"
												class="button add_to_cart_button">Add to cart</a>
										</div>
										<!-- /.price-add-to-cart -->


									</div>
									<!-- /.product-inner -->
								</div> <!-- /.product-outer -->
							</li>
						</c:forEach>
					</ul>
				</div>
				<div role="tabpanel" class="tab-pane" id="list-view"
					aria-expanded="true">

					<ul class="products columns-3">
						<c:forEach var="p" items="${products }">
							<li class="product list-view">
								<div class="media">
									<div class="media-left">
										<a
											href="${pageContext.request.contextPath }/product/detail/${p.id }.html">
											<c:forEach var="photo" items="${p.photos }">
												<c:if test="${photo.main == true}">
													<img class="wp-post-image"
														data-echo="${pageContext.request.contextPath }/assets/user/images/products/${photo.url }"
														src="${pageContext.request.contextPath }/assets/user/images/blank.gif"
														alt="">
												</c:if>
											</c:forEach>

										</a>
									</div>
									<div class="media-body media-middle">
										<div class="row">
											<div class="col-xs-12">
												<span class="loop-product-categories"><a rel="tag"
													href="#">${p.category.name}</a></span><a
													href="${pageContext.request.contextPath }/product/detail/${p.id }.html"><h3>${p.name }</h3>

													<div class="product-short-description">
														<p style="white-space: pre;">${p.shortDescription }</p>
													</div> </a>
											</div>
											<div class="col-xs-12">

												<c:choose>
													<c:when test="${p.quantity == 0}">
														<div class="availability in-stock">
															Availablity: <span style="color: red;">Out of
																stock</span>
														</div>
													</c:when>

													<c:when test="${p.quantity >=4}">
														<div class="availability in-stock">
															Availablity: <span> ${p.quantity} in stock</span>
														</div>
													</c:when>

													<c:otherwise>
														<div class="availability in-stock">
															Availablity: <span style="color: orange;">Only
																${p.quantity} in stock</span>
														</div>
													</c:otherwise>
												</c:choose>


												<span class="price"><span class="electro-price"><span
														class="amount">$${p.price }</span></span></span> <a
													class="button product_type_simple add_to_cart_button ajax_add_to_cart"
													data-product_sku="5487FB8/35" data-product_id="2706"
													data-quantity="1"
													href="${pageContext.request.contextPath }/cart/buy/${p.id }.html"
													rel="nofollow">Add to cart</a>
											</div>
										</div>
									</div>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>


			</main>
			<!-- #main -->
		</div>
		<!-- #primary -->
	</div>
	<!-- .container -->
</div>