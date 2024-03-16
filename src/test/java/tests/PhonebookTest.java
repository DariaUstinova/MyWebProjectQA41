package tests;

import config.BaseTest;
import helpers.*;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import model.Contact;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.time.Duration;

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

    @Test
    @Description("User already exist. Login and add contact.")
    public void loginOfAnExistingUserAddContact() throws InterruptedException {
        Allure.description("User already exist. Login and add contact.!");

        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Step 1");
        LoginPage lPage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        Allure.step("Step 2");
        lPage.fillEmailField(PropertiesReader.getProperty("existingUserEmail")).fillPasswordField(PropertiesReader.getProperty("existingUserPassword")).clickByLoginButton();

        Allure.step("Step 3");
        MainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact(NameAndLastnameGenerator.generateName(),
                NameAndLastnameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(10,5,3),
                AddressGenerator.generateAddress(),
                "new description");
        newContact.toString();
        addPage.fillFormAndSave1(newContact);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue(contactsPage.getDataFromContactList(newContact));
        TakeScreen.takeScreenshot("screen");
        Thread.sleep(3000);

    }
    @Test
    public void registrationPositive(@Optional("chrome") String browser) throws InterruptedException{
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lp = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lp.fillEmailField(EmailGenerator.generateEmail(5,5,3)).fillPasswordField(PasswordStringGenerator.generateString()).clickByRegistrationButton2();
        ContactsPage cp = new ContactsPage(getDriver());
        Thread.sleep(3000);
        Assert.assertTrue(getDriver().findElements(By.tagName("button")).size()>0);

    }
    // Or from Michail:
    @Test
    @jdk.jfr.Description("Successful Registration")
    public void successfulRegistration(){
        Allure.description("Successful Registration test.");
        MainPage mainPage = new MainPage(getDriver());
        Allure.step("Open LOGIN menu");
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lpage.fillEmailField(EmailGenerator.generateEmail(5,5,3))
                .fillPasswordField(PasswordStringGenerator.generateString());
        Alert alert =  lpage.clickByRegistrationButton();
        if (alert==null){
            ContactsPage contactsPage = new ContactsPage(getDriver());
            Assert.assertTrue( contactsPage. isElementPersist(getDriver()
                    .findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));
        }else {
            TakeScreen.takeScreenshot("UnSuccessful Registration");}
    }
    @Test
    public void deleteContactApproachTwo() throws IOException {
        String filename = "contactDataFile.ser";
        MainPage mainPage = new MainPage(getDriver());
        LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
        lpage.fillEmailField(PropertiesReader.getProperty("existingUserEmail"))
                .fillPasswordField(PropertiesReader.getProperty("existingUserPassword"))
                .clickByLoginButton();
        MainPage.openTopMenu(TopMenuItem.ADD.toString());
        AddPage addPage = new AddPage(getDriver());
        Contact newContact = new Contact(NameAndLastnameGenerator.generateName(),NameAndLastnameGenerator.generateLastName(),
                PhoneNumberGenerator.generatePhoneNumber(),
                EmailGenerator.generateEmail(10,5,3),
                AddressGenerator.generateAddress(), "Test description");
        addPage.fillFormAndSave1(newContact);
        Contact.serializeContact(newContact, filename);
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Contact deserContact = Contact.deserializeContact(filename);
        Assert.assertNotEquals(contactsPage.deleteContactByPhoneNumberOrName(deserContact.getPhone()),
                contactsPage.getContactsListSize());

    }
    @Test
@jdk.jfr.Description("Registration of an already registered user")
 public void registrationOfAnAlreadyRegisteredUser(){
    Allure.description("Successful Registration test.");
    MainPage mainPage = new MainPage(getDriver());
    Allure.step("Open LOGIN menu");
    LoginPage lpage = mainPage.openTopMenu(TopMenuItem.LOGIN.toString());
//    String emailSave = "test5@test.com";
//    String pwdSave = "Codirovka84!";
    String emailSave = EmailGenerator.generateEmail(5,5,3);
    String pwdSave = PasswordStringGenerator.generateString();
    PropertiesWriter.writeProperties(emailSave, pwdSave, true);
    lpage.fillEmailField(emailSave)
            .fillPasswordField(pwdSave);
    Alert alert1 =  lpage.clickByRegistrationButton();
    if (alert1==null){
        ContactsPage contactsPage = new ContactsPage(getDriver());
        Assert.assertTrue( contactsPage. isElementPersist(getDriver()
                .findElement(By.xpath("//button[contains(text(),'Sign Out')]"))));
    }else {
       // TakeScreen.takeScreenshot("UnSuccessful Registration");
        System.out.println("mistake1");}

    lpage.clickBySignOutButton();
    lpage.fillEmailField(emailSave).fillPasswordField(pwdSave);
  //  lpage.fillEmailField(PropertiesReader.getProperty(emailSave).fillPasswordField(PropertiesReader.getProperty(pwdSave)); //reads incorrectly
    Alert alert2 =  lpage.clickByRegistrationButton();
    if (alert2!=null){
    System.out.println(alert2.getText());
    alert2.accept();
    }else {
      //  TakeScreen.takeScreenshot("UnSuccessful Registration");
        System.out.println("mistake2");}
}
}

