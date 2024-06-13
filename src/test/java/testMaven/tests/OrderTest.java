package testMaven.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import testMaven.PageObjects.CartPage;
import testMaven.PageObjects.CheckOutPage;
import testMaven.PageObjects.ConfirmationPage;
import testMaven.PageObjects.OrderPage;
import testMaven.PageObjects.ProductCatalogue;
import testMaven.TestComponents.BaseTest;

public class OrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	
	/*Without DataProvider
	@Test
	//public void submitOrder( ) throws IOException, InterruptedException{*/
	/*With DataProdiver
	@Test(dataProvider="getdata") add groups if required
	public void submitOrder( String email, String password, String productName) throws IOException, InterruptedException{*/
	//with HahMap
	@Test(dataProvider="getdata",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		

		
		//Without DataProvider
		//ProductCatalogue productCatalogue = landingPage.LoginApplication("corona-rebid-0d@icloud.com", "Asdf@123");
		//With DataProdiver 
		//ProductCatalogue productCatalogue = landingPage.LoginApplication(email,password);
		//with HahMap
		ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
		
		List<WebElement> products = productCatalogue.getproductList();
		productCatalogue.addProuctToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage= cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		ConfirmationPage  confirmationPage =checkOutPage.goToFinalCheckOut();
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}

@Test(dependsOnMethods= {"submitOrder"})
public void OrderHistoryTest()
{  
	
	ProductCatalogue productCatalogue = landingPage.LoginApplication("corona-rebid-0d@icloud.com", "Asdf@123");
	OrderPage orderPage = productCatalogue.goToOrdersPage();
	Assert.assertTrue(orderPage.VerifyProductDisplay(productName));
}


// withJson
@DataProvider
public Object[][] getdata() throws IOException
{ 
	List <HashMap<String ,String>> data =getJsonDatatoMap(System.getProperty("user.dir")+"//src//test//java//testMaven//data//PurchaseOrder.json");
	return new Object [][]  {{data.get(0)},{data.get(1)}};
}}
	

/*  //Withdataprovider
	 @DataProvider
	public Object[][] getdata()
	{
	
	return new Object [][]  {{"corona-rebid-0d@icloud.com","Asdf@123","ZARA COAT 3"},{"Sudhee@gmail.com","Qwer@123","ADIDAS ORIGINAL"}};
	}*/
//Method1

	/* @DataProvider
	public Object[][] getdata()
	{
	 *Object[][] data	= new Object [3][2];
	data[0][0]="FirstSetUserName";
	data[0][1]="Password1";
	data[1][0]="SecondSetUserName";
	data[1][1]="Password2";
	
	return data;}*/

	/*//HashMap  @DataProvider
	public Object[][] getdata()
	{
	HashMap<String,String> map= new HashMap<String,String> ();
	map.put ("email","corona-rebid-0d@icloud.com");
	map.put("password","Asdf@123");
	map.put("product", "ZARA COAT 3");
	

	HashMap<String,String> map1= new HashMap<String,String> ();
	map1.put ("email","Sudhee@gmail.com");
	map1.put("password","Qwer@123");
	map1.put("product", "ADIDAS ORIGINAL");
	
	return new Object [][]  {{map},{map1}};}*/
