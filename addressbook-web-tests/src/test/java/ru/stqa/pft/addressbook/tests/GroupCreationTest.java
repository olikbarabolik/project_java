package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation(){
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()+1));
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }
}
