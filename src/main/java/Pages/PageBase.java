package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected WebDriver driver;

    public PageBase(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);    }


    public void ClosePopUp(){
        // Handle the sign-in popup if it appears
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@aria-label='Dismiss sign in information.']")));
            closePopup.click();
        } catch (TimeoutException e) {
            System.out.println("Popup not found, continuing.");
        }

    }
}
