<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>V-Guitar &mdash; Trang web mua bán nhạc cụ hàng đầu Việt
	Nam</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Free HTML5 Website Template by gettemplates.co" />
<meta name="keywords"
	content="free website templates, free html5, free template, free bootstrap, free website template, html5, css3, mobile first, responsive" />
<meta name="author" content="gettemplates.co" />

<!-- 
	//////////////////////////////////////////////////////

	FREE HTML5 TEMPLATE 
	DESIGNED & DEVELOPED by FreeHTML5.co
		
	Website: 		http://freehtml5.co/
	Email: 			info@freehtml5.co
	Twitter: 		http://twitter.com/fh5co
	Facebook: 		https://www.facebook.com/fh5co

	//////////////////////////////////////////////////////
	 -->

<!-- Facebook and Twitter integration -->
<meta property="og:title" content="V-Guitar" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="V-Guitar" />
<meta property="og:description" content="V-Guitar" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:card" content="" />

<link href="css/googlecss1.css" rel="stylesheet">
<link href="css/googlecss2.css" rel="stylesheet">

<!-- Animate.css -->
<link rel="stylesheet" href="css/animate.css">
<!-- Icomoon Icon Fonts-->
<link rel="stylesheet" href="css/icomoon.css">
<!-- Bootstrap  -->
<link rel="stylesheet" href="css/bootstrap.css">

<!-- Flexslider  -->
<link rel="stylesheet" href="css/flexslider.css">

<!-- Owl Carousel  -->
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/owl.theme.default.min.css">

<!-- Theme style  -->
<link rel="stylesheet" href="css/style.css">

<!-- Modernizr JS -->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"
	type="text/javascript"></script>
<script src="/js/modernizr-2.6.2.min.js"></script>
</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<%@ include file="header.jsp"%>

		<aside id="fh5co-hero" class="js-fullheight">
		<div id="head-container" class="flexslider js-fullheight">
			<ul class="slides">
				<c:forEach items="${hotitems}" var="hotitem">
					<li class="slider-li"
						style="background-image: url(images/${hotitem.images}-1.jpg);">
						<div class="overlay-gradient"></div>
						<div class="container">
							<div
								class="col-md-6 col-md-offset-3 col-md-pull-3 js-fullheight slider-text">
								<div class="slider-text-inner">
									<div class="desc">
										<h2>${hotitem.name}</h2>
										<span class="price"><fmt:formatNumber pattern="###,###"
												value="${hotitem.price}" type="currency" />đ</span>

										<p>${hotitem.info}</p>
										<p>
											<a href="single.html"
												class="btn btn-primary btn-outline btn-lg">Purchase Now</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
		</aside>
		<div id="fh5co-services" class="fh5co-bg-section">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-sm-4 text-center">
						<div class="feature-center animate-box"
							data-animate-effect="fadeIn">
							<span class="icon"> <i class="icon-credit-card"></i>
							</span>
							<h3>Credit Card</h3>
							<p>Thanh toán thông qua thẻ tín dụng quốc tế của các hãng
								Visa , Mastercard vv..</p>
							<p>
								<a href="#" class="btn btn-primary btn-outline">Learn More</a>
							</p>
						</div>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<div class="feature-center animate-box"
							data-animate-effect="fadeIn">
							<span class="icon"> <i class="icon-wallet"></i>
							</span>
							<h3>Save Money</h3>
							<p>Far far away, behind the word mountains, far from the
								countries Vokalia and Consonantia, there live the blind texts.
								Separated they live in Bookmarksgrove</p>
							<p>
								<a href="#" class="btn btn-primary btn-outline">Learn More</a>
							</p>
						</div>
					</div>
					<div class="col-md-4 col-sm-4 text-center">
						<div class="feature-center animate-box"
							data-animate-effect="fadeIn">
							<span class="icon"> <i class="icon-paper-plane"></i>
							</span>
							<h3>Free Delivery</h3>
							<p>Tất cả các đơn hàng trên V-Guitar đều được miễn phí hoàn
								toàn vào giao hàng trong vòng 5 ngày kể từ ngày đặt hàng</p>
							<p>
								<a href="#" class="btn btn-primary btn-outline">Learn More</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="fh5co-product">
			<div class="container">
				<div class="row animate-box">
					<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
						<span>Cool Stuff</span>
						<h2>Products.</h2>
						<p>Don't wanna take your chances on a random feature? Tap the
							link in our bio to reserve your feature today.</p>
					</div>
				</div>
				<form name="fsearch" id="fsearch" action="/searchresult#sl"
					method="get">
					<div class="input-group" style="width: 20.5%; margin: auto;">

						<input name="search" id="inputsearch" type="text"
							placeholder="Search.."
							style="border-width: 0.5px 0.5px 0.5px 0.5px; border-style: groove; height: 33px; padding-left: 3%;">
						<input type="hidden" name="v" value="0" /> <input type="hidden"
							name="p" value="1"> <input type="hidden" name="size"
							value="6"> <span class="input-group-btn"> <span
							class="input-group-btn">
								<button id="buttonsearch" class="btn btn-primary" type="button"
									style="height: 33px; border-width: 0.5px 0.5px 0.5px 0.5px; border-style: groove; box-shadow: 1px 1px 1px 1px #888888;"
									onclick='this.form.submit()'>
									<i class="icon-search"></i>
								</button>
						</span>
					</div>
				</form>
				<div class="styled-select slate" id="sl">
					<select id="slview">
						<c:if test="${viewid==0}">
							<option selected="selected" value="v=0&search=${search}&size=2&p=${pcursor}#fsearch">---Tùy
								chọn xem---</option>
							<option value="?v=1&search=${search}&size=${size}&p=${pcursor}#fsearch">Giá từ thấp
								- cao</option>
							<option value="?v=2&search=${search}&size=${size}&p=${pcursor}#fsearch">Giá từ cao -
								thấp</option>
						</c:if>
						<c:if test="${viewid == 1}">
							<option  value="v=0&search=${search}&size=${size}&p=${pcursor}#fsearch">---Tùy
								chọn xem---</option>
							<option value="?v=1&search=${search}&size=${size}&p=${pcursor}#fsearch" selected="selected">Giá
								từ thấp - cao</option>
							<option value="?v=2&search=${search}&size=${size}&p=${pcursor}#fsearch">Giá từ cao -
								thấp</option>
						</c:if>
						<c:if test="${viewid == 2}">
						<option  value="v=0&search=${search}&size=${size}&p=${pcursor}#fsearch">---Tùy
								chọn xem---</option>
							<option value="?v=1&search=${search}&size=${size}&p=${pcursor}#fsearch">Giá từ thấp
								- cao</option>
							<option value="?v=2&search=${search}&size=${size}&p=${pcursor}#fsearch" selected="selected">Giá
								từ cao - thấp</option>
						</c:if>
					</select>
				</div>
				<div class="row" id="i">


					<c:forEach items="${items}" var="item">
						<div class="col-md-4 text-center animate-box">
							<div class="product">
								<div id="product-grid${item.item_id}" class="product-grid"
									style="background-image:url(images/${item.images}-1.jpg);">
									<div class="coldiv">
										<c:forEach items="${item.collections}" var="collection">
											<c:if test="${collection.collection_id==4}">
												<span class="sale">Sale</span>
											</c:if>
											<c:if test="${collection.collection_id==1}">
												<span class="used">Used</span>
											</c:if>
											<c:if test="${collection.collection_id==3}">
												<span class="outlet">Outlet</span>
											</c:if>
										</c:forEach>
									</div>

									<div class="inner">
										<p>

											<a href="/addcart?id=${item.item_id}&quantity=1" class="icon"><i
												class="icon-shopping-cart"></i></a> <a href="itemdetails?id=${item.item_id}"
												class="icon"><i class="icon-eye"></i></a>
										</p>
									</div>
								</div>
								<div class="desc">
									<h3>
										<a href="single.html">${item.name}</a>
									</h3>
									<span class="price"><fmt:formatNumber pattern="###,###"
											value="${item.price}" type="currency" />đ</span>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<div>
					<table class="pagination-table">
						<tr>
							<td>
							<c:if test="${pcursor>plinksrange}">
							<a href="?v=${viewid}&search=${search}&p=${pcursor-plinksrange}&size=${size}#i"> << </a>
							</c:if>
							<c:if test="${pcursor>1}">
									<a href="?v=${viewid}&search=${search}&p=${pcursor-1}&size=${size}#i">Prev</a>
									
								</c:if> 
								<c:if test="${pcursor > plinksrange}">
									<span>...</span>
								</c:if> 
								<c:choose>
									<c:when test="${pages <= lpage}">
										<c:forEach begin="${fpage}" end="${pages}"
											step="1" varStatus="i">
											<c:choose>
												<c:when test="${pcursor==i.index}">
													<span>${i.index}</span>
												</c:when>
												<c:otherwise>
													<a href="?v=${viewid}&search=${search}&p=${i.index}&size=${size}#i"> <span>${i.index}</span>
													</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:when>
									<c:otherwise>
									<c:forEach begin="${fpage }" end="${lpage}"
											step="1" varStatus="i">
											<c:choose>
												<c:when test="${pcursor==i.index}">
													<span>${i.index}</span>
												</c:when>
												<c:otherwise>

													<a href="?v=${viewid}&search=${search}&p=${i.index}&size=${size}#i"> <span>${i.index}</span>
													</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</c:otherwise>
								</c:choose> <c:if test="${lpage<pages}">
									<span>...</span>
								</c:if> 
								<c:if test="${pcursor< pages}">
									<a href="?v=${viewid}&search=${search}&p=${pcursor+1}&size=${size}#i">Next</a>
								</c:if>
								<c:if test="${lpage<pages}">
								<a href="?v=${viewid}&search=${search}&p=${pcursor+plinksrange}&size=${size}#i"> >> </a>
								</c:if>
						</tr>
					</table>
				</div>
			</div>
		</div>

		<div id="fh5co-testimonial" class="fh5co-bg-section">
			<div class="container">
				<div class="row animate-box">
					<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
						<span>Testimony</span>
						<h2>Happy Clients</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="row animate-box">
							<div class="owl-carousel owl-carousel-fullwidth">
								<div class="item">
									<div class="testimony-slide active text-center">
										<figure> <img src="images/person1.jpg" alt="user">
										</figure>
										<span>Leslie Cheung, via <a
											href="https://www.facebook.com/ThieuDinhAnh" class="twitter">Facebook</a></span>
										<blockquote>
											<p>&ldquo; Dịch vụ tốt &rdquo;</p>
										</blockquote>
									</div>
								</div>
								<div class="item">
									<div class="testimony-slide active text-center">
										<figure> <img src="images/person2.jpg" alt="user">
										</figure>
										<span>Hideto Matsumoto, via <a
											href="https://www.facebook.com/hideto.matsu?fref=ts"
											class="twitter">Facebook</a></span>
										<blockquote>
											<p>&ldquo;Separated they live in Bookmarksgrove right at
												the coast of the Semantics, a large language ocean.&rdquo;</p>
										</blockquote>
									</div>
								</div>
								<div class="item">
									<div class="testimony-slide active text-center">
										<figure> <img src="images/person3.jpg" alt="user">
										</figure>
										<span>Kurt Cobain, via <a
											href="https://www.facebook.com/kurtcobain/?fref=ts"
											class="twitter">Facebook</a></span>
										<blockquote>
											<p>&ldquo;Far from the countries Vokalia and Consonantia,
												there live the blind texts. Separated they live in
												Bookmarksgrove right at the coast of the Semantics, a large
												language ocean.&rdquo;</p>
										</blockquote>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div id="fh5co-counter" class="fh5co-bg fh5co-counter"
			style="background-image: url(images/img_bg_5.jpg);">
			<div class="container">
				<div class="row">
					<div class="display-t">
						<div class="display-tc">
							<div class="col-md-3 col-sm-6 animate-box">
								<div class="feature-center">
									<span class="icon"> <i class="icon-eye"></i>
									</span> <span class="counter js-counter" data-from="0" data-to="22070"
										data-speed="5000" data-refresh-interval="50">1</span> <span
										class="counter-label">Creativity Fuel</span>

								</div>
							</div>
							<div class="col-md-3 col-sm-6 animate-box">
								<div class="feature-center">
									<span class="icon"> <i class="icon-shopping-cart"></i>
									</span> <span class="counter js-counter" data-from="0" data-to="450"
										data-speed="5000" data-refresh-interval="50">1</span> <span
										class="counter-label">Happy Clients</span>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 animate-box">
								<div class="feature-center">
									<span class="icon"> <i class="icon-shop"></i>
									</span> <span class="counter js-counter" data-from="0" data-to="700"
										data-speed="5000" data-refresh-interval="50">1</span> <span
										class="counter-label">All Products</span>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 animate-box">
								<div class="feature-center">
									<span class="icon"> <i class="icon-clock"></i>
									</span> <span class="counter js-counter" data-from="0" data-to="5605"
										data-speed="5000" data-refresh-interval="50">1</span> <span
										class="counter-label">Hours Spent</span>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="fh5co-started">
			<div class="container">
				<div class="row animate-box">
					<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
						<h2>Newsletter</h2>
						<p>Just stay tune for our latest Product. Now you can
							subscribe</p>
					</div>
				</div>
				<div class="row animate-box">
					<div class="col-md-8 col-md-offset-2">
						<form class="form-inline">
							<div class="col-md-6 col-sm-6">
								<div class="form-group">
									<label for="email" class="sr-only">Email</label> <input
										type="email" class="form-control" id="email"
										placeholder="Email">
								</div>
							</div>
							<div class="col-md-6 col-sm-6">
								<button type="submit" class="btn btn-default btn-block">Subscribe</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

		<footer id="fh5co-footer" role="contentinfo">
		<div class="container">
			<div class="row row-pb-md">
				<div class="col-md-4 fh5co-widget">
					<h3>Shop.</h3>
					<p>Facilis ipsum reprehenderit nemo molestias. Aut cum mollitia
						reprehenderit. Eos cumque dicta adipisci architecto culpa amet.</p>
				</div>
				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">About</a></li>
						<li><a href="#">Help</a></li>
						<li><a href="#">Contact</a></li>
						<li><a href="#">Terms</a></li>
						<li><a href="#">Meetups</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Shop</a></li>
						<li><a href="#">Privacy</a></li>
						<li><a href="#">Testimonials</a></li>
						<li><a href="#">Handbook</a></li>
						<li><a href="#">Held Desk</a></li>
					</ul>
				</div>

				<div class="col-md-2 col-sm-4 col-xs-6 col-md-push-1">
					<ul class="fh5co-footer-links">
						<li><a href="#">Find Designers</a></li>
						<li><a href="#">Find Developers</a></li>
						<li><a href="#">Teams</a></li>
						<li><a href="#">Advertise</a></li>
						<li><a href="#">API</a></li>
					</ul>
				</div>
			</div>

			<div class="row copyright">
				<div class="col-md-12 text-center">
					<p>
						<small class="block">&copy; 2016 Free HTML5. All Rights
							Reserved.</small> <small class="block">Designed by <a
							href="http://freehtml5.co/" target="_blank">FreeHTML5.co</a> Demo
							Images: <a href="http://blog.gessato.com/" target="_blank">Gessato</a>
							&amp; <a href="http://unsplash.co/" target="_blank">Unsplash</a></small>
					</p>
					<p>
					<ul class="fh5co-social-icons">
						<li><a href="#"><i class="icon-twitter"></i></a></li>
						<li><a href="https://www.facebook.com/ThieuDinhAnh"><i
								class="icon-facebook"></i></a></li>
						<li><a href="#"><i class="icon-linkedin"></i></a></li>
						<li><a href="#"><i class="icon-dribbble"></i></a></li>
					</ul>
					</p>
				</div>
			</div>

		</div>
		</footer>
	</div>

	<div class="gototop js-top">
		<a href="#" class="js-gotop"><i class="icon-arrow-up"></i></a>
	</div>

	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Easing -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Waypoints -->
	<script src="js/jquery.waypoints.min.js"></script>
	<!-- Carousel -->
	<script src="js/owl.carousel.min.js"></script>
	<!-- countTo -->
	<script src="js/jquery.countTo.js"></script>
	<!-- Flexslider -->
	<script src="js/jquery.flexslider-min.js"></script>
	<!-- Main -->
	<script src="js/main.js"></script>
	<!-- MyOwnJS -->
	<script src="js/myownjs.js"></script>


</body>
</html>

