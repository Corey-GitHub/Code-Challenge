package Login;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Successful_login_chrome {
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
  private String accountInfoElement = "gb_ub";
  private String browserDriver = "webdriver.chrome.driver";
  private String browserDriverLocation = "C:\\Program Files (x86)\\Google Chrome\\chromedriver.exe";

  @Before
  public void setUp() throws Exception {
	System.setProperty(browserDriver, browserDriverLocation);
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--start-maximized");
	driver = new ChromeDriver(options);    
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
    driver.findElement(By.name(passwordElement)).sendKeys(password);
    driver.findElement(By.name(passwordElement)).clear();
    driver.findElement(By.name(passwordElement)).sendKeys(password);
    driver.findElement(By.id(signInButton)).click(); 
    Thread.sleep(15000);
    driver.findElement(By.cssSelector(accountInfo)).click();
    Thread.sleep(15000);
    String login =driver.findElement(By.className(accountInfoElement)).getText();
       
    // Warning: assertTextPresent may require manual changes
    Assert.assertTrue(login.contains(emailAddress));
  }
}