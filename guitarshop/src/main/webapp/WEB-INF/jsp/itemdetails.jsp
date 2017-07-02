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
<title>Shop &mdash; Free Website Template, Free HTML5 Template
	by gettemplates.co</title>
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
<meta property="og:title" content="" />
<meta property="og:image" content="" />
<meta property="og:url" content="" />
<meta property="og:site_name" content="" />
<meta property="og:description" content="" />
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
<script src="js/modernizr-2.6.2.min.js"></script>
<!-- FOR IE9 below -->
<!--[if lt IE 9]>
	<script src="js/respond.min.js"></script>
	<![endif]-->

</head>
<body>

	<div class="fh5co-loader"></div>

	<div id="page">
		<%@ include file="header.jsp"%>
		<!-- 
	<header id="fh5co-header" class="fh5co-cover fh5co-cover-sm" role="banner" style="background-image:url(images/img_bg_2.jpg);">
		<div class="overlay"></div>
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-md-offset-2 text-center">
					<div class="display-t">
						<div class="display-tc animate-box" data-animate-effect="fadeIn">
							<h1>Product Details</h1>
							<h2>Free html5 templates Made by <a href="http://freehtml5.co" target="_blank">freehtml5.co</a></h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
 -->
		<div id="fh5co-product" style="background-color: antiquewhite; ">
			<div class="container" >
				<div class="row">
					<div class="col-md-10 col-md-offset-1 animate-box">
						<div class="row animate-box">
							<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">
								<h2>${item.name}</h2>
								<p>

									<a href="addcart?id=${item.item_id}&quantity=1"
										class="btn btn-primary btn-outline btn-lg">Add to Cart</a>
								</p>
							</div>
						</div>
						<div class="owl-carousel owl-carousel-fullwidth product-carousel">
							<c:forEach items="${images}" var="img">
								<div class="item">
									<div class="active text-center"
										style=" margin:auto ;width: 600px; height: 400px; background-image: url(images/${img});background-size: contain; background-repeat: no-repeat; background-position:center; ">

									</div>
								</div>
							</c:forEach>

						</div>
						<div class="row animate-box">
							<div class="col-md-8 col-md-offset-2 text-center fh5co-heading">


								<form action="addcart" method = "get">
									<p>
										Số lượng: <input type="text" value="1" name="quantity"
											style="width: 61px; border-width: 0.5px 0.5px 0.5px 0.5px; border-style: groove; height: 33px; padding-left: 3%;" />
									</p>
									<p>
										<input type="hidden" name="id" value="${item.item_id}">
										<input type="submit" name="" value="Add to Cart"
											class="btn btn-primary btn-outline btn-lg">

									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-10 col-md-offset-1">
						<div class="fh5co-tabs animate-box">
							<ul class="fh5co-tab-nav" style="background-color: aliceblue;">
								<li class="active"><a href="#" data-tab="1"><span
										class="icon visible-xs"><i class="icon-file"></i></span><span
										class="hidden-xs">Product Details</span></a></li>
								<li><a href="#" data-tab="2"><span
										class="icon visible-xs"><i class="icon-bar-graph"></i></span><span
										class="hidden-xs">Specification</span></a></li>
								<li><a href="#" data-tab="3"><span
										class="icon visible-xs"><i class="icon-star"></i></span><span
										class="hidden-xs">Feedback &amp; Ratings</span></a></li>
							</ul>

							<!-- Tabs -->
							<div class="fh5co-tab-content-wrap">

								<div class="fh5co-tab-content tab-content active"
									data-tab-content="1">
									<div class="col-md-10 col-md-offset-1">
										<span class="price"><fmt:formatNumber pattern="###,###"
											value="${item.price}" type="currency" />đ</span>
										<h2>${item.name}</h2>
										<h2 class="uppercase">Collections:</h2>
										<p>
											<c:forEach items="${item.collections}" var="collection">
									${collection.name}|
									 </c:forEach>
										</p>
										<h2 class="uppercase">Category:</h2>
										<p>${item.category.name}</p>
										<h2 class="uppercase">Information:</h2>
										<p>${item.info}</p>
										<h2 class="uppercase">In stock:</h2>
										<p>${item.number}</p>
									</div>
								</div>

								<div class="fh5co-tab-content tab-content" data-tab-content="2">
									<div class="col-md-10 col-md-offset-1">
										<h3>Product Specification</h3>
										<table style="width: 60%">
											<tr>
												<td>
													<h2 class="uppercase">Brand name:</h2>
													<p>${item.brandname}</p>

												</td>
												<td>
													<h2 class="uppercase">Color:</h2>
													<p>${item.color}</p>
												</td>
											</tr>
											<tr>
												<td><h2 class="uppercase">Shape:</h2>
													<p>
														${item.shape}
													</p></td>
												<td></td>
											</tr>
										</table>
									</div>
								</div>

								<div class="fh5co-tab-content tab-content" data-tab-content="3">
									<div class="col-md-10 col-md-offset-1">
										<h3>Happy Buyers</h3>
										<div class="feed">
											<div>
												<blockquote>
													<p>Paragraph placeat quis fugiat provident veritatis
														quia iure a debitis adipisci dignissimos consectetur magni
														quas eius nobis reprehenderit soluta eligendi quo
														reiciendis fugit? Veritatis tenetur odio delectus
														quibusdam officiis est.</p>
												</blockquote>
												<h3>&mdash; Louie Knight</h3>
												<span class="rate"> <i class="icon-star2"></i> <i
													class="icon-star2"></i> <i class="icon-star2"></i> <i
													class="icon-star2"></i> <i class="icon-star2"></i>
												</span>
											</div>
											<div>
												<blockquote>
													<p>Paragraph placeat quis fugiat provident veritatis
														quia iure a debitis adipisci dignissimos consectetur magni
														quas eius nobis reprehenderit soluta eligendi quo
														reiciendis fugit? Veritatis tenetur odio delectus
														quibusdam officiis est.</p>
												</blockquote>
												<h3>&mdash; Joefrey Gwapo</h3>
												<span class="rate"> <i class="icon-star2"></i> <i
													class="icon-star2"></i> <i class="icon-star2"></i> <i
													class="icon-star2"></i> <i class="icon-star2"></i>
												</span>
											</div>
										</div>
									</div>
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
						<li><a href="#"><i class="icon-facebook"></i></a></li>
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

</body>
</html>

