package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactGroup(){
        click((By.xpath("(//input[@name='submit'])[2]")));
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobilePhone());
        //attach(By.name("photo"), contactData.getPhoto());

    }


    public void deleteSelectedContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickOK(){
        super.clickOK();
    }

    public void initContactModificationById(int index){
        click(By.xpath("//a[@href='edit.php?id="+ index +"']"));
    }

    public void submitContactModification(){
        updateObject();
        returnToContactPage();
    }

    public void submitAddTo(){
        click(By.xpath("//input[@value='Add to']"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }

    public void submitRemoveTo() {
        click(By.xpath("//input[@name='remove']"));
    }

    public void selectContact(int index){
        super.selectContact(index);
    }

    public void create(ContactData contactData){
        gotoToContactPage();
        fillContactForm(contactData);
        submitContactGroup();
        contactCache= null;
        returnToContactPage();

    }

    public void modify(ContactData contactData){
        initContactModificationById(contactData.getId());
        fillContactForm(contactData);
        contactCache= null;
        submitContactModification();
    }

    public void clickToGroup(int id){
        click(By.xpath("//select[@name='group']"));
        chooseBySelect(By.name("group"), id);
      }



    public ContactData delete (ContactData contact){
        selectContactById(contact.getId());
        deleteSelectedContact();
        clickOK();
        contactCache= null;
        return contact;
    }

    public void selectContactById(int id) {
        super.wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    }

    public Set<Contacts> getContactGroup(Groups groupAllBefore, int id){
        Set<Contacts> contactBefore1 = new HashSet<>();
        for ( GroupData result : groupAllBefore) {
            if (result.getId() == id){
                Contacts contactBefore = result.getContacts();//список "до"
                contactBefore1.add(result.getContacts());
            }
        }
        return contactBefore1;
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

    private Contacts contactCache = null;

    public Contacts all(){
        if (contactCache!=null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();

        Contacts contacts = new Contacts();
        List<WebElement> elements = super.wd.findElements(By.name("entry"));
        for (WebElement element : elements){//перебор строки
            List<WebElement>  cells = element.findElements(By.cssSelector("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmail = cells.get(4).getText();
            String address = cells.get(3).getText();
            ContactData contact = new ContactData().withId(id).withName(firstname).withLastName(lastname).withAllPhones(allPhones).withAddress(address).withAllEmail(allEmail);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public int count(){
        return super.count();

    }

    public ContactData infoFromEditForm(ContactData contact){

        initContactModificationById(contact.getId());
        String firstname = super.wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = super.wd.findElement(By.name("lastname")).getAttribute("value");
        String home = super.wd.findElement(By.name("home")).getAttribute("value");
        String mobile = super.wd.findElement(By.name("mobile")).getAttribute("value");
        String work = super.wd.findElement(By.name("work")).getAttribute("value");
        String address = super.wd.findElement(By.name("address")).getAttribute("value");
        String email = super.wd.findElement(By.name("email")).getAttribute("value");
        String email2 = super.wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = super.wd.findElement(By.name("email3")).getAttribute("value");

        super.wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstname).withLastName(lastname).withHomePhone(home).withMobile(mobile).withWorkPhone(work)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}