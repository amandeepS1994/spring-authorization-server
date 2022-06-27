package com.abidevel.oauth.authorization;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abidevel.oauth.authorization.configuration.authorization.TokenStoreConfiguration;

@SpringBootTest
public class AuthorizationApplicationTests {

	@Autowired
	protected TokenStoreConfiguration tokenStoreConfiguration;

	@Test
	void contextLoads() {

	}

}
