package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.tests.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class GroupHelper extends HelperBase {

    private WebDriver wd;

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void fillGroupForm(GroupData groupData){
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());

    }

    public void submitGroupCreation(){

            click(By.name("submit"));
    }

    public void selectGroup(){
        click(By.name("selected[]"));
    }

    public void deleteSelectedGroups(){
        click(By.name("delete"));
    }


}
