package Tests;

import Pages.HomePage;
import Pages.SearchResults;
import org.testng.annotations.Test;

import javax.naming.directory.SearchResult;
import java.time.Duration;

public class SearchResultsTest extends TestBase{
    HomePage HomeObject;
    SearchResults Search;
    String CheckInMonth = "October 2025";
    String CheckOutMonth ="October 2025";
    String StartDateVale= "1";
    String EndDateVale= "15";
    String LocationText= "Alexandria";


    @Test
    public void SearchTolip(){

        HomeObject= new HomePage(driver);
        Search = new SearchResults(driver);

        HomeObject.wholeSearchFunc(CheckInMonth,CheckOutMonth,StartDateVale,EndDateVale, LocationText);

        Search.SearchForTuplip();


    }
}
