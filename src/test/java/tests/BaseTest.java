package tests;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import selenium_core.DriverManager;
import selenium_core.DriverManagerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public DriverManager driverManager;

    public void setUPTest(String browser, String version, long waitTime) throws Exception {
        driverManager = DriverManagerFactory.getDriverManager(browser);
        driver = driverManager.getWebDriver(version);
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }

    public void takeScreenShot(String fileName) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenshots/"+fileName+".png"));
    }

    public void reportScreenshot(String fileName, String desc) throws IOException {
        takeScreenShot(fileName);
        Path content = Paths.get("screenshots/"+fileName+".png");
        try(InputStream is = Files.newInputStream(content)){
            Allure.addAttachment(desc, is);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void quit() {
        driverManager.quitWebDriver();
    }


    public void startApplication(String env) throws Exception {
        switch (env) {
            case "QA":{
                driver.get("https://www.booking.com/");
            }break;
            case "STG":{
                driver.get("https://www.booking.com/a");
            }break;
            default: throw new Exception("ENV: " +env+" not supported!");
        }
    }
}
