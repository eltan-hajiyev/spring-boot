package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.first.model.UserFirst;
import com.example.first.repository.UserFirstRepository;
import com.example.second.model.UserSecond;
import com.example.second.repository.UserSecondRepository;

/**
 * @author Elten Hajiyev
 */
@RequestMapping("/url")
@Controller
public class MyController {
	@Autowired
	UserFirstRepository ur1;
	@Autowired
	UserSecondRepository ur2;

	@ResponseBody
	@RequestMapping("/url1")
	public String getVal() {
		UserFirst u1 = new UserFirst();
		u1.setName("user 1");
		ur1.save(u1);

		UserSecond u2 = new UserSecond();
		u2.setName("user 2");
		ur2.save(u2);

		return "Hello";
	}
}
