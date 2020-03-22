package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class GroupCreationTest extends TestBase{
    @Test
    public void testGroupCreation(){

        app.gotoToGroupPage();
        app.initGroupCreation();
        app.fillGroupForm(new GroupData("test1", "test2","test3"));
        app.submitGroupCreation();
        app.returnToGroupPage();
    }
}
