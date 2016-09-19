package Sort_table;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Sort_table_by_date {
	  private WebDriver driver;
	  private String iterator = "\"+i+\"";   
	  private String valueFromTableXpath = "/html/body/div[3]/div[2]/div[1]/div[1]/div/table/tbody/tr["+iterator+"]/td[3]";
	  private String website = "http://demos.telerik.com//aspnet-mvc//grid//sorting";
	  public String orderDate = "Order Date";
	  private String nextPageButton ="span.k-icon.k-i-arrow-e";
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
    driver.findElement(By.linkText(orderDate)).click();
    Thread.sleep(15000);
    
    //Put all the sorted values in to an array
    List<java.util.Date> sorteddatevaluesfromtable = new ArrayList<java.util.Date>();
    List<java.util.Date> datevaluesfromtable = new ArrayList<java.util.Date>();
    String s;  
    for(int t=0;t<166;t++)
    {
    //Get all the values on the next page and put them in the array.#Repeat	
    for(int i=1;i<6;i++)
    {
    	Thread.sleep(1000);    
    	s=driver.findElement(By.xpath(valueFromTableXpath)).getText().toString();
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");    
        java.util.Date date=sdf.parse(s);
        sorteddatevaluesfromtable.add(date);  	
        datevaluesfromtable.add(date);    	
    }
    //Click next page button
    driver.findElement(By.cssSelector(nextPageButton)).click();
    }
    
    //Sort one array  
    Collections.sort(sorteddatevaluesfromtable);
    
    //Create a count of matched arraylist elements
    int count=0;
    //Compare individual elements are identical
    for(int y=0;y<datevaluesfromtable.size();y++)
    {
    	if(datevaluesfromtable.get(y).equals(sorteddatevaluesfromtable.get(y)))
    	{
    		count++;
    	}
    }    
    Assert.assertTrue(sorteddatevaluesfromtable.size()==count);
    }
}