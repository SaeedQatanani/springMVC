package com.saeed.countries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saeed.countries.services.ApiService;
@RestController
public class MainController {
	@Autowired
	ApiService apiServ;
	@RequestMapping("/1")
	public List<Object[]> index() {
		List<Object[]> table = apiServ.q1();
		return table;
	}
	@RequestMapping("/2")
	public List<Object[]> index2() {
		List<Object[]> table = apiServ.q2();
		return table;
	}
	@RequestMapping("/3")
	public List<Object[]> index3() {
		List<Object[]> table = apiServ.q3();
		return table;
	}
	@RequestMapping("/4")
	public List<Object[]> index4() {
		List<Object[]> table = apiServ.q4();
		return table;
	}
	@RequestMapping("/5")
	public List<Object[]> index5() {
		List<Object[]> table = apiServ.q5();
		return table;
	}
	@RequestMapping("/6")
	public List<Object[]> index6() {
		List<Object[]> table = apiServ.q6();
		return table;
	}
	@RequestMapping("/7")
	public List<Object[]> index7() {
		List<Object[]> table = apiServ.q7();
		return table;
	}
	@RequestMapping("/8")
	public List<Object[]> index8() {
		List<Object[]> table = apiServ.q8();
		return table;
	}
}
