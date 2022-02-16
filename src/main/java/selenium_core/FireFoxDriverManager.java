package selenium_core;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FireFoxDriverManager extends DriverManager {

    @Override
    public void createWebDriver(String version) {
        System.setProperty("webdriver.gecko.driver", "src/main/java/resources/geckodriver"+version+".exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("start-maximized");
        this.driver = new FirefoxDriver(options);
    }
}
