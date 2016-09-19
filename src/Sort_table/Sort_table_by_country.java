package Sort_table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Sort_table_by_country {
	  private WebDriver driver;
	  private String iterator = "\"+i+\"";   
	  private String valueFromTableXpath = "/html/body/div[3]/div[2]/div[1]/div[1]/div/table/tbody/tr["+iterator+"]/td[1]";
      private String nextPageButton ="span.k-icon.k-i-arrow-e";
      private String website = "http://demos.telerik.com//aspnet-mvc//grid//sorting";
      private String shipCountry = "Ship Country";
  @Before
  public void setUp() throws Exception {
		driver = new FirefoxDriver();
	    driver.manage().window().maximize();	    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCheckTableSort() throws Exception {
        
    //Go to page with grid
    driver.get(website);      
    Thread.sleep(9000);
    
    //Sort by country asc
    driver.findElement(By.linkText(shipCountry)).click();
    Thread.sleep(15000);
    
    //Put all the sorted values in to an array
    ArrayList<String> valuesfromtable = new ArrayList<String>();
    ArrayList<String> sortedvaluesfromtable = new ArrayList<String>();
    for(int t=0;t<166;t++)
    {
    //Get all the values on the next page and put them in the array.#Repeat	
    for(int i=1;i<6;i++)
    {
       	Thread.sleep(1000);
    	valuesfromtable.add(driver.findElement(By.xpath(valueFromTableXpath)).getText().toString());
    	sortedvaluesfromtable.add(driver.findElement(By.xpath(valueFromTableXpath)).getText().toString());    	  	
    }
    //Click next page button
    driver.findElement(By.cssSelector(nextPageButton)).click();
    }
    
    //Sort one array
    Collections.sort(sortedvaluesfromtable);
    
    //Create a count of matched arraylist elements
    int count=0;
    //Compare individual elements are identical
    for(int y=0;y<valuesfromtable.size();y++)
    {
    	if(valuesfromtable.get(y).equals(sortedvaluesfromtable.get(y)))
    	{
    		count++;
    	}
    }    
    Assert.assertTrue(valuesfromtable.size()==count);    
  }
}