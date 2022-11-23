package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EventsPage extends AbsBasePage{
    public EventsPage(WebDriver driver) {
        super(driver);
    }

    private List<WebElement> cards;


    public List<WebElement> getEventCards() {
        return cards;
    }

    public void scrollPageDown(){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        boolean documentReady = false;
        List<WebElement> current;
        while (!documentReady){
            String viewCss = ".dod_new-event-content";
            current = findAllElemByCss(viewCss);
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ignored) {
            }
            this.cards = findAllElemByCss(viewCss);
            documentReady = current.size() == cards.size();
        }
    }

    public void sortByEventType(String sortType){
        String sortXpath = "//span[contains(text(),'Все мероприятия')]";
        clickElementByXpath(sortXpath);
        clickElementByXpath(sortType);
    }

}
