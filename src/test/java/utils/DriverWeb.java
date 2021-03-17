package utils;;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import io.cucumber.java.Scenario;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.html5.LocationContext;
import org.openqa.selenium.remote.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DriverWeb {


    public static Properties properties;
    private static WebDriver driver;
    private  ChromeOptions options;
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

    private DriverWeb() {
        loadProperties();
        setCapabilities("iPad");
        WebDriverManager.chromedriver().setup();
        try{
            driver = new ChromeDriver(options);

        }
        catch(Exception e){
        }


    }


    private void setCapabilities(String emulation){
        options = new ChromeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName",emulation );
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
    }



    //region Public methods

    public static WebDriver getDriver() {
        if (driver == null) {
            new DriverWeb();}
        return driver;
    }

    public static void screenshot(Scenario snr)  {

        try{

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            // now copy the screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File(System.getProperty("user.dir") + "target/" + properties.getProperty("Screenshots")  + "screenshot.png"));

        }
        catch(Exception e){ e.printStackTrace();}
    }

    public static Properties getProperties() {
        return properties;
    }

    public static void  changeLocation(double latitude, double longitude){
        Location location = new Location(latitude, longitude, 1000);
        ((LocationContext)driver).setLocation(location);
    }

    public static void setUpBandwidth(int downloadSpeed, int uploadSpeed,boolean offline) throws IOException {
        CommandExecutor executor = ((RemoteWebDriver) driver).getCommandExecutor();

        Map<Object, Object> map = new HashMap<>();
        map.put("offline", offline);
        map.put("latency", 5);
        map.put("download_throughput", downloadSpeed);
        map.put("upload_throughput", uploadSpeed);
        Response response = executor.execute(new Command(((RemoteWebDriver) driver).getSessionId(), "setNetworkConditions",
                ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map))));

    }


    //endregion
}
