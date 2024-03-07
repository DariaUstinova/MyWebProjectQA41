package tests;

import config.BaseTest;
import helpers.AlertHandler;
import helpers.TopMenuItem;
import io.qameta.allure.Allure;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.AddPage;
import pages.BasePage;
import pages.LoginPage;
import pages.MainPage;

public class PhonebookTest extends BaseTest {

    @Test(description = "The test checks the empty field warning declaration.")
    @Parameters("browser")
    public void registrationWithoutPassword(@Optional("chrome") String browser) throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");

        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Click by Login button");
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Click by Reg button");
        String expectedString = "Wrong";

        Alert alert= loginPage.fillEmailField("myemail@mail.com").clickByRegistrationButton();
        boolean isAlertHandled = AlertHandler.handleAlert(alert, expectedString);
        Assert.assertTrue(isAlertHandled);
    }
    @Test(description = "The test checks successful Login " +
            "and creation of a new contact on ContactsPage.")
    @Parameters("browser")
    public void LoginPossitive(@Optional("chrome") String browser) throws InterruptedException {
        Allure.description("User's login");

        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Click by Login button");
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Click by Login button");
        BasePage basePage = loginPage.fillEmailField("myemailtest@mail.com").fillPasswordField("Testtest84!").clickByLoginButton();


        AddPage addPage =  mainPage.openTopMenu(TopMenuItem.ADD.toString());
        //new AddPage(getDriver());
        addPage.fillFormAndSave();






    }

}
