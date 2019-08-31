package com.example.demo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.mybatis.dao.primary.ApiDao;
import com.example.demo.mybatis.dao.second.SecondApiDao;
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

	@Autowired
	private ApiDao apiDao;
	
	@Autowired
	private SecondApiDao secondApiDao;

	@GetMapping(path = "/m")
	public @ResponseBody String helloWorld() {
		return String.format("%s %s", apiDao.selectName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}
	
	@GetMapping(path = "/mm")
	public @ResponseBody String mm() {
		return String.format("%s %s", secondApiDao.selectName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	}
}
