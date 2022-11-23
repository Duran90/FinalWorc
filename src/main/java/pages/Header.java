package pages;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public class Header extends AbsPageObject {
    public final String MENU_XPATH_COURSE_SELECTOR = "//p[contains(text(),'Курсы')]";
    public final String MENU_XPATH_EVENTS_SELECTOR = "//p[contains(text(),'События')]";
    public final String TESTING = "//a[contains(text(),'Тестирование')]";
    public  final String NEAR = "//a[contains(text(),'Календарь мероприятий')]";


    public Header(WebDriver driver) {
        super(driver);
    }

    public LoginPage sinInClick() {
        clickElementByCss("button[data-modal-id='new-log-reg']");
        return new LoginPage(driver);
    }

    public TestingPage openTestingPage(){
        clickElementByXpath(MENU_XPATH_COURSE_SELECTOR);
        clickElementByXpath(TESTING);
        return new TestingPage(driver);
    }

    public EventsPage openNearPage(){
        clickElementByXpath(MENU_XPATH_EVENTS_SELECTOR);
        clickElementByXpath(NEAR);
        return new EventsPage(driver);
    }


}
