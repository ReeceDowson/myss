package com.reece.spring.datacontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class DataController {
	
	@RequestMapping("/jump.do")
	public String jump(){
		System.out.println("进入了Controller中的jump方法..");
		return "redirect:../cost/cost.html";
	}
}
