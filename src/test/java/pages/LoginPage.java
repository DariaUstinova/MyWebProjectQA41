/* Задача*: дописать класс LoginPage* используя аннотации @FindBy
, создать два метода: fillPasswordField и clickByLoginButton по аналогии с fillEmailField и clickByRegisterButton*/
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailField;

    @FindBy(xpath = "//button[@name='registration']")
    WebElement registrationButton;

    @FindBy(xpath = "//input[@name='password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    WebElement loginButton;

    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 20), this);

    }

    public LoginPage fillEmailField(String email) {
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage clickByRegistrationButton(){
        registrationButton.click();
        return this;
    }

    public LoginPage fillPasswordField(String password){
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage clickByLoginButton(){
        loginButton.click();
        return this;
    }
}
