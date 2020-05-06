package ru.stqa.pft.mantis.tests;

import ru.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import ru.stqa.pft.mantis.tests.TestBase;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import java.io.IOException;
import java.util.List;

import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.Message;
//import javax.mail.Message;
import javax.mail.internet.MimeMessage;

import ru.lanwen.verbalregex.VerbalExpression;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;

public class RegistrationTests extends TestBase {


    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String email = String.format("user1%s@localhost", now);
        String user = String.format("user1%s", now);
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.registration().finish(confirmationLink,password);
        app.newSession().login(user,password);
        Assert.assertTrue(app.newSession().login(user,password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email){
        MailMessage mailMessage = mailMessages.stream().filter((m)->m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


}
