package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MakeService;


@Controller
public class MainController {
	@Autowired
	MakeService makeService;
	
	@RequestMapping("/test")
	public String test() {
		makeService.insertData();
		System.out.println("asdf");
		makeService.insertData2();
		return "test";
	}
	@RequestMapping("/abcd2")
    public @ResponseBody String abcd2() throws Exception {
        return "abcd2";
    }


}
