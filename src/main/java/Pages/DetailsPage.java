package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DetailsPage extends PageBase {

    public DetailsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "hprt_nos_select_78883120_386871369_0_33_0_131741")
    WebElement AmountElement;

    @FindBy(xpath = "//h2[text()='Tolip Hotel Alexandria']")
    WebElement HotelName;

    @FindBy(xpath = "//input[@type='radio' and @value='1' and @name='bedPreference_78883120']")
    WebElement SingleBeds;

    @FindBy(xpath = "//input[@type='radio' and @value='2' and @name='bedPreference_786871369']")
    WebElement LargeDoubleBed;

    @FindBy(xpath = "//span[normalize-space(text())='Special Offer - Deluxe Room with City View - Egyptians and Residents Only']")
    WebElement label;

    public void selectAmount(String Amount) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(AmountElement));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AmountElement);
        Select amountSelect = new Select(AmountElement);
        int index = Integer.parseInt(Amount);
        amountSelect.selectByIndex(index);

        System.out.println("Selected amount: " + index);
    }

    public void selectbeds(String beds) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", label);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        if (beds.equalsIgnoreCase("2 single beds")) {
            SingleBeds.click();
            System.out.println("2 single beds");
        } else {
            LargeDoubleBed.click();
            System.out.println("1 large double bed");
        }
    }

    public String wholeSelectFunc(String Amount, String beds) {
        String originalWindow = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Keep increased timeout
        wait.until(driver -> driver.getWindowHandles().size() > 1);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String name = HotelName.getText();

        selectbeds(beds);
        selectAmount(Amount);

        // Locator for the spinner element inside the button
        By spinnerLocator = By.cssSelector("button[data-popover-content-id='submit_cta_holder_button_tooltip'] .bui-button__loader .bui-spinner");
        // Using the ID as the primary locator for the reserve button, as previously discussed
        By reserveButtonBy = By.cssSelector("button.txp-bui-main-pp.bui-button--primary.js-reservation-button");

        try {
            System.out.println("Waiting for spinner to disappear...");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
            System.out.println("Spinner disappeared.");
        } catch (TimeoutException e) {
            System.out.println("Spinner did not disappear within the timeout. Proceeding with button click anyway.");
            // Log this or handle as a potential issue, but do not re-throw here
        }

        WebElement finalReserveBtn = null; // Declare outside try-catch to be accessible
        try {
            // First, wait for the button to be visible AND clickable
            System.out.println("Attempting to make button clickable...");
            finalReserveBtn = wait.until(ExpectedConditions.elementToBeClickable(reserveButtonBy));

            // --- ADDED LINE: Scroll to the button before clicking ---
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalReserveBtn);
            System.out.println("Scrolled to the reserve button.");
            // -----------------------------------------------------------

            finalReserveBtn.click();
            System.out.println("Clicked on reservation button (standard click).");
        } catch (TimeoutException e) {
            System.out.println("Button still not clickable via standard Selenium click. Trying JavaScript click...");
            try {
                // If elementToBeClickable fails, explicitly wait for its presence before attempting JavaScript click.
                // This handles cases where the element might briefly disappear or become stale.
                finalReserveBtn = wait.until(ExpectedConditions.presenceOfElementLocated(reserveButtonBy));

                // --- ADDED LINE: Scroll to the button before JavaScript clicking ---
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", finalReserveBtn);
                System.out.println("Scrolled to the reserve button for JavaScript click.");
                // ---------------------------------------------------------------------

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", finalReserveBtn);
                System.out.println("Clicked on reservation button using JavaScript.");
            } catch (Exception jsClickException) {
                System.err.println("Failed to click using JavaScript as well: " + jsClickException.getMessage());
                // Re-throw the exception to mark the test as failed if both attempts fail
                throw new RuntimeException("Failed to click reservation button after multiple attempts.", jsClickException);
            }
        }

   return name; }
}