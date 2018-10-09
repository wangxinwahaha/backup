/* 
 * @(#)w.java
 *
 * Copyright 2018, 重庆华山智安科技有限公司保留.
 */
package com.example.demo.ctrl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	private static final Log logger = LogFactory.getLog(HelloController.class);

	@RequestMapping(value = "/hello111111", method = RequestMethod.GET)
	public String hello() {
		return "customer2";
	}

    @PostMapping(value = "/user")
    public String addUser( @RequestBody User user){
        return user.toString();
    }
    
    @PostMapping(value = "/error")
    public String testError( @RequestBody User user) throws Exception{
        throw new Exception("测试失败");
    }
	
}