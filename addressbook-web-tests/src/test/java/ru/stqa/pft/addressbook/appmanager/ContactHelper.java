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
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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

    public void create(ContactData contactData){
        gotoToContactPage();
        fillContactForm(contactData);
        submitContactGroup();
        returnToContactPage();

    }

    public void modify(ContactData contactData){
        initContactModification(contactData.getId());
        fillContactForm(contactData);
        submitContactModification();
    }

    public ContactData delete (ContactData contact){
        selectContactById(contact.getId());
        deleteSelectedContact();
        clickOK();
        return contact;
    }

    private void selectContactById(int id) {
        super.wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
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

    public Contacts all(){
        Contacts contacts = new Contacts();
        List<WebElement> elements = super.wd.findElements(By.name("entry"));
        for (WebElement element : elements){//перебор строки
            List<WebElement>  cells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            //ContactData contact = new ContactData(id, firstname, null, lastname, null, null, null);
            ContactData contact = new ContactData().withId(id).withName(firstname).withLastName(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

    public int getContactCount(){
        return super.getContactCount();

    }
}