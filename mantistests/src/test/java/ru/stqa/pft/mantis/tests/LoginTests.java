package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import org.testng.SkipException;
import java.io.IOException;
import java.util.List;
import ru.stqa.pft.mantis.tests.TestBase;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;
import ru.stqa.pft.mantis.model.MailMessage;
import javax.xml.rpc.ServiceException;
public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException,ServiceException {
        try {
            skipIfNotFixed(1);
            //skipIfNotFixedRest(1);
        } catch (SkipException e) {
            e.printStackTrace();
        }
        HttpSession session = app.newSession();
        session.login("administrator", "root");
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));

    }
}
