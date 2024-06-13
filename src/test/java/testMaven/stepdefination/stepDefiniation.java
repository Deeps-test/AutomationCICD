package testMaven.stepdefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testMaven.PageObjects.CartPage;
import testMaven.PageObjects.CheckOutPage;
import testMaven.PageObjects.ConfirmationPage;
import testMaven.PageObjects.LandingPage;
import testMaven.PageObjects.ProductCatalogue;
import testMaven.TestComponents.BaseTest;

public class stepDefiniation extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage  confirmationPage;
	@Given("I landed on Ecommerce page")
	public  void I_landed_on_Ecommerce_page() throws IOException
	{
		landingPage=LaunchApplication();
		
	}

	@Given("^login with USername (.+) and Password (.+)$")
	public  void logged_in_username_and_Password(String username ,String password){
	
		 productCatalogue = landingPage.LoginApplication(username,password);
	 
	}
	
	@When("^I add product(.+) to cart$")
	public  void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productCatalogue.getproductList();
		productCatalogue.addProuctToCart(productName);
		
	}
	
	@When("^Checkout (.+) and submit the Order $")
	public  void Checkout_product_and_submit_the_order(String productName)
	
	{
 cartPage = productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage checkOutPage= cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
	confirmationPage =checkOutPage.goToFinalCheckOut();
		
	}
	//"THANKYOU FOR THE ORDER." message is displayed on confirmation Page
	@Then("{string}  message is displayed on confirmation Page")
	
	public  void message_is_displayed_on_confirmation_Page(String string) {
		
		String confirmMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.quit();
	}
	
	}

