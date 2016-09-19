package Login;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Unsuccessful_login_firefox {
  private WebDriver driver;
  private String website = "https://www.google.ie//?gws_rd=cr,ssl&ei=Sd7ZV5jOD-HEgAb376HQDQ";
  private String emailAddressElement = "Email";
  private String passwordElement = "Passwd";
  private String nextButton = "next";
  private String signInButton = "signIn";
  private String logInButton ="gb_70";
  private String matchFailureString ="^[\\s\\S]*Wrong password\\. Try again\\.[\\s\\S]*$";
  @Before
  public void setUp() throws Exception {
		driver = new FirefoxDriver();
	    driver.manage().window().maximize();    
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUnsuccessfulLogin() throws Exception {
    driver.get(website);
    driver.findElement(By.id(logInButton)).click();
    driver.findElement(By.name(emailAddressElement)).click();
    driver.findElement(By.name(emailAddressElement)).clear();
    driver.findElement(By.name(emailAddressElement)).sendKeys("email");
    driver.findElement(By.name(emailAddressElement)).clear();
    driver.findElement(By.name(emailAddressElement)).sendKeys("email");
    driver.findElement(By.id(nextButton)).click();
    driver.findElement(By.name(passwordElement)).click();
    driver.findElement(By.name(passwordElement)).clear();
    driver.findElement(By.name(passwordElement)).sendKeys("password");
    driver.findElement(By.name(passwordElement)).clear();
    driver.findElement(By.name(passwordElement)).sendKeys("password");
    driver.findElement(By.id(signInButton)).click(); 
    driver.close(); 
    
    // Warning: assertTextPresent may require manual changes
    assertTrue(driver.findElement(By.cssSelector("BODY")).getText().matches(matchFailureString));
  }
}