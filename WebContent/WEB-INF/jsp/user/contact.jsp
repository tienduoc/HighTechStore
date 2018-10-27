<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<div class="page-template-default contact-v1">
	<div id="content" class="site-content" tabindex="-1">
		<div class="container">

			<nav class="woocommerce-breadcrumb">
				<a href="home.html">Home</a><span class="delimiter"><i
					class="fa fa-angle-right"></i></span>Contact
			</nav>

			<div id="primary" class="content-area">
				<main id="main" class="site-main">
				<article class="post-2508 page type-page status-publish hentry"
					id="post-2508">
					<div itemprop="mainContentOfPage" class="entry-content">
						<div class="map"
							style="width: 100vw; position: relative; margin-left: -50vw; left: 50%; margin-bottom: 3em;">
							<iframe height="514" allowfullscreen="" style="border: 0px none;"
								src="https://www.google.com/maps/embed/v1/place?key= AIzaSyCuXlJEHe4TZc7Otg9HWZuFyd51JhfaTUs&q=CNC%20Aptech%20Computer%20Education%2C%20440%2F30%20Nguy%E1%BB%85n%20Ki%E1%BB%87m%2C%20ph%C6%B0%E1%BB%9Dng%203%2C%20Ph%C3%BA%20Nhu%E1%BA%ADn%2C%20H%E1%BB%93%20Ch%C3%AD%20Minh&amp;key=AIzaSyD4iE2xVSpkLLOXoyqT-RuPwURN3ddScAI"
								width="600" height="450" style="border:0" allowfullscreen></iframe>
						</div>
						<div class="vc_row-full-width vc_clearfix"></div>
						<div class="vc_row wpb_row vc_row-fluid">
							<div
								class="contact-form wpb_column vc_column_container vc_col-sm-9 col-sm-9">
								<div class="vc_column-inner ">
									<div class="wpb_wrapper">
										<div class="wpb_text_column wpb_content_element ">
											<div class="wpb_wrapper">
												<h2 class="contact-page-title">Leave us a Message</h2>
												<p>Thank you for your attention to our goods. If you are
													not satisfied or have contributed to our service, we are
													happy to receive your valuable feedback and sincerely
													appreciate these valuable ideas.</p>
											</div>
										</div>
										<div lang="en-US" dir="ltr" id="wpcf7-f2507-p2508-o1"
											class="wpcf7" role="form">
											<div class="screen-reader-response"></div>
											<s:form class="wpcf7-form" method="post"
												commandName="contact"
												action="${pageContext.request.contextPath }/home/contact.html">
												<div class="form-group row">
													<div class="col-xs-12 col-md-6">
														<label>Name*</label><br> <span
															class="wpcf7-form-control-wrap first-name"><s:input
																required="required"
																class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required input-text"
																path="name" name="name"></s:input></span>
													</div>
													<div class="col-xs-12 col-md-6">
														<label>Email*</label><br> <span
															class="wpcf7-form-control-wrap last-name"><s:input
																required="required" type="email"
																class="wpcf7-form-control wpcf7-text wpcf7-validates-as-required input-text"
																path="email" name="email"></s:input></span>
													</div>
												</div>
												<div class="form-group">
													<label>Subject</label><br> <span
														class="wpcf7-form-control-wrap subject"><s:input
															type="text"
															class="wpcf7-form-control wpcf7-text input-text"
															path="subject" name="subject"></s:input></span>
												</div>
												<div class="form-group">
													<label>Your Message</label><br> <span
														class="wpcf7-form-control-wrap your-message"><s:textarea
															class="wpcf7-form-control wpcf7-textarea" rows="10"
															cols="40" path="description" id="description"
															name="description"></s:textarea></span>
												</div>
												<div class="form-group clearfix">

													<p>
														<button type="submit">Send Message</button>
													</p>

												</div>
											</s:form>
										</div>
									</div>
								</div>
							</div>
							<div
								class="store-info wpb_column vc_column_container vc_col-sm-3 col-sm-3">
								<div class="vc_column-inner ">
									<div class="wpb_wrapper">
										<div class="wpb_text_column wpb_content_element ">
											<div class="wpb_wrapper">
												<h2 class="contact-page-title">Our Store</h2>
												<address>
													440/30 Nguyễn Kiệm Road<br> Ward 4, Phu Nhuan District<br>
													Ho Chi Minh City, Vietnam
												</address>
												<h3>Hours of Operation</h3>
												<ul class="list-unstyled operation-hours inner-right-md">
													<li class="clearfix"><span class="day">Monday:</span><span
														class="pull-right flip hours">08h00 - 22h00</span></li>
													<li class="clearfix"><span class="day">Tuesday:</span><span
														class="pull-right flip hours">08h00 - 22h00</span></li>
													<li class="clearfix"><span class="day">Wednesday:</span><span
														class="pull-right flip hours">08h00 - 22h00</span></li>
													<li class="clearfix"><span class="day">Thursday:</span><span
														class="pull-right flip hours">08h00 - 22h00</span></li>
													<li class="clearfix"><span class="day">Friday:</span><span
														class="pull-right flip hours">08h00 - 22h00</span></li>
													<li class="clearfix"><span class="day">Saturday:</span><span
														class="pull-right flip hours">09h00 - 21h00</span></li>
													<li class="clearfix"><span class="day">Sunday</span><span
														class="pull-right flip hours">Closed</span></li>
												</ul>
												<h3>Careers</h3>
												<p class="inner-right-md">
													If you’re interested in employment opportunities at
													High Tech Store, please email us: <a
														href="mailto:contact@hightechstore.com">contact@hightechstore.com</a>
												</p>
											</div>
										</div>
									</div>
								</div>
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
</div>