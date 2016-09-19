package Login;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Successful_login_firefox {
  private WebDriver driver;
  private String website = "https://www.google.ie//?gws_rd=cr,ssl&ei=Sd7ZV5jOD-HEgAb376HQDQ";
  private String emailAddress = "justanotherapplicant@gmail.com";
  private String emailAddressElement = "Email";
  private String password = "joboffer";
  private String passwordElement = "Passwd";
  private String nextButton = "next";
  private String signInButton = "signIn";
  private String accountInfo ="span.gb_7a.gbii";
  private String logInButton ="gb_70";

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
    driver.findElement(By.name(emailAddressElement)).sendKeys(emailAddress);
    driver.findElement(By.name(emailAddressElement)).clear();
    driver.findElement(By.name(emailAddressElement)).sendKeys(emailAddress);    
    driver.findElement(By.id(nextButton)).click();
    Thread.sleep(2000);
    driver.findElement(By.name(passwordElement)).click();
    driver.findElement(By.name(passwordElement)).clear();
    driver.findElement(By.name(passwordElement)).sendKeys(password );
    driver.findElement(By.name(passwordElement)).clear();
    driver.findElement(By.name(passwordElement)).sendKeys(password );
    driver.findElement(By.id(signInButton)).click(); 
    Thread.sleep(9000);
    driver.findElement(By.cssSelector(accountInfo)).click();
    String login =driver.findElement(By.name(emailAddressElement)).getText();    
    
    // Warning: assertTextPresent may require manual changes
    Assert.assertTrue(login.contains(emailAddress));
  }
  }
