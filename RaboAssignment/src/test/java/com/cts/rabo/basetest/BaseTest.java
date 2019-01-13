package com.cts.rabo.basetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


@ContextConfiguration({ "file:src/test/resources/config/rabo-servlet.xml" })
@WebAppConfiguration
public abstract class BaseTest {
	
	protected MockMvc mockmvc;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;

}
