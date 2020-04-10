package ru.stqa.pft.addressbook.tests;



import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class ContactCreationTest extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().gotoToContactPage();
        ContactData contact = new ContactData("Olga", "Vladislavovna", "Brook", "The best street in the world", "mymail@gmail.com", "+79171233211");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        contact.setId(after.stream().max((o1,o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        //before = got, after = excepted

    }


}
