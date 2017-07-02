<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/bootstrap.min.css" rel="stylesheet" />
<link href="/css/common.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<form:form modelAttribute="orderForm"
					class="col-md-offset-2 col-md-8">
					<h2 class="form-signin-heading" align="center">TẠO ĐƠN HÀNG</h2>
					<h4 class="text-center">
						<a href="/home">Home </a>
					</h4>
					<spring:bind path="receiver_name">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="receiver_name">Người nhận:</label>
							<form:input path="receiver_name" autofocus="true"
								class="form-control" type="text" id="receiver_name"/>
							<form:errors path="receiver_name"></form:errors>
						</div>
					</spring:bind>
					
					<spring:bind path="postal_code">
						<label for="postal_code">Mã bưu chính:</label>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							
							<form:input path="postal_code" autofocus="true"
								class="form-control" type="text" id="postal_code" />
							<form:errors path="postal_code"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="delivery_address">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="delivery_address">Địa chỉ nhận hàng:</label>
							<form:input path="delivery_address" autofocus="true"
								class="form-control" type="text" id="delivery_address" />
							<form:errors path="delivery_address"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="payment_method">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="payment_method">Phương thức thanh toán:</label>
							<form:select path="payment_method" id="payment_method" class="form-control">
								<form:option value="COD">COD(Thanh toán khi nhận hàng)</form:option>
								<form:option value="BANK">Chuyển khoản ngân hàng</form:option>
							</form:select>
							<form:errors path="postal_code"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="receiver_phone">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="receiver_phone">Số điện thoại liên hệ:</label>
							<form:input path="receiver_phone" class="form-control" type="text" id="receiver_phone" />
							<form:errors path="receiver_phone"></form:errors>
						</div>
					</spring:bind>
					<button class="btn btn-lg btn-primary btn-block" type="button" onclick="fillForm();">Sử dụng thông tin tài khoản</button>
					<button class="btn btn-lg btn-primary btn-block" type="submit">OK</button>
				</form:form>
			</div>
		</div>
	</div>
		<!-- CheckOutJs -->
	<script src="js/checkout.js"></script>
	<script type="text/javascript">
	function fillForm(){
		   document.getElementById("receiver_name").value="${currentUser.firstname} ${currentUser.lastname}";
		   document.getElementById("postal_code").value="${currentUser.postalcode}";
		   document.getElementById("delivery_address").value="${currentUser.address}";
		   document.getElementById("receiver_phone").value="${currentUser.phonenumber}";
	}
	</script>
	
</body>
</html>