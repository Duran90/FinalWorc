package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.AbsPageObject;

public class CourseCardElement {

    private final WebElement element;

    public CourseCardElement(WebElement element) {
        this.element = element;
    }

    public String getTitle() {
        return element.findElement(By.cssSelector(".lessons__new-item-title")).getText();
    }

    public void click() {
        element.click();
    }
}
