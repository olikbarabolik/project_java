package ru.stqa.pft.addressbook.tests;



import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.thoughtworks.xstream.XStream;
import com.google.gson.Gson;
import com.google.common.reflect.TypeToken;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactCreationTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/ru/stqa/pft/addressbook/tests/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        String[] split = line.split(";");
        while (line != null){
            json+=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @Test(dataProvider = "validContactFromJSON")
    public void testContactCreation(ContactData contact) throws Exception {
        File photo = new File("src/test/java/ru/stqa/pft/addressbook/tests/resources/stru.png");
        Contacts before = app.contact().all();
        app.contact().gotoToContactPage();
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()+1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }




}
