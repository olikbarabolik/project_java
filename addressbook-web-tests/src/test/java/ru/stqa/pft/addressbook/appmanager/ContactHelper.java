package ru.stqa.pft.addressbook.appmanager;


import org.testng.Assert;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactHelper extends HelperBase {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactGroup(){
        click((By.xpath("(//input[@name='submit'])[2]")));
    }


    public void fillContactForm(ContactData contactData) {
        System.out.println("!!!!! contactData" + contactData.getFirstName());
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());

    }


    public void deleteSelectedContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickOK(){
        super.clickOK();
    }

    public void initContactModification(int index){
        click(By.xpath("//a[@href='edit.php?id="+ index +"']"));
    }

    public void submitContactModification(){
        updateObject();
        returnToContactPage();
    }

    public void selectContact(int index){
        super.selectContact(index);
    }

    public void createContact(ContactData contactData){
        System.out.println("2222");
        gotoToContactPage();
        fillContactForm(contactData);
        submitContactGroup();
        returnToContactPage();

    }

    public void gotoToContactPage() {
        click(By.linkText("add new"));
    }

    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    public boolean isThereAContact(){
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = super.wd.findElements(By.name("entry"));
        for (WebElement element : elements){//перебор строки
            List<WebElement>  cells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    public int getContactCount(){
        return super.getContactCount();

    }
}