import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.List;
//remember to handle sign in pop up
public class New {
  //  @FindBy(xpath = "//*[@alt='Tolip Hotel Alexandria']")
    WebElement TolipImage;

//    @FindBy(xpath = "//*[@id=\"bodyconstraint-inner\"]/div/div/div[2]/div[3]/div[2]/div[2]/div[3]/div[7]/div[1]/div[2]/div/div[2]/div[2]/div/div[2]/a")
  //  WebElement SeeAvailBtn;


   // @FindBy(xpath = "//button[@aria-label='Dismiss sign in information.']")
  //  WebElement ClosePopUp;

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
        WebElement search = driver.findElement(By.xpath("//button[@type='submit']\n"));
       // search.click();



        //location setting
        WebElement Location = driver.findElement(By.id(":rh:"));//done
        Location.sendKeys("Alexandria");
        //date setting
        WebElement startDate = driver.findElement(By.xpath("//button[@data-testid='searchbox-dates-container']\n")); //done
        startDate.click();
        String CheckInMonth = "October 2025";
        String CheckOutMonth = "December 2025";

        String StartDateVale = "1";
        String EndDateVale = "15";

        WebElement NextMonthButton = driver.findElement(By.xpath("//button[@aria-label='Next month']"));
        while(true){
            WebElement monthEl = driver.findElement(By.xpath("//h3[@aria-live='polite']"));

            String month = monthEl.getText();
            System.out.println(month);
            if(CheckInMonth.equals(month)){
            System.out.println("found");
            break;
            }
            else
            {
                NextMonthButton.click();
            }}
            List<WebElement> ele = driver.findElements(By.xpath("(//tbody)[1]//tr/td/span"));
            String eleText;
            for (WebElement element:ele){
            eleText= element.getText();
            if(eleText.equals(StartDateVale)){
                System.out.println("found start date");
                element.click();
                break;
            }

            else
            {
                System.out.println(eleText);

            }}
            //loop for checkout date month
            while(true){
                    WebElement monthEl = driver.findElement(By.xpath("//h3[@aria-live='polite']"));

                    String month = monthEl.getText();
                    System.out.println(month);
                    if(CheckOutMonth.equals(month)){
                        System.out.println("found");
                        break;
                    }
                    else
                    {
                        NextMonthButton.click();
                    }}
//loop for checkout date day
       // WebElement month2=driver.findElement()
        List<WebElement> ele2 = driver.findElements(By.xpath("(//tbody)[1]//tr/td/span"));

        for (WebElement element2:ele2){
                    eleText= element2.getText();
                    if(eleText.equals(EndDateVale)){
                        System.out.println("found end date");
                        element2.click();
                        break;
                    }

                    else
                    {
                        System.out.println(eleText);

                    }

        }

        search.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement TolipImage = driver.findElement(By.xpath("//*[@alt='Tolip Hotel Alexandria']"));

        WebElement SeeAvailBtn = driver.findElement(By.xpath("//a[contains(@href, 'royal-tulip-alexandria') and contains(@data-testid, 'availability')]"));

        // Scroll to the hotel image
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", TolipImage);

// Handle the sign-in popup if it appears
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Dismiss sign in information.']")));
            closePopup.click();
        } catch (TimeoutException e) {
            System.out.println("Popup not found, continuing.");
        }

// Wait for See Availability button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(SeeAvailBtn));

// Option 1: Try normal click
        try {
            SeeAvailBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element not clickable, using JavaScript click as fallback.");
            // Option 2: Use JavaScript click as a fallback
            jse.executeScript("arguments[0].click();", SeeAvailBtn);
        }


    }}



