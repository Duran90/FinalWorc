package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObject.AbsPageObject;

public class LoginPage extends AbsPageObject {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public MainPage signIn(String email, String password){
        WebElement form = driver.findElement(By.xpath("//form[@action = '/login/']"));
        WebElement inputEmail = form.findElement(By.xpath(".//input[@name = 'email']"));
        WebElement inputPassword = form.findElement(By.xpath(".//input[@name = 'password']"));
        WebElement submit = form.findElement(By.xpath(".//button[@type = 'submit']"));
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        submit.submit();
        return new MainPage(driver);
    }
}
