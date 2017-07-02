package com.spring.service;

import com.spring.model.User;

public interface SecurityService {
	  public String findLoggedInUsername();
	  public User findLoggedInUser();
	  public void autoLogin(String username, String password);
}
