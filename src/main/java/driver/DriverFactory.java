package driver;

import driver.impl.ChromeWebDriver;
import driver.impl.Drivers;
import driver.impl.FirefoxWebDriver;
import driver.impl.SafariWebDriver;
import exceptions.DriverNotSupportedException;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class DriverFactory implements IDriverFactory {

    private String browserType = System.getProperty("browser").toLowerCase(Locale.ROOT);


    @Override
    public WebDriver getDriver() throws DriverNotSupportedException {
        switch (Drivers.valueOf(browserType.toUpperCase())) {
            case CHROME: {
                return new ChromeWebDriver().newDriver();
            }
            case FIREFOX:{
                return new FirefoxWebDriver().newDriver();
            }
            case SAFARI:
                return new SafariWebDriver().newDriver();
            default:
                throw new DriverNotSupportedException(this.browserType);
        }
    }
}
