package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class AddGroupToContact extends TestBase{

    //проверка наличия контакта
    @BeforeMethod
    public void ensurePreconditionsContact(){
        if (app.db().contacts().size() == 0){
            app.contact().create(new ContactData().withName("Test").withMiddlename("Testovich").withLastName("Testov").withAddress("My home"));
        }
    }

    //проверка наличия группы
    @BeforeMethod
    public void ensurePreconditionsGroup(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withHeader("test1"));
        }
    }

    @Test
    public void testAddGroupToContact(){

        Contacts before = app.db().contacts();
        Groups groupAllBefore = app.db().groups(); //получаем общий список групп

        Set<GroupData> freeGroup = new HashSet<>();
        //находим группу без контактов
        for ( GroupData result : groupAllBefore) {
            if (result.getContacts().size()==0){
                freeGroup.add(result);
            }
        }

        //если все группы добавлены, делаем новый контакт с группой
        if (freeGroup.size()==0){
            app.contact().create(new ContactData().withName("Test41").withMiddlename("Testovich").withLastName("Testov").withAddress("My home"));
            //Добавить
            for ( GroupData result : groupAllBefore) {
                freeGroup.add(result);

            }
        }

        Set<Contacts> contactBefore1 = new HashSet<>();
        Set<Contacts> contactAfter1 = new HashSet<>();

        //какая группа открыта
        GroupData modifiedGroup = freeGroup.iterator().next();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedGroup.getId();//везде передаем данный id

        //получаем список контактов у группы "до"
        for ( GroupData result : groupAllBefore) {
            if (result.getId() == id){
                Contacts contactBefore = result.getContacts().withAdded(modifiedContact);//список "до"
                contactBefore1.add(contactBefore);
            }
        }


        /* Добавляем группу*/
        app.contact().selectContactById(modifiedContact.getId());
        app.contact().submitAddTo();
        app.contact().homePage();

        Groups groupAllAfter = app.db().groups();
        //список контактов "после"
        Groups groupContact =  modifiedContact.getGroups();
        for ( GroupData result1 : groupAllAfter) {
            if (result1.getId() == id){
                //Contacts contactAfter = result1.getContacts().withAdded(modifiedContact);//список "после"
                Contacts contactAfter = result1.getContacts();//список "после"
                contactAfter1.add(contactAfter);
            }
        }
        //и сравнить полученные списки
        assertThat(contactAfter1, equalTo(contactBefore1));



    }

}
