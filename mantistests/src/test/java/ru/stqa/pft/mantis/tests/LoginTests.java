package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;
import static org.testng.Assert.assertTrue;
import org.testng.Assert;

public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        //Assert.assertTrue(session.login("administrator", "root"));
        //Assert.assertTrue(session.isLoggedInAs("administrator"));

    }
}
