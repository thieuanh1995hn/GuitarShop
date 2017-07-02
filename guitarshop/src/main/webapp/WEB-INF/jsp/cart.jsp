<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link href='https://fonts.googleapis.com/css?family=Lato:300,400'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="http://weloveiconfonts.com/api/?family=entypo">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

<link rel='stylesheet prefetch'
	href='https://s3-us-west-2.amazonaws.com/s.cdpn.io/5175/utf-latest.min.css'>

<link rel='stylesheet' href='css/cart/style.css'>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>
	<div class="main">
		<h1>Giỏ hàng</h1>
		<h2 class="sub-heading">Miễn phí vận chuyển tất cả các sản phẩm</h2>
		<h2 class="sub-heading">
			<a href="/home">Trở về trang chủ</a>
		</h2>
		<section class="shopping-cart">
			<ol class="ui-list shopping-cart--list" id="shopping-cart--list">
				<c:choose>
					<c:when test="${not empty itemsincart}">
						<c:forEach items="${itemsincart}" var="itemincart">
							<li class="_grid shopping-cart--list-item">
								<div class="_column product-image">
									<img class="product-image--img"
										src="images/${itemincart.item.images}-1.jpg" alt="Item image" />
								</div>
								<div class="_column product-info">
									<h4 class="product-name">${itemincart.item.name}</h4>
									<p class="product-desc">${itemincart.item.info}</p>
									<div class="price product-single-price">
										<fmt:formatNumber pattern="###,###"
											value="${itemincart.item.price}" type="currency" />
										đ
									</div>
								</div>
								<div class="_column product-modifiers"
									data-product-price="{{=price}}">
									<div class="_grid">
										<a href="decrease?item_id=${itemincart.item.item_id}"><button
												class="_btn _column product-subtract">&minus;</button></a>
										<div class="_column product-qty">${itemincart.quantity}
										</div>
										<a href="increase?item_id=${itemincart.item.item_id}">
											<button class="_btn _column product-plus">&plus;</button>
										</a>
									</div>
									<a href="/deleteitemincart?id=${itemincart.item.item_id}"><button class="_btn entypo-trash product-remove">Xóa</button></a>
									<div class="price product-total-price"
										style="text-align: center;">
										<fmt:formatNumber pattern="###,###"
											value="${itemincart.item.price*itemincart.quantity}"
											type="currency" />
										đ
									</div>
								</div>
							</li>
						</c:forEach>
			</ol>
			<footer class="_grid cart-totals">
				<div class="_column subtotal" id="subtotalCtr">
					<div class="cart-totals-key">Tổng giá sản phẩm</div>
					<div class="cart-totals-value">
						<fmt:formatNumber pattern="###,###" value="${total}"
							type="currency" />
						đ
					</div>
				</div>
				<div class="_column shipping" id="shippingCtr">
					<div class="cart-totals-key">Phí vận chuyển</div>
					<div class="cart-totals-value">0 đ</div>
				</div>
				<div class="_column taxes" id="taxesCtr">
					<div class="cart-totals-key">Thuế (10%)</div>
					<div class="cart-totals-value">
						<fmt:formatNumber pattern="###,###" value="${tax}" type="currency" />
						đ
					</div>
				</div>
				<div class="_column total" id="totalCtr">
					<div class="cart-totals-key">Tổng cộng</div>
					<div class="cart-totals-value">
						<fmt:formatNumber pattern="###,###" value="${sum}" type="currency" />
						đ
					</div>
				</div>
				<div class="_column checkout">
				<a href="/checkout">
					<button class="_btn checkout-btn entypo-forward">Checkout</button>					
				</a>
				</div>
	</div>

	</footer>

	</section>
	</div>
	</c:when>
	<c:otherwise>
		<h2 align=center>Chưa có sản phẩm nào trong giỏ hàng</h2>
	</c:otherwise>
	</c:choose>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/zepto/1.0/zepto.min.js'></script>

	<script src="js/cart/index.js"></script>

</body>
</html>
