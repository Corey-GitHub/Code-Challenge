package Parse_string;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;

public class Parse_string {
  private WebDriver driver;
  private String browserDriver = "webdriver.ie.driver";
  private String browserDriverLocation = "C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe";
  private String website = "http://demos.telerik.com//aspnet-mvc//datetimepicker//index";
  @Before
  public void setUp() throws Exception {
	System.setProperty(browserDriver, browserDriverLocation);
	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	capabilities.setCapability("requireWindowFocus", true);
	driver = new InternetExplorerDriver();   
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCheckTableSort() throws Exception {    
    WebDriver driver = new InternetExplorerDriver();
    driver.get(website);
    WebElement element = driver.findElement(By.id("datetimepicker"));
    String elementval = element.getAttribute("value");    
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.ENGLISH);    
    java.util.Date date=sdf.parse(elementval);
    System.out.println(date);
    Thread.sleep(9000);
  }
}