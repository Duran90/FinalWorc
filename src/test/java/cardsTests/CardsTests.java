package cardsTests;

import driver.DriverFactory;
import exceptions.DriverNotSupportedException;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.concurrent.TimeUnit;

public class CardsTests {
    private final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CardsTests.class);
    private WebDriver driver;
    int cardsCount = 14;

    private String title = "Нагрузочное тестирование";
    private String duration = "4 месяца";
    private String format = "Online";
    private String description = "Все инструменты и особенности процесса проведения достоверного нагрузочного тестирования. Стенды для отработки материалов.";


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
    public void countAllCards() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        TestingPage testingPage = mainPage.getHeader().openTestingPage();
        assertCountCard(testingPage, cardsCount);
    }

    @Test
    public void fillCard() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        TestingPage testingPage = mainPage.getHeader().openTestingPage();
        LessonsPage lessonsPage = testingPage.clickOnCard(title);
        assertTitlt(lessonsPage, title);
        assertDescription(lessonsPage, description);
        assertDuration(lessonsPage, duration);
        assertFormat(lessonsPage, format);

    }

    public void assertCountCard(TestingPage testingPage, int expectedCount) {
        int count = testingPage.courseCount();
        Assertions.assertEquals(expectedCount, count);
        logger.info("Количество ожидаемых карточек: " + expectedCount + "/n" + "Фактическое кол-во: " + count);
    }

    public void assertTitlt(LessonsPage lessonsPage, String title) {
        Assertions.assertEquals(title, lessonsPage.getTitle());
    }

    public void assertDescription(LessonsPage lessonsPage, String description) {
        Assertions.assertEquals(description, lessonsPage.getDescription());
    }

    public void assertDuration(LessonsPage lessonsPage, String duration) {
        Assertions.assertEquals(duration, lessonsPage.getDurationOfStudy());
    }

    public void assertFormat(LessonsPage lessonsPage, String format) {
        Assertions.assertEquals(format, lessonsPage.getFormat());
    }


}
