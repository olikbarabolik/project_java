package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.getNavigateHelper().gotoToContactPage();
        ContactData strData = new ContactData("Olga", "Vladislavovna", "Brook", "The best street in the world", "mymail@gmail.com", "+79171233211");
        app.getContactHelper().fillContactForm(strData);
        app.getContactHelper().submitContactGroup();
    }


}
