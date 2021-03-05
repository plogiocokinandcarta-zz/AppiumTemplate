package utils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Driver {


    public static Properties properties;
    private static AppiumDriver<MobileElement> driver;
    private DesiredCapabilities capabilities;
    private static DateFormat df;

    private void LoadProperties() {
        try {
            PropertyLoader propertyLoader = new PropertyLoader();
            properties = propertyLoader.getPropValues("config.properties");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private Driver() {
        LoadProperties();

        try {
            if (properties.getProperty("platform").toUpperCase().equals("ANDROID"))
            {
                SetCapabilitiesAndroid();
                driver = new AndroidDriver<>(new URL(properties.getProperty("appiumIP")),capabilities);
            }
            else{
                SetCapabilitiesAndroidIos();
                driver = new IOSDriver<>(new URL(properties.getProperty("IosStuff")),capabilities);
            }


            Location location = new Location(41.902652, -87.701975, 1000);
            driver.setLocation(location);

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void SetCapabilitiesAndroid() {
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
    private void SetCapabilitiesAndroidIos() {
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


    public static void Screenshot(Scenario snr)  {

        try{
            //String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            byte[] ts =  driver.getScreenshotAs(OutputType.BYTES);
            snr.attach(ts, "image/png","error");

        }
        catch(Exception e){ e.printStackTrace();}


    }

    public static Properties getProperties() {
        return properties;
    }
    //endregion
}
