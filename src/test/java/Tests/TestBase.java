package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class TestBase {


    protected WebDriver driver;

@BeforeSuite
    public void StartDriver(){
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    driver.get("https://www.booking.com/");

}

//@AfterSuite
 //   public void Stopdriver()
//{
  //  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
   // driver.quit();
//}
}
