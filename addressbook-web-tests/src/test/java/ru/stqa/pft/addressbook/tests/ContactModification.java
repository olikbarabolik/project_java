package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testGroupModification(){
        app.initContactModification();
        ContactData strData = new ContactData("55Olga55", "Vladislavovna", "Brook");
        app.fillContactForm(strData);
        app.submitGroupModification();
    }
}
