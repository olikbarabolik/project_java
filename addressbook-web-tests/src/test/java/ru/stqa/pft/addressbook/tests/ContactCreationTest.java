package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTest extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        app.contact().gotoToContactPage();
        ContactData contact = new ContactData().withLastName("Brook").withName("Olga").withMiddlename("Vladislavovna").withAddress("The best street in the world")
                .withEmail("mymail@gmail.com").withMobile("79171233211");
                app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }


}
