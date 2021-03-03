package utils;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Driver {

    private static AndroidDriver driver;
    private Properties properties;
    private PropertyLoader propertyLoader;
    private DesiredCapabilities capabilities;
    private static DateFormat df;

    private void LoadProperties() {
        try {
            propertyLoader = new PropertyLoader();
            properties = propertyLoader.getPropValues("config.properties");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    private Driver() {
        LoadProperties();
        SetCapabilities();
        try {
            driver = new AndroidDriver (new URL(properties.getProperty("appiumIP")),capabilities);
            Location location = new Location(41.902652, -87.701975, 1000);
            driver.setLocation(location);
            //driver = new AndroidDriver (new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void SetCapabilities() {
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



    public static AndroidDriver getDriver() {
        if (driver == null) {
            new Driver();}
        return driver;

    }


    public static String Screenshot() throws IOException {
        String destination;

        try{
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

            TakesScreenshot ts = (TakesScreenshot)driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            destination = System.getProperty("user.dir") + "/target/cucumber-report/"+dateName+".png";
            File finalDestination = new File(destination);
            FileUtils.copyFile(source, finalDestination);

        }
        catch(Exception e){ return null ;}

        return destination;
    }
}
