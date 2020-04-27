package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class AddGroupToContact extends TestBase{

    @Test
    public void testAddGroupToContact(){


        Contacts before = app.db().contacts();

        ContactData modifiedContact = before.iterator().next();

        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName(modifiedContact.getFirstName()).withLastName(modifiedContact.getLastName());

        app.contact().selectContactById(modifiedContact.getId());

        app.contact().submitAddTo();
        app.contact().homePage();

        assertThat(app.contact().count(), equalTo(before.size()));
        //assertEquals(groupsAfter.size(), groupsBefore.size()+1);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


    }

}
