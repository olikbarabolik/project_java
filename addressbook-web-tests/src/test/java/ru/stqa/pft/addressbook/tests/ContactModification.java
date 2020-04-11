package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withName("Test").withMiddlename("Testovich").withLastName("Testov").withAddress("My home"));
        }
    }

    @Test(enabled = false)
    public void testContactModification(){
        //Модификация имени, отчества и мобильного

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("5222Barabolik579").withLastName("Brook")
                .withMiddlename("Vladislavovna9").withAddress("The best street in the world").withEmail("mymail@gmail.com").withMobile("79611233211");
                app.contact().fillContactForm(contact);

        app.contact().modify(contact);
        Contacts after = app.contact().all();
        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    }
}
