package com.rohanbojja.greeze;

import com.rohanbojja.greeze.models.User;
import com.rohanbojja.greeze.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
class GreezeApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}
}
