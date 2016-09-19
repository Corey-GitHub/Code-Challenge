package Hidden_element;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import java.util.List;
import org.openqa.selenium.*;	
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demonstrate_hidden_element {
  private WebDriver driver;
  private String website = "http://www.w3schools.com/jquery/tryit.asp?filename=tryjquery_eff_show_hide";
  private String jscode = "<a href=\"javascript:hideshow(document.getElementById('adiv'))\">Click here</a>\n \n<script type=\"text/javascript\">\nfunction hideshow(which){\nif (!document.getElementById)\nreturn\nif (which.style.display==\"block\")\nwhich.style.display=\"none\"\nelse\nwhich.style.display=\"block\"\n}\n</script>\n \n<div id=\"adiv\" style=\"font:24px bold; display: block\">Now you see me</div>";
  private String runitXpath = "//button[@onclick='submitTryit(1)']";
  private String getHiddenText = "return document.getElementById('adiv').innerHTML";
  private String selectHiddenText = "html body div#adiv";
  private String hiddenText = "Now you see me";
  private String hideLink = "Click here";
  private String editTextArea = "textareaCode";
  private String resultFrame = "iframeResult";
  
  @Before
  public void setUp() throws Exception {
		driver = new FirefoxDriver();
	    driver.manage().window().maximize();	    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
  public void testUnsuccessfulLogin() throws Exception {
	    //Got to the javascript editor on w3schools 
	    driver.get(website);
	    
	    //Clear the text area and enter in some javascript code then run it
	    driver.findElement(By.id(editTextArea)).clear();
	    driver.findElement(By.id(editTextArea)).sendKeys("");
	    driver.findElement(By.id(editTextArea)).clear();
	    driver.findElement(By.id(editTextArea)).sendKeys("");
	    driver.findElement(By.id(editTextArea)).clear();
	    driver.findElement(By.id(editTextArea)).sendKeys(jscode);
	    driver.findElement(By.id(editTextArea)).clear();
	    driver.findElement(By.id(editTextArea)).sendKeys(jscode);
	    driver.findElement(By.xpath(runitXpath)).click();
	    driver.findElement(By.xpath(runitXpath)).click();
	    
	    //Switch to the frame with the results in it
	    driver.switchTo().frame(resultFrame);
	    
	    //Create a list containing all elements
	    List<WebElement> no = driver.findElements(By.tagName("a"));	    
	    
	    for (WebElement pagelink : no)
	         {    	    
    	    
	    	//Create Javascript object to run Javascript code
    	    JavascriptExecutor js = (JavascriptExecutor)((RemoteWebElement) pagelink).getWrappedDriver();
    	    
    	    //If the link button is contained in this element then use that WebElement to demonstrate hidden elements
	    	if(pagelink.getText().contains(hideLink))
	    	{
	    		//Assert that text is present before hiding element	    		
	    		Assert.assertTrue(driver.findElement(By.cssSelector(selectHiddenText)).getText().toString().equals(hiddenText));
	    			    		
	    		//Wait for link element to become available
	    		WebDriverWait wait = new WebDriverWait(driver, 10);
	    		WebElement link = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(hideLink)));
	    		
	    		//Click link to cause text to become hidden
	    		link.click();
	    		
	    		//Assert that text is no longer accessible after being rendered hidden 
	    		Assert.assertTrue(driver.findElement(By.cssSelector(selectHiddenText)).getText().toString().isEmpty());
	    		
	    		//Assert that hiddentext has been accessed using JavascriptExecutor 	    		
	    		Assert.assertTrue(js.executeScript(getHiddenText, pagelink).toString().equals(hiddenText));
	    		    		
	    	}
	    	
	          }    

	      }

}
