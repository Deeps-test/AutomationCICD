package testMaven.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testMaven.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	WebDriver driver;
	public CheckOutPage(WebDriver driver) 
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectCountry;
	
	By results= By.cssSelector(".ta-results.list-group.ng-star-inserted");
	By checkoutBtn =By.cssSelector("a.btnn.action__submit.ng-star-inserted");

	public void selectCountry (String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();

	}
	
	public ConfirmationPage goToFinalCheckOut()
	{
	waitForElementToAppear(checkoutBtn);
	submit.click();
	//ConfirmationPage  confirmationPage=new ConfirmationPage(driver);
	//return confirmationPage;
	return new ConfirmationPage(driver);
	}
}
