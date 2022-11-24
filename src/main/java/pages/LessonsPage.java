package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LessonsPage extends AbsBasePage{
    private static final String cssTitle = ".course-header2__title";
    private static final String cssDescription = ".course-header2__admin-text";
    private static final String cssFormat = ".course-header2-bottom__content-item.container__col.container__col_2.container__col_md-2.container__col_ssm-12 .course-header2-bottom__item-text";
    private static final String cssDurationOfStudy = ".course-header2-bottom__content-item.container__col.container__col_4.container__col_md-4.container__col_ssm-12 .course-header2-bottom__item-text";

    public LessonsPage(WebDriver driver) {
        super(driver);;
    }

    public String getTitle(){
        return getTextByCss(cssTitle);
    }
    public String getDescription(){
        return getTextByCss(cssDescription);
    }
    public String getFormat(){
        return getTextByCss(cssFormat);
    }
    public String getDurationOfStudy(){
        return getTextByCss(cssDurationOfStudy);
    }


}
