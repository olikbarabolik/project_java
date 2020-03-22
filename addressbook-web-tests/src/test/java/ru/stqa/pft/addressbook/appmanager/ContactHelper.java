package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.tests.ContactData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        //super.wd = wd;
        super(wd);
    }

    public void submitContactGroup(){
        click((By.xpath("(//input[@name='submit'])[2]")));
    }


    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());

    }


}