package ru.stqa.pft.mantis.tests;
import ru.stqa.pft.mantis.tests.TestBase;
import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

    @Test
    public void testRegistration(){
        //app.registration("user1", "user1@localhost.localdomain");
        app.registration();
    }
}
