package com.criff.Tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.criff.models.Account;
import com.criff.repository.AccountsDaoImpl;
import com.criff.services.AccountService;

import junit.framework.Assert;


public class AccountServiceTests {
	
	@Mock
	public AccountsDaoImpl accountDaoImpl;
	
	@InjectMocks
	public static AccountService accountService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		accountService = new AccountService();
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testViewSingleAccount() {
		Mockito.when(accountDaoImpl.getSingleAccount(4)).thenReturn(
				Arrays.asList(
						new Account(4, "Checking", "USD", 10000, true)					
						)
			);
	}

	@Test
	public void testViewAllAccounts() {
		Mockito.when(accountDaoImpl.getAllAccounts(4)).thenReturn(
				Arrays.asList(
						new Account(1, "Savings", "USD", 12000, true),
						new Account(2, "Checking", "USD", 10000, false),
						new Account(3, "Savings", "USD", 8000, true),
						new Account(4, "Checking", "USD", 6000, false)
						)
			);
	}
	
	


}
