package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase{

    @Test
    public void testContactModification(){
        //Модификация имени, отчества и мобильного
        if (!app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Test", "Testovich", "Testov", "My home", null, null));
        }
        app.getContactHelper().initContactModification();
        ContactData strData = new ContactData("55Olga55", "Vladislavovna9", "Brook", "The best street in the world", "mymail@gmail.com", "+79611233211");
         app.getContactHelper().fillContactForm(strData);
        app.getContactHelper().submitContactModification();


    }
}
