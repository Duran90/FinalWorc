package driver.impl;

import exceptions.DriverNotSupportedException;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.logging.Level;

public class SafariWebDriver implements IDriver{
    @Override
    public WebDriver newDriver() throws DriverNotSupportedException {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        safariOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
        safariOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        safariOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        downloadLocalWebDriver(DriverManagerType.SAFARI);

        return new SafariDriver(safariOptions);
    }
}
