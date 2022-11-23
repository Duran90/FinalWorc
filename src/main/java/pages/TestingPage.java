package pages;

import components.CourseCardElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TestingPage extends AbsBasePage {
    private final String classname = ".lessons__new-item_hovered";
    private List<WebElement> courses;


    public TestingPage(WebDriver driver) {
        super(driver);
        courses = findAllElemByCss(classname);

    }

    public int courseCount() {
        return courses.size();
    }

    public LessonsPage clickOnCard(String courseName) {
        for (WebElement courseCard : courses) {
            WebElement title = courseCard.findElement(By.cssSelector(".lessons__new-item-title"));
            if (courseName.equals(title.getText())) {
                courseCard.click();
                return new LessonsPage(driver);
            }
        }
        return null;
    }

}
