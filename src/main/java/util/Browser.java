package util;

import org.json.simple.JSONArray;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by mpizarro on 8/01/2018.
 */

public class Browser {

    private static final String BROWER_CHROME = "chrome";
    private static final String BROWER_FIREFOX = "firefox";
    public static final String BROWSER_IE = "IE";
    private util.DriverPath driverPath = new util.DriverPath();
    private WebDriver driver;
    private util.JsonUtil jsonUtil = new util.JsonUtil();
    private Yaml yaml = new Yaml();


    public WebDriver getBrowserDriver(String browser, String url) throws Exception {
        System.out.println("Launching " + browser + " Browser");
        InputStream input;
        Map<String, String> yamlParsers;
        switch (browser) {
            case BROWER_CHROME:
                //---------Windows
                System.setProperty("webdriver.chrome.driver", driverPath.getChrome());

                //--------Linux
                //System.setProperty("webdriver.chrome.driver", driverPath.getChromeLinux());

                //--------Mac
                //System.setProperty("webdriver.chrome.driver", driverPath.getChromeMac());

                input = new FileInputStream(new File(driverPath.getUrl()));
                yamlParsers = (Map<String, String>) yaml.load(input);
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                driver.get(yamlParsers.get(url));
                return driver;

            case BROWER_FIREFOX:
                //--------Windows
                System.setProperty("webdriver.gecko.driver", driverPath.getFirefox());

                //--------Linux---
                //System.setProperty("webdriver.gecko.driver", driverPath.getFirefoxLinux());
                input = new FileInputStream(new File(driverPath.getUrl()));
                yamlParsers = (Map<String, String>) yaml.load(input);
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                driver.get(yamlParsers.get(url));
                return driver;

            case BROWSER_IE:
                System.setProperty("webdriver.ie.driver", driverPath.getInternetExplorer());
                Yaml yaml = new Yaml();
                input = new FileInputStream(new File(driverPath.getUrl()));
                yamlParsers = (Map<String, String>) yaml.load(input);
                String urlIE = yamlParsers.get(url);
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(CapabilityType.BROWSER_NAME, "IE");
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, urlIE);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
                capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
                driver = new InternetExplorerDriver(capabilities);
                driver.manage().window().maximize();
                return driver;
            default:
                throw new Exception("Browser " + browser + "not implemented!");
        }
    }

    public ArrayList<WebDriver> homePage(String url, String browsers) throws Exception {
        JSONArray browsersForTest = (JSONArray) jsonUtil.returnJson("login.json").get(browsers);
        String browser;
        ArrayList<WebDriver> drivers = new ArrayList<>();
        for (int i = 0; i < browsersForTest.size(); i++) {
            browser = (String) browsersForTest.get(i);
            drivers.add(getBrowserDriver(browser, url));
        }
        return drivers;
    }

}
