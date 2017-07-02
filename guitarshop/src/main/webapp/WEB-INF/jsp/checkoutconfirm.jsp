<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>A simple, clean, and responsive HTML invoice template</title>

<link rel='stylesheet' href='css/style.css'>
</head>

<body>
	<div class="invoice-box">
	<form:form action="/checkout/addorder" method="POST" modelAttribute="orderForm">
		<table cellpadding="0" cellspacing="0">
			<tr class="top">
				<td colspan="2">
					<table>
						<tr>
							<td class="title"><img src="/images/logo.png"
								style="width: 35%; max-width: 300px;"></td>

							<td><br> Ngày lập: ${dateshow}<br></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr class="information">
				<td colspan="2">
					<table>
						<tr>
							<td><b>Người nhận:</b> <i>${orderForm.receiver_name}</i><br>
								<b>Mã bưu chính:</b> <i>${orderForm.postal_code}</i><br> <b>Địa
									chỉ:</b> <i>${orderForm.delivery_address}</i><br> <b>SĐT:</b>
								<i>${orderForm.receiver_phone}</i></td>

							<td style="text-align: left; padding-left: 20%;"><b>Người
									dùng:</b> <i>${currentUser.username}</i><br> <b>Tên người
									dùng:</b> <i>${currentUser.firstname} ${currentUser.lastname}</i> <br>
								<b>Email:</b> <i>${currentUser.email}</i></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr class="heading">
				<td>Phương thức thanh toán</td>

				<td></td>
			</tr>

			<tr class="details">
				<td>${orderForm.payment_method}</td>

				<td></td>
			</tr>

			<tr class="heading">
				<td>Sản phẩm</td>

				<td>Giá</td>
			</tr>
			<c:forEach items="${itemsincart}" var="itemincart">
				<tr class="item">
					<td>${itemincart.item.name} ( ${itemincart.quantity}x <fmt:formatNumber
							pattern="###,###" value="${itemincart.item.price}"
							type="currency" /> đ )
					</td>

					<td><fmt:formatNumber pattern="###,###"
							value="${itemincart.item.price*itemincart.quantity}"
							type="currency" /> đ</td>
				</tr>
			</c:forEach>

			<tr class="item	last">
				<td>Thuế (10%)</td>

				<td><fmt:formatNumber pattern="###,###" value="${tax}"
						type="currency" /> đ</td>
			</tr>

			<tr class="total">
				<td></td>

				<td>Tổng tiền: <fmt:formatNumber pattern="###,###"
						value="${sum}" type="currency" /> đ
				</td>
			</tr>
			<tr>
			<td></td>
			<td><a href="/checkout"><button 
							class="btn btn-primary btn-block" type="button" align="left">
							Quay lại</button></a>
			
				<button
							class="btn btn-primary btn-block" type="submit" align="left">
							OK</button></td>
			</tr>	
		</table>
		</form:form>
	</div>
</body>
</html>