package ru.stqa.pft.mantis.tests;

import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;

import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.tests.TestBase;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import  javax.mail.MessagingException;

import static org.testng.Assert.assertTrue;
import org.testng.Assert;

import java.io.IOException;


public class ChangePasswordTest extends TestBase {


    @Test
    public void testRegistration() throws IOException, MessagingException, IOException {
        app.getChangePasswordHelper().loginToPage();
        app.getChangePasswordHelper().goTopage();
        UserData userTest = new UserData();
        userTest = app.getChangePasswordHelper().changePassword();
        Assert.assertTrue(app.newSession().login(userTest.getUsername(), userTest.getPassword()));
    }


}
