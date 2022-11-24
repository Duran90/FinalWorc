package pages;

import components.EventCardComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class EventsPage extends AbsBasePage {

    private static final String cardSelector = ".dod_new-event";

    public EventsPage(WebDriver driver) {
        super(driver);
    }

    public List<EventCardComponent> getEventCards() {
        return driver.findElements(By.cssSelector(cardSelector))
                .stream().map(EventCardComponent::create).collect(Collectors.toList());
    }

    public void scrollPageDown() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        int loadedItems = driver.findElements(By.cssSelector(cardSelector)).size();
        while (true) {
            js.executeScript("$('html,body').animate({scrollTop: document.body.scrollHeight},\"fast\");");
            waitUntilCountChanges(loadedItems);

            int quantity = driver.findElements(By.cssSelector(cardSelector)).size();
            if (loadedItems == quantity) break;
            loadedItems = quantity;
        }
    }

    public void waitUntilCountChanges(int current) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5);
            wait.until((ExpectedCondition<Boolean>) driver -> {
                int elementCount = driver.findElements(By.cssSelector(cardSelector)).size();
                return elementCount > current;
            });
        } catch (org.openqa.selenium.TimeoutException ignore) {
        }

    }

    public void sortByEventType(String sortType) {
        String sortXpath = "//span[contains(text(),'Все мероприятия')]";
        String sortTypeXpath = String.format("//a[contains(text(),'%s')]", sortType);
        clickElementByXpath(sortXpath);
        clickElementByXpath(sortTypeXpath);
    }

}
