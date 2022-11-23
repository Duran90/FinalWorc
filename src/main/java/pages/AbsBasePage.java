package pages;

import org.openqa.selenium.WebDriver;
import pageObject.AbsPageObject;

public abstract class AbsBasePage extends AbsPageObject {

    public final static String BASE_URL = System.getProperty("base.url");

    private final Header header;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        this.header = new Header(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public Header getHeader() {
        return header;
    }
}
