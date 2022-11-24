package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class AbsPageObject {

    protected WebDriver driver;
    protected Actions actions;

    protected WebDriverWait wait;

    public AbsPageObject(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void clickElementByCss(String cssParam){
        new WebDriverWait(driver,4).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(cssParam))));
        driver.findElement(By.cssSelector(cssParam)).click();
    }
    public void clickElementById(String idParam){
        new WebDriverWait(driver,4).until((ExpectedConditions.elementToBeClickable(driver.findElement(By.id(idParam)))));
        driver.findElement(By.id(idParam)).click();
    }
    public void clickElementByXpath(String xpathParam){
        new WebDriverWait(driver,4).until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xpathParam))));
        driver.findElement(By.xpath(xpathParam)).click();
    }

    public String getTextByCss(String css){
        return driver.findElement(By.cssSelector(css)).getText();
    }
    public List<WebElement> findAllElemByXpath(String xpath){
        return driver.findElements(By.xpath(xpath));
    }
    public List<WebElement> findAllElemByCss(String css){
        return driver.findElements(By.cssSelector(css));
    }


}
