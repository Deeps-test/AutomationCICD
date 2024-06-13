package testMaven.tests;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Assert;


import testMaven.PageObjects.CartPage;
import testMaven.PageObjects.ProductCatalogue;
import testMaven.TestComponents.BaseTest;
import testMaven.TestComponents.Retry;

public class ErrorValidation  extends BaseTest{
	@Test(groups= {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		//String productName = "ZARA COAT 3";
		
		 landingPage.LoginApplication("corona-rebid-0d@icloud.com", "Ahhhhhf@123");
		AssertJUnit.assertEquals("Incorrect email or password./",landingPage.getErrorMessage() );
	}

	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException{
			String productName = "ZARA COAT 3";
			
			ProductCatalogue productCatalogue = landingPage.LoginApplication("corona-rebid-0d@icloud.com", "Asdf@123");
			
			//List<WebElement> products = productCatalogue.getproductList();
			productCatalogue.addProuctToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);
			
		}
}
