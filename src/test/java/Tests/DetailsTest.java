package Tests;

import Data.ExcelReader;
import Pages.DetailsPage;
import Pages.HomePage;
import Pages.LastPage;
import Pages.SearchResults;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class DetailsTest extends TestBase {
   //String CheckInMonth = "October 2025";
   //String CheckOutMonth ="October 2025";
    //String StartDateVale= "1";
    //String EndDateVale= "15";
    //String LocationText= "Alexandria";

  //  String beds = "2 single beds";
    //String Amount ="2";

//
    //@DataProvider(name ="ExcelData")
  //  public  Object[][] detailsData () throws IOException{
  //      ExcelReader ER = new ExcelReader();
   //     return ER.getExcelData();

   // }

    @DataProvider(name = "excelData")
    public Object[][] readExcel() {
        return ExcelReader.getExcelData("Sheet1");
    }


    @Test(dataProvider = "excelData")
    public void detailsTest( String CheckInDay,String CheckInMonth, String CheckInYear, String CheckOutDay, String CheckOutMonth,String CheckOutYear,String location , String Amount ,String beds){

        CheckInDay = CheckInDay.split("\\.")[0];
        CheckInYear = CheckInYear.split("\\.")[0];
        CheckOutDay = CheckOutDay.split("\\.")[0];
        CheckOutYear = CheckOutYear.split("\\.")[0];
        Amount = Amount.split("\\.")[0];

        System.out.println(CheckInDay+CheckInMonth+CheckInYear+CheckOutDay+CheckOutMonth+CheckOutYear+location+Amount+beds);
        HomePage HomeObject = new HomePage(driver);
        HomeObject.wholeSearchFunc(CheckInMonth+" "+ CheckInYear ,CheckInDay,CheckOutMonth+" "+CheckInYear ,CheckOutDay,location);

        SearchResults Search = new SearchResults(driver);
        Search.SearchForTuplip();

        DetailsPage Details = new DetailsPage(driver);

        String name = Details.wholeSelectFunc(Amount,beds);
       Assert.assertEquals("Tolip Hotel Alexandria",name);

        LastPage lp = new LastPage(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        lp.getAndPrintBookingDetails();

    }
}
