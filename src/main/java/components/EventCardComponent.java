package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EventCardComponent {

    private static final String TYPE_SELECTOR = ".dod_new-type__text";
    private static final String DATE_SELECTOR = ".dod_new-event__date-text";
    private static final String TITLE_SELECTOR = ".dod_new-event__title";

    public static final String DATE_PATTERN = "d MMMM yyyy HH:mm";



    private final String title;

    private final String type;

    private final LocalDateTime date;

    private EventCardComponent(String title, String type, LocalDateTime date) {
        this.title = title;
        this.type = type;
        this.date = date;
    }

    public static EventCardComponent create(WebElement element) {
        String title = element.findElement(By.cssSelector(TITLE_SELECTOR)).getText();
        String type = element.findElement(By.cssSelector(TYPE_SELECTOR)).getText();
        List<String> dateElements = element.findElements(By.cssSelector(DATE_SELECTOR)).stream().map(WebElement::getText).collect(Collectors.toList());
        dateElements.add(1, String.valueOf(LocalDateTime.now().getYear()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDateTime date = LocalDateTime.parse(String.join(" ", dateElements), formatter);
        //ну это костыль конечно
        if (date.getMonth() == Month.JANUARY) {
            date = date.plusYears(1);
        }
        return new EventCardComponent(title, type, date);
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "EventCardComponent{" +
                "title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", date=" + date +
                '}';
    }
}
