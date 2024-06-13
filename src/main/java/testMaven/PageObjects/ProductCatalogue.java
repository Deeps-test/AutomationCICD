package testMaven.PageObjects;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import testMaven.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage =By.cssSelector("#toast-container");
	@FindBy(css=".ng-animating")
	WebElement spinner;
			
	public  List<WebElement> getproductList()
	{
		waitForElementToAppear(productsBy);
		return products;
		
	}
	 
	public WebElement getProductByName(String productName)
	{
		WebElement prod = getproductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
		return prod;

	}
	
	
	public void addProuctToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName);
		
				prod.findElement(addToCart).click();
				waitForElementToAppear(toastMessage);
				waitForElementToDisappear(spinner);
				Thread.sleep(9000);
			
	}
	
	
	
	
}
	