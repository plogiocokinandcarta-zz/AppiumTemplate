package utils;;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.OutputType;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Driver {


    public static Properties properties;
    private static AppiumDriver<MobileElement> driver;
    private DesiredCapabilities capabilities;
    private static DateFormat df;



    private void loadProperties() {
        try {
            PropertyLoader propertyLoader = new PropertyLoader();
            properties = propertyLoader.getPropValues("config.properties");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private Driver() {
        loadProperties();

        try {
            if (properties.getProperty("platform").toUpperCase().equals("ANDROID"))
            {
                setCapabilitiesAndroid();
                driver = new AndroidDriver<>(new URL(properties.getProperty("appiumIP")),capabilities);
            }
            else{
                setCapabilitiesIos();
                driver = new IOSDriver<>(new URL(properties.getProperty("IosStuff")),capabilities);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }


    private void setWebCapabilities(){

    }

    private void setCapabilitiesAndroid() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,properties.getProperty("platform"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        capabilities.setCapability("appPackage",properties.getProperty("appPackage"));
        capabilities.setCapability("udid",properties.getProperty("udid"));
        capabilities.setCapability("appWaitPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("appActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("appWaitActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("automationName", "UiAutomator2");


        HashMap<String, String> customFindModules = new HashMap<>();
        customFindModules.put("ai", "test-ai-classifier");
        capabilities.setCapability("customFindModules", customFindModules);
        capabilities.setCapability("shouldUseCompactResponses", false);
    }
    private void setCapabilitiesIos() {
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,properties.getProperty("platform"));
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
        capabilities.setCapability("appPackage",properties.getProperty("appPackage"));
        capabilities.setCapability("udid",properties.getProperty("udid"));
        capabilities.setCapability("appWaitPackage", properties.getProperty("appPackage"));
        capabilities.setCapability("appActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("appWaitActivity", properties.getProperty("appActivity"));
        capabilities.setCapability("automationName", "UiAutomator2");


        HashMap<String, String> customFindModules = new HashMap<>();
        customFindModules.put("ai", "test-ai-classifier");
        capabilities.setCapability("customFindModules", customFindModules);
        capabilities.setCapability("shouldUseCompactResponses", false);
    }


    //region Public methods

    public static AppiumDriver<MobileElement> getDriver() {
        if (driver == null) {
            new Driver();}
        return driver;

    }



    public static void screenshot(Scenario snr)  {

        try{

            byte[] ts =  driver.getScreenshotAs(OutputType.BYTES);
            snr.attach(ts, "image/png","screenshot");

        }
        catch(Exception e){ e.printStackTrace();}


    }

    public static Properties getProperties() {
        return properties;
    }


    public static void  changeLocation(double latitud, double longitude){
        Location location = new Location(latitud, longitude, 1000);
        driver.setLocation(location);
    }

    public static void setUpBandwidth(int downloadSpeed, int uploadSpeed,boolean offline) throws IOException {
        CommandExecutor executor = ((RemoteWebDriver) driver).getCommandExecutor();

        Map map = new HashMap();
        map.put("offline", offline);
        map.put("latency", 5);
        map.put("download_throughput", downloadSpeed);
        map.put("upload_throughput", uploadSpeed);
        Response response = executor.execute(new Command(((RemoteWebDriver) driver).getSessionId(), "setNetworkConditions",
                ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));

    }


    //endregion
}
