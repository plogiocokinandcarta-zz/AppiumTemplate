package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import pages.PageMaps;
import utils.Driver;




public class MyStepdefs {

    AppiumDriver<MobileElement> driver;
    Scenario s;
    PageMaps maps;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
        driver.launchApp();

    }

    @After
    public void quit(Scenario s)  {
        Driver.Screenshot(s);
        driver.closeApp();
    }


    @Given("the google maps app on appium")
    public void theGoogleMapsAppOnAppium() throws Exception {
        Thread.sleep(5000);
        maps = new PageMaps(driver);
        maps.goToMyLocation();

    }

    @When("search {string}")
    public void search(String arg0) {
       // driver.findElementByCustom("ai:search").sendKeys(arg0);



    }

    @Then("the maps open in the correct place")
    public void theMapsOpenInTheCorrectPlace() {

    }
}
