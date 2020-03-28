package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase{
    @Test
    public void testGroupCreation(){
        app.getNavigateHelper().gotoToGroupPage();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2","test3"));
        /**/
    }
}
