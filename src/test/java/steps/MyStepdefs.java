package steps;





import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.cucumber.java.Scenario;
import io.appium.java_client.android.AndroidDriver;


import org.openqa.selenium.OutputType;

import pages.PageMaps;
import utils.Driver;


import java.io.IOException;



public class MyStepdefs {

    AndroidDriver driver;
    Scenario s;
    PageMaps maps;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
        driver.launchApp();

    }

    @After
    public void quit(Scenario s) throws IOException {

        try {
           // String path = Driver.Screenshot();
            byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            s.attach(screenshot, "image/png","error");


        }
        catch(Exception  e ){throw e;}
        driver.closeApp();
    }


    @Given("the google maps app on appium")
    public void theGoogleMapsAppOnAppium() throws InterruptedException {
        Thread.sleep(5000);
        maps = new PageMaps(driver);
        try{
            maps.skip.click();
            Thread.sleep(3000);
            //maps.findLocation.click();
            maps.centerButon.click();
            Thread.sleep(3000);
            maps.permision.click();
            Thread.sleep(10000);

        }
        catch(Exception e){throw e;}




    }

    @When("search {string}")
    public void search(String arg0) {
        driver.findElementByCustom("ai:search").sendKeys(arg0);
    }

    @Then("the maps open in the correct place")
    public void theMapsOpenInTheCorrectPlace() {
    }
}
