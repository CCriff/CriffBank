package com.criff.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.criff.repository.UserDaoImpl;
import com.criff.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
	
	@Mock
	public UserDaoImpl userDaoImpl;
	
	@InjectMocks
	public static UserService userService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userService = new UserService();
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUser() {
	
	}

	@Test
	public void testUserLogin() {
		
	}

	@Test
	public void testLogout() {
		
	}

	@Test
	public void testDisplayAccts() {
		
	}

}
