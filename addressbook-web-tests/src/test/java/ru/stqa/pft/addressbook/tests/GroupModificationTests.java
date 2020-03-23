package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification(){
        app.gotoToGroupPage();
        app.selectGroup();
        app.initgroupModification();
        app.fillGroupForm(new GroupData("test1", "test2","test3"));
        app.submitGroupModification();
        app.returnToGroupPage();
    }


}
