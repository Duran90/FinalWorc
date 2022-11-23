package nearTests;

import cardsTests.CardsTests;
import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.MainPage;
import pages.EventsPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NearTests {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardsTests.class);
    private WebDriver driver;
    private EventsPage eventsPage;
    private String expectedFormat = "Открытый вебинар";
    private static final String DATE_PATTERN = "yyyy dd MMMM hh:mm";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

    String onlineXpath = "//a[contains(text(),'Открытый вебинар')]";

    @BeforeEach
    public void setUp() throws DriverNotSupportedException {
        this.driver = new DriverFactory().getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        logger.info("Драйвер поднят");
    }

    @AfterEach
    public void startDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }



    @Test
    public void sortAndCheckTest() throws ParseException {
        logger.info("Сортировка и формат");
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        eventsPage = mainPage.getHeader().openNearPage();
        eventsPage.sortByEventType(onlineXpath);
        eventsPage.scrollPageDown();
        checkFormat();
        checkEventsDates();
    }

    public void checkFormat() {
        System.out.println("Индекс и значение");
        List<WebElement> events = eventsPage.getEventCards();
        for (int j = 0; j < events.size(); j++) {
            String actualFormat = events.get(j).findElement(By.cssSelector(".dod_new-type__text")).getText();
            Assertions.assertEquals(expectedFormat, actualFormat);
            logger.info("index: " + j + " --- " + actualFormat);
        }

    }

    public void checkEventsDates() throws ParseException {
        for (WebElement card : eventsPage.getEventCards()) {
            checkEventDate(card);
        }
    }

    private void checkEventDate(WebElement event) throws ParseException {
        List<WebElement> dateElements = event.findElements(By.cssSelector(".dod_new-event__date-text"));
        String dateText = getEventDate(dateElements);
        Date eventDate = dateFormat.parse(dateText);
        Date nowDate = new Date();
        logger.info("Текушая дата: " + nowDate+" --- Дата иветна: " + eventDate);
        Assertions.assertTrue(nowDate.before(eventDate));
    }

    private String getEventDate(List<WebElement> dateElements) {
        List<String> dates = new ArrayList<>();
        for (WebElement e : dateElements) {
            dates.add(e.getText());
        }
        dates.add(0, String.valueOf(LocalDate.now().getYear()));
        return String.join(" ", dates);
    }

}
