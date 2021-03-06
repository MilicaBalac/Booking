package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static javax.xml.bind.JAXBIntrospector.getValue;

public class SearchResultPage extends BasePage{

    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#ss")
    WebElement location;

    @FindBy(css = "#xp__guests__toggle")
    WebElement guests;

    @FindBy(xpath ="//label[text()='Adults']/../..//span[@class='bui-stepper__display']")
    WebElement adultsNum;

    @FindBy(xpath ="//label[text()='Children']/../..//span[@class='bui-stepper__display']")
    WebElement childrenNum;
    //label[text()='Children']/../..//input
    @FindBy(xpath ="//label[text()='Rooms']/../..//span[@class='bui-stepper__display']")
    WebElement roomsNum;

    @FindBy(xpath = "//div[@data-placeholder='Check-in Date']")
    WebElement checkInDateEl;

    @FindBy(xpath = "//div[@data-placeholder='Check-out Date']")
    WebElement checkOutDateEl;

    @FindBy(xpath = "//span[text()= 'See availability']")
    List<WebElement> seeAvailability;


    public void verifyLocation(String value){
       Assert.assertEquals(getValue(location), value);
    }

    public void verifyAdultsNum(String value){
        Assert.assertEquals(getText(adultsNum), value);
    }

    public void verifyChildrenNum(String value){
        Assert.assertEquals(getText(childrenNum), value);
    }
    public void verifyRoomsNum(String value){
        Assert.assertEquals(getText(roomsNum), value);
    }

    public void openGuestInfo() throws InterruptedException {
        clickElement(guests);
    }

    public void verifyDates (Map<String, String> data) throws ParseException {


        Date checkInDate = (new SimpleDateFormat("yyy-MM-dd")).parse(data.get("CheckIn"));
        Date checkOutDate = (new SimpleDateFormat("yyy-MM-dd")).parse(data.get("CheckOut"));

        String pattern = "EEEE, MMMM dd, YYYY";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String checkInFormatted = simpleDateFormat.format(checkInDate);
        String checkOutFormatted = simpleDateFormat.format(checkOutDate);


        System.out.println(checkInFormatted);
        System.out.println(checkOutFormatted);
        Assert.assertEquals(checkInFormatted, getText(checkInDateEl));
        Assert.assertEquals(checkOutFormatted, getText(checkOutDateEl));
    }

    public void clickFirstResult() throws InterruptedException {
        clickElement(seeAvailability.get(0));
    }
}
