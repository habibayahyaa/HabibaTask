package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

public class SearchResults extends PageBase {

    public SearchResults(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@alt='Tolip Hotel Alexandria']")
    WebElement TolipImage;

    @FindBy(xpath = "//a[contains(@href, 'royal-tulip-alexandria') and contains(@data-testid, 'availability')]")
    WebElement SeeAvailBtn;


    @FindBy(xpath = "//button[@aria-label='Dismiss sign in information.']")
    WebElement ClosePopUp;






   // }
    public void SearchForTuplip ()
    {

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", TolipImage);

// Handle the sign-in popup if it appears
       super.ClosePopUp();

// Wait for See Availability button to be clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        wait.until(ExpectedConditions.elementToBeClickable(SeeAvailBtn));

// Option 1: Try normal click
        try {
            SeeAvailBtn.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element not clickable, using JavaScript click as fallback.");
            // Option 2: Use JavaScript click as a fallback
            jse.executeScript("arguments[0].click();", SeeAvailBtn);
        }



    }


}
