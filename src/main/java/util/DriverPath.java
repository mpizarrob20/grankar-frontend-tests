package util;

/**
 * Created by mpizarro on 8/01/2018.
 */

public class DriverPath {

    private String chrome = "src/main/java/drivers/chromedriver2.35.exe";
    private String chromeLinux = "src/main/java/drivers/chromedriver";
    private String chromeMac = "src/main/java/drivers/chromedriver_mac";
    private String firefox = "src/main/java/drivers/geckodriver.exe";
    private String firefoxLinux = "src/main/java/drivers/geckodriver";
    private String internetExplorer = "src/main/java/drivers/IEDriverServer_x64_3.4.0.exe";
    private String url = "src/test/java/resources/url.yaml";

    public String getChrome() {
        return chrome;
    }

    public String getFirefox() {
        return firefox;
    }

    public String getUrl() {
        return url;
    }

    public String getChromeMac() {
        return chromeMac;
    }

    public String getInternetExplorer() {
        return internetExplorer;
    }

    public String getChromeLinux() {
        return chromeLinux;
    }

    public String getFirefoxLinux() {
        return firefoxLinux;
    }


}
