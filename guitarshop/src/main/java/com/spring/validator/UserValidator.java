package com.spring.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spring.dao.UserDao;
import com.spring.model.User;


@Component
public class UserValidator implements Validator  {
	@Autowired
	 private UserDao userDao;
	@Override
	public boolean supports(Class<?> aClass) {
		// TODO Auto-generated method stub
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
	    if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
	      errors.rejectValue("username", "Size.userForm.username");
	    }
	    if (userDao.getUserByUserName(user.getUsername()) != null) {
	      errors.rejectValue("username", "Duplicate.userForm.username");
	    }

	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
	    if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
	      errors.rejectValue("password", "Size.userForm.password");
	    }
	    if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phonenumber", "NotEmpty");
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalcode", "NotEmpty");
	    if (user.getPostalcode().length() < 5 || user.getPostalcode().length() > 7) {
		      errors.rejectValue("postalcode", "Size.userForm.postalcode");
		    }
	    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");

	}
	

}
