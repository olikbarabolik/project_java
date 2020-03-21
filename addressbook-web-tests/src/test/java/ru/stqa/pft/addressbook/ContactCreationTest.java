package ru.stqa.pft.addressbook;



import org.testng.annotations.*;



public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation() throws Exception {
        app.gotoToContactPage();
        ContactData strData = new ContactData("Olga", "Vladislavovna", "Brook");
        app.fillContactForm(strData);
        app.submitContactGroup();
    }


}
