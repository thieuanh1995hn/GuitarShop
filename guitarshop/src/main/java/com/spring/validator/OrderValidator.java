package com.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.model.Order;


@Component
public class OrderValidator  implements Validator  {

	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return Order.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Order order = (Order) o;
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiver_name", "NotEmpty");
	    if (order.getReceiver_name().length() < 6 || order.getReceiver_name().length() > 32) {
	      errors.rejectValue("receiver_name", "Size.orderForm.delivery_name");
	    }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "delivery_address", "NotEmpty");
	    if (order.getDelivery_address().length() < 6 || order.getDelivery_address().length() > 100) {
	      errors.rejectValue("delivery_address", "Size.orderForm.delivery_address");
	    }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payment_method", "NotEmpty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "receiver_phone", "NotEmpty");
	
	}

}
