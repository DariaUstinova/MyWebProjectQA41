package tests;

import config.BaseTest;
import helpers.TopMenuItem;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

public class PhonebookTest extends BaseTest {

    @Test
    public  void phoneBookTest_001(){
        MainPage mainPage = new MainPage(getDriver());
        LoginPage loginPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());


      //  loginPage.fillEmailField("myemail@mail.com").clickByRegistrationButton();
        loginPage.fillEmailField("daria.ystinova84@gmail.com").fillPasswordField("Codirovka84!").clickByLoginButton();


        // Thread.sleep(5000);


    }
}
