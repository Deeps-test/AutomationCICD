package testMaven.PageObjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testMaven.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)
	{ 
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	//WebElement userEmail= driver.finwaitForWebElementToAppeardElement(By.id("userEmail"));
	//driver.findElement(By.id("userPassword"))
	//driver.findElement(By.id("login"))
	//page Factory
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPasswordElement;
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue LoginApplication(String email, String password)
	{
		userEmail.sendKeys(email);
		userPasswordElement.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage()
	{
		
		waitForWebElementToAppear(errorMessage);
		
		return errorMessage.getText();
	}
	
}
