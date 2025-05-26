package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class LastPage extends PageBase{


    public LastPage(WebDriver driver) {
        super(driver);
    }




        public List<String> getAndPrintBookingDetails() {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Extracting data from window.utag_data
            String checkinDate = (String) js.executeScript("return window.utag_data.date_in;");
            String checkoutDate = (String) js.executeScript("return window.utag_data.date_out;");
            String adults = (String) js.executeScript("return window.utag_data.adults;");
            String rooms = (String) js.executeScript("return window.utag_data.rooms;");
            String hotelName = (String) js.executeScript("return window.utag_data.hotel_name;");
            String nights = (String) js.executeScript("return window.utag_data.nights;");
            String currency = (String) js.executeScript("return window.utag_data.currency;");
            String basePrice = (String) js.executeScript("return window.utag_data.base;");

            // Store details in an ArrayList
            List<String> bookingDetails = new ArrayList<>();
            bookingDetails.add("Check-in Date: " + checkinDate);
            bookingDetails.add("Check-out Date: " + checkoutDate);
            bookingDetails.add("Number of Adults: " + adults);
            bookingDetails.add("Number of Rooms: " + rooms);
            bookingDetails.add("Hotel Name: " + hotelName);
            bookingDetails.add("Number of Nights: " + nights);
            bookingDetails.add("Currency: " + currency);
            bookingDetails.add("Base Price: " + basePrice);

            // Print details to console
            System.out.println("--- Booking Details Summary ---");
            for (String detail : bookingDetails) {
                System.out.println(detail);
            }
            System.out.println("-----------------------------");

            return bookingDetails;
        }
    }



