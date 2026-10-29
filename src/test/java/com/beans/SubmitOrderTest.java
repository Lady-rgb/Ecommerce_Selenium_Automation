package com.beans;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import com.TestComponents.BaseTest;
import com.pageobjects.CartPage;
import com.pageobjects.CheckoutPage;
import com.pageobjects.ConfirmationPage;
import com.pageobjects.OrderPage;
import com.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData")
	   public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
				
		ProductCatalogue pc= lp.LoginApplication(input.get("email"),input.get("password"));
		pc.getProductList();
		pc.addProductToCart(productName);
		CartPage cp = pc.goToCartPage();
		
		Boolean match= cp.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage cop=cp.goToCheckOut();
		cop.selectCountry("India");
		ConfirmationPage conp= cop.submitOrder();
		
		String confirmMessage = conp.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println(confirmMessage);


	}

	
	@Test(dependsOnMethods= {"submitOrder"})
	public void order()
	{
		ProductCatalogue pc= lp.LoginApplication("ladygaga@gmail.com", "Lady@123");
		OrderPage op=pc.goToOrderPage();
		Assert.assertTrue(op.VerifyOrderDisplay(productName));		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data=  getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//com//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}
