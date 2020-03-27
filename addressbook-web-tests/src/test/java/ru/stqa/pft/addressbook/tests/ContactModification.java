package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testGroupModification(){
        //Модификация имени, отчества и мобильного
        app.getContactHelper().initContactModification();
        ContactData strData = new ContactData("55Olga55", "Vladislavovna9", "Brook", "The best street in the world", "mymail@gmail.com", "+79611233211");
         app.getContactHelper().fillContactForm(strData);
        app.getContactHelper().submitContactModification();


    }
}
