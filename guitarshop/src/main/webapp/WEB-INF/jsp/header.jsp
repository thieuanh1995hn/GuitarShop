<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="fh5co-nav" role="navigation">
	<span>---></span> <span style="float: right;"> <--- </span>
	<div class="container">
		<div class="row">
			<div class="col-md-3 col-xs-2">
				<div id="fh5co-logo">
					<a href="/home">V-Guitar</a>
				</div>
			</div>
			<div class="col-md-6 col-xs-6 text-center menu-1">
				<ul>
					<li class="has-dropdown"><a href="/items?v=0&p=1&size=6">Cửa
							hàng</a>
						<ul class="dropdown">
							<c:forEach items="${categories}" var="category">
								<li><a href="/items/${category.category_id}?v=0&p=1&size=6">${category.name}</a></li>
							</c:forEach>
						</ul></li>

					<li class="has-dropdown"><a href="#">Lớp học</a>
						<ul class="dropdown" style="width: 220px;">
							<li><a href="#">Lớp guitar cổ điển</a></li>
							<li><a href="#">Lớp guitar điện</a></li>
							<li><a href="#">Lớp piano</a></li>
							<li><a href="#">Lớp violin</a></li>
						</ul></li>
					<li><a href="/about">Giới thiệu</a></li>
					<li><a href="/contact">Liên hệ</a></li>
				</ul>
			</div>
			<div class="col-md-3 col-xs-4 text-right hidden-xs menu-2">
				<ul>
					<li class="search">
						<form action="/searchresult#fsearch" method="get">
							<div class="input-group">
								<input type="hidden" name="v" value="0" /> <input type="hidden"
									name="p" value="1"> <input type="hidden" name="size"
									value="6"> <input name="search" id="inputsearch"
									type="text" placeholder="Search.."> <span
									class="input-group-btn">
									<button id="buttonsearch" class="btn btn-primary" type="button"
										onclick='this.form.submit()'>
										<i class="icon-search"></i>
									</button>
								</span>

							</div>
						</form>

					</li>

					<li class="shopping-cart"><a href="/cart" class="cart"><span><small>${numberincart}</small><i
								class="icon-shopping-cart"></i></span></a></li>

				</ul>

			</div>
			<div id="loginline" style="float: right;">
			<a href="/manager" ><span><i class="icon-user"></i></span></a>
			<c:choose>
				
				<c:when test="${not empty pageContext.request.userPrincipal.name}">
					<span style="text-transform: inherit; font-size: 12px;"> Xin chào
					<a href="/manager"><u>${pageContext.request.userPrincipal.name} !</u></a> </span>
				</c:when>
				<c:otherwise>
					<span style="text-transform: inherit; font-size: 12px;">Bạn
						chưa đăng nhập </span>
				</c:otherwise>
			</c:choose>
			<a href="/login" style="text-transform: inherit; font-size: 12px;"><u>Đăng nhập</u></a> 
			<a href="/logout"style="text-transform: inherit; font-size: 12px;"><u>Đăng xuất</u></a>
			<a href="/registration"style="text-transform: inherit; font-size: 12px;"><u>Đăng ký</u></a>
			
			</div>
		</div>

	</div>
</nav>