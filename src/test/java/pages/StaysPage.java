package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class StaysPage extends BasePage{

    WebDriver driver;

    public StaysPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#ss")
    WebElement location;

    @FindBy(xpath = "//div[@data-mode='checkin']")
    WebElement dates;

    @FindBy(css = ".xp__guests__count")
    WebElement guestsCount;

    @FindBy(css = "[aria-label='Decrease number of Adults']")
    WebElement decAdultNum;

    @FindBy(css = "[aria-label='Increase number of Adults']")
    WebElement incAdultNum;

    @FindBy(css = "[aria-label='Decrease number of Children'")
    WebElement decChildNum;

    @FindBy(css = "[aria-label='Increase number of Children']")
    WebElement incChildNum;

    @FindBy(css = "[aria-label='Increase number of Rooms']")
    WebElement incRooms;

    @FindBy(xpath = "//span[contains(text(), 'Search')]")
    WebElement search;


    public void addLocation(String locationText) throws InterruptedException {
        typeText(location,locationText);
    }

    public void checkInOut(String checkInDate, String checkOutDate) throws InterruptedException {

        clickElement(dates);
        clickElement(driver.findElement(By.xpath("//td[@data-date='"+checkInDate+"']")));
        clickElement(driver.findElement(By.xpath("//td[@data-date='"+checkOutDate+"']")));

    }

    public void addOccupancyInformation(Map<String, String> data) throws InterruptedException {

        clickElement(guestsCount);

        addAdults(data.get("AdultsNum"));
        addChildren(data.get("ChildrenNum"), data.get("ChildrensYears"));
        addRooms(data.get("Rooms"));


    }

    private void addRooms(String num) throws InterruptedException {
        for(int i = 2; i <= Integer.parseInt(num); i++) {
            clickElement(incRooms);
        }
    }

    private void addChildren(String num, String age) throws InterruptedException {
        String [] ages = age.split(",");
        if(num.equals("0")){
            clickElement(decChildNum);
        }
        for(int i = 1; i <= Integer.parseInt(num); i++) {
            clickElement(incChildNum);
            selectByValue(driver.findElement(By.cssSelector("[aria-label='Child "+i+" age']")), ages[i-1]);
        }

    }

    private void addAdults(String num) throws InterruptedException {
        if(num.equals("2")){
            //do nothing
        } else if(Integer.parseInt(num)< 2){
            clickElement(decAdultNum);
        } else {
            for(int i = 3; i <= Integer.parseInt(num); i++) {
                clickElement(incAdultNum);
            }
        }
    }

    public void clickSearch() throws InterruptedException {
        clickElement(search);
    }
}
