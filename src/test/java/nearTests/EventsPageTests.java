package nearTests;

import cardsTests.CardsTests;
import components.EventCardComponent;
import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.EventsPage;
import pages.MainPage;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class EventsPageTests {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardsTests.class);
    private WebDriver driver;
    private EventsPage eventsPage;
    private static final String expectedFormat = "Открытый вебинар";



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
    public void sortAndCheckTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        eventsPage = mainPage.getHeader().openNearPage();
        logger.info("Сортировка");
        eventsPage.sortByEventType(expectedFormat);
        logger.info("Скрол");
        eventsPage.scrollPageDown();
        logger.info("Проверка карточек: старт");
        checkCards();
        logger.info("Проверка карточек: финиш");
    }

    public void checkCards() {
        eventsPage.getEventCards().forEach(this::checkCard);
    }

    private void checkCard(EventCardComponent component) {
        logger.info(String.format("Проверка карточки: %s", component));
        logger.info(String.format("Тип мероприятия: ожидаемое %s, действительное %s", expectedFormat, component.getDate()));
        Assertions.assertEquals(expectedFormat, component.getType());
        logger.info(String.format("Дата мероприятия: ожидаемое %s, действительное %s", LocalDateTime.now(), component.getDate()));
        Assertions.assertTrue(component.getDate().isAfter(LocalDateTime.now()));
    }

}
