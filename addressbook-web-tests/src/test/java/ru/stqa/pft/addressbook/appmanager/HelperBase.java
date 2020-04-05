package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;

import org.openqa.selenium.NoSuchElementException;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;


public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public void type(By locator, String text){
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void selectObject(){
        click(By.name("selected[]"));
    }

    public void updateObject(){
        click(By.name("update"));
    }

    public void clickOK(){
        wd.switchTo().alert().accept();
    }

    public boolean isElementPresent(By locator){
        try{
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){
            return false;
        }
    }

    public List<GroupData> getGroupList(){
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){

            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            GroupData group = new GroupData(id, name, null, null);
            groups.add(group);
        }
        return groups;
    }

    public int getGroupCount(){
       return wd.findElements(By.name("selected[]")).size();
    }

    public int getContactCount(){
        return wd.findElements(By.name("selected[]")).size();
    }

    public void selectGroup(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectContact(int index){
        wd.findElements(By.name("selected[]")).get(index).click();
    }


    protected List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element : elements){//перебор строки
             List<WebElement>  cells = element.findElements(By.cssSelector("td.center + td + td")); //перебор столбцов внутри
                for (WebElement cells2 : cells) {
                    int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                    String firstname = cells2.getText();
                    if ((firstname.length()>0)){//значение непустое

                        ContactData contact = new ContactData(id, firstname, null, null, null, null, null);
                        contacts.add(contact);
                    }
                }
        }
        return contacts;
    }

}
