package com.beans;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;

import com.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{
	
	   @Test(groups= {"ErrorHandling"})
	   public void submitOrder() throws IOException {
		   
		lp.LoginApplication("ladysss@gmail.com", "Lady@123");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
		System.out.println(lp.getErrorMessage());
	}

}
