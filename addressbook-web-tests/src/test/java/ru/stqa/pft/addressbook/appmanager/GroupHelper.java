package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

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


    public void selectGroupById(int id){
        super.wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }


    public GroupData delete (GroupData group){
        selectGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
        return group;
    }

    public void deleteSelectedGroups(){
        click(By.name("delete"));
    }

    public void initgroupModification(){
        click(By.name("edit"));
    }

    public void submitGroupModification(){
        updateObject();
    }

    public void returnToGroupPage(){
        click(By.linkText("group page"));
    }


    public void create(GroupData groupData){
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group){
        selectGroupById(group.getId());
        initgroupModification();
        fillGroupForm(group);
        submitGroupModification();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup(){
        return isElementPresent(By.name("selected[]"));
    }

    public int count(){
       return super.count();

    }

    private Groups groupCache = null;

    public Groups all(){
        if (groupCache!=null){
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = super.wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){

            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData().withId(id).withName(name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }


}
