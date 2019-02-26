package com.netcracker.ncedu.tlt.dimi1.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping("/tttt")
	public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name) {
		return "Hello, " + name + "!";
	}

}
