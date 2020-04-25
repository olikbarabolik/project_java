package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.thoughtworks.xstream.XStream;
import com.google.gson.Gson;
import com.google.common.reflect.TypeToken;


public class GroupCreationTest extends TestBase{

    @DataProvider
    public Iterator<Object[]> validGroupsFromString() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header1").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header2").withFooter("footer 1")});
        list.add(new Object[] {new GroupData().withName("test1").withHeader("header2").withFooter("footer 3")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/ru/stqa/pft/addressbook/tests/resources/groups.csv")));
        String xml = "";
        String line = reader.readLine();
        while (line != null){
            String[] split = line.split(";");
            list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
            line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/ru/stqa/pft/addressbook/tests/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.alias("group",GroupData.class);
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/java/ru/stqa/pft/addressbook/tests/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        String[] split = line.split(";");
        while (line != null){
            json+=line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validGroupsFromJSON")
    public void testGroupCreation(GroupData group){
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()+1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void testBadGroupCreation(){
        app.goTo().groupPage();
        Groups before = app.db().groups();
        GroupData group = new GroupData().withName("test3'");
        app.group().create(group);

        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));

    }


}
