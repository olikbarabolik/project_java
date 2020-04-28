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

        Set<Contacts> contactBefore1 = new HashSet<>();
        Set<Contacts> contactAfter1 = new HashSet<>();

        //какая группа открыта
        GroupData modifiedGroup = freeGroup.iterator().next();
        ContactData modifiedContact = before.iterator().next();
        int id = modifiedGroup.getId();//везде передаем данный id

        //получаем список контактов у группы "до"
        for ( GroupData result : groupAllBefore) {
            if (result.getId() == id){
                Contacts contactBefore = result.getContacts();//список "до"
                contactBefore1.add(result.getContacts());
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
                Contacts contactAfter = result1.getContacts().withAdded(modifiedContact);//список "после"
                contactAfter1.add(result1.getContacts());
            }
        }
        //и сравнить полученные списки
        assertThat(contactAfter1, equalTo(contactBefore1));



    }

}
