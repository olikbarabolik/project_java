package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteGroupFromContact extends TestBase {

    @Test
    public void testDeleteGroupToContact(){


        Contacts before = app.db().contacts();
        app.contact().clickToGroup();
        
        //Contacts before1 = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        //app.contact().selectContactById(deletedContact.getId());
        app.contact().selectContact(1);
        app.contact().submitRemoveTo();
        app.contact().homePage();
        Contacts after = app.db().contacts();
        //assertEquals(before.size(), after.size());
        //assertThat(after, equalTo(before.without(deletedContact)));
        //вернуться на главную


    }
}
