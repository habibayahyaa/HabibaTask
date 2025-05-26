package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.List;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy (id=":rh:")
    WebElement Location;

    @FindBy (xpath= "//button[@data-testid='searchbox-dates-container']\n")
    WebElement startDate;


    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;



   @FindBy(xpath="//button[@aria-label='Next month']")
    WebElement NextMonthButton;

   @FindBy(xpath="//h3[@aria-live='polite']")
   WebElement monthEl;

    @FindBy(xpath="(//tbody)[1]//tr/td/span")
     List<WebElement> ele;


   public String month;
   public String eleText;

    @FindBy(xpath="//h3[@aria-live='polite']")
    public WebElement monthEl2;

    @FindBy(xpath="(//tbody)[1]//tr/td/span")
   public List<WebElement> ele2;


    public void PickDate(String CheckInMonth ,String StartDateVale ,String CheckOutMonth ,String EndDateVale){
       startDate.click();
       while(true){

            month = monthEl.getText();
           System.out.println(month);
           if(CheckInMonth.equals(month)){
               System.out.println("found");
               break;
           }
           else
           {
               NextMonthButton.click();
           }}



       for (WebElement element:ele){
           eleText= element.getText();
           if(StartDateVale.equals(eleText)){
               System.out.println("found start date");
               element.click();
               break;
           }

           else
           {
               System.out.println("(" + eleText + ")" + "(" +StartDateVale + ")");

           }}
       //loop for checkout date month
       while(true){

           String month = monthEl2.getText();
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

       for (WebElement element2:ele2){
           eleText= element2.getText();
           if(EndDateVale.equals(eleText)){
               System.out.println("found end date");
               element2.click();
               break;
           }

           else
           {
               System.out.println("(" + eleText +")");

           }

       }

   }

   public void WriteLocation(String LocationText)
   {
       Location.sendKeys(LocationText);
   }

   public void search (){

     searchButton.click();



    }

    public void wholeSearchFunc( String CheckInMonth  , String StartDateVale , String CheckOutMonth, String EndDateVale ,
     String LocationText ){
        super.ClosePopUp();
        this.WriteLocation(LocationText);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.PickDate(CheckInMonth,  StartDateVale, CheckOutMonth, EndDateVale );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.search();


    }
}
