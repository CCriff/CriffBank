package com.criff.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.criff.models.Account;
import com.criff.models.User;

public class JunitTests {

	/************************************Users*************************************************/    
    
    User user = new User(100, "Curtis", "Criff", "curtis", "criff");

    /************************************Users*************************************************/
    @Test
    public void userId_test(){

        assertEquals(100, user.getId());

    }
    
    @Test
    public void firstname_test(){

        assertEquals("Curtis", user.getFirstName());

    }

    @Test
    public void lastname_test(){

        assertEquals("Criff", user.getLastName());

    }
    
    @Test
    public void username_test(){

        assertEquals("curtis", user.getEmail());

    }
    
    @Test
    public void pwHash_test(){

        assertEquals("criff", user.getPass_hash());

    }
    
    @Test
    public void setUserId_test(){

        user.setId(15);
        assertEquals(15, user.getId());

    }

    @Test
    public void setFirstName_test(){


        user.setFirstName("Shleana");
        assertEquals("Shleana", user.getFirstName());
    }
    
    @Test
    public void setLastName_test(){


        user.setLastName("Peoples");
        assertEquals("Peoples", user.getLastName());
    }

    @Test
    public void setUserName_test(){


        user.setEmail("shleana");
        assertEquals("shleana", user.getEmail());
    }

    @Test
    public void setPwHash_test(){


        user.setPass_hash("shleana");
        assertEquals("shleana", user.getPass_hash());
    }
    
    /************************************Accounts*************************************************/
    
    Account account = new Account(12, "Savings", "USD", 12000, false);
    
    /************************************Accounts*************************************************/
    
    @Test
    public void accountId_test(){

        assertEquals(12, account.getAcctID());

    }
    
    @Test
    public void acctType_test(){

        assertEquals("Savings", account.getType());

    }

    @Test
    public void currency_test(){

        assertEquals("USD", account.getCurrency());

    }
    
    @Test
    public void acctBalance_test(){

        
        assertEquals(12000, account.getBalance(), 0);

    }
    
    @Test
    public void acctStatus_test(){

        assertFalse(account.isAcctStatus());

    }
    
    @Test
    public void setAcctId_test(){

        account.setAcctID(25);
        assertEquals(25, account.getAcctID());

    }

    @Test
    public void setAcctType_test(){


        account.setType("Checking");
        assertEquals("Checking", account.getType());
    }
    
    @Test
    public void setCurrency_test(){


        account.setCurrency("USD");
        assertEquals("USD", account.getCurrency());
    }

    @Test
    public void setBalance_test(){


        account.setBalance(14000);
        assertEquals(14000, account.getBalance(), 0.0);
    }

    @Test
    public void setStatus_test(){


        account.setAcctStatus(true);
        assertTrue(account.isAcctStatus());
    }

    

}
