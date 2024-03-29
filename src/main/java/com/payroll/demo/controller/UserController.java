package com.payroll.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.payroll.demo.service.UserService;

@RestController
@CrossOrigin
public class UserController {

	@Autowired
	UserService userservice;

	@GetMapping("user/{username}/{password}")
	public int UserLogin(@PathVariable("username") String username1, @PathVariable("password") String password1) {

		int flag = userservice.loginValidation(username1, password1);
		if (flag == 0) {
			return 0;
		}

		return flag;

	}
			
	}

