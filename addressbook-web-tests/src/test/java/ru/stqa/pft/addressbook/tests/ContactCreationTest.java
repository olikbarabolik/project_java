package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.gotoToContactPage();
        ContactData strData = new ContactData("Olga", "Vladislavovna", "Brook");
        app.fillContactForm(strData);
        app.submitContactGroup();
    }


}
