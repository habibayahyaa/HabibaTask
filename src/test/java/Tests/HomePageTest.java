package Tests;

import Pages.HomePage;
import Pages.SearchResults;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePageTest extends TestBase
{  String CheckInMonth = "October 2025";
    String CheckOutMonth ="October 2025";
    String StartDateVale= "1";
    String EndDateVale= "15";
    String LocationText= "Alexandria";

    HomePage HomeObject;
    SearchResults Search;

    @Test
    public void userCanSearchSuccessfully()
    {

        HomeObject= new HomePage(driver);
        HomeObject.wholeSearchFunc(CheckInMonth,CheckOutMonth,StartDateVale,EndDateVale, LocationText );


    }

}
