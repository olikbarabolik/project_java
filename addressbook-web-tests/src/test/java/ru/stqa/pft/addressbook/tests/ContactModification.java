package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification(){
        //Модификация имени, отчества и мобильного
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Test", "Testovich", "Testov", "My home", null, null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().initContactModification();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(), "55Olga55", "Vladislavovna9", "Brook", "The best street in the world", "mymail@gmail.com", "+79611233211");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());


        before.add(contact);
        before.remove(before.size()-1);

        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


    }
}
