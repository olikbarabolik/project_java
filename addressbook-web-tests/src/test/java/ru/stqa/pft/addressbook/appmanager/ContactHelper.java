package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;



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
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email"), contactData.getEmail());
        type(By.name("mobile"), contactData.getMobile());
    }

    public void deleteSelectedContact(){
        click(By.xpath("//input[@value='Delete']"));
    }

    public void clickOK(WebDriver wd){
        wd.manage().timeouts().implicitlyWait(1300, TimeUnit.SECONDS);
        wd.switchTo().alert().accept();
    }

    public void initContactModification(){

        click(By.xpath("//img[@alt='Edit']"));
    }



}