package ru.stqa.pft.addressbook.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.security.acl.Group;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class DeleteGroupFromContact extends TestBase {

    //убрать захардкоженную группу и взять ее из списка
    //брать список контактов данной группы
    //сравнить контакт
    //для модификации аналогично

    @Test
    public void testDeleteGroupToContact(){

        //взять первую свободную группу с контактами и передать ее

        Groups groupAllBefore = app.db().groups();//получаем общий список групп
        Set<GroupData> freeGroup = new HashSet<>();
        //находим группы, в которых есть контакты
        for ( GroupData result : groupAllBefore) {
            if (result.getContacts().size()!=0){
                freeGroup.add(result);
            }
         }


        GroupData deletedGroup = freeGroup.iterator().next();
        int id = deletedGroup.getId();//везде передаем данный id

        ContactData deletedContact = deletedGroup.getContacts().iterator().next();//удаленный контакт

        app.contact().clickToGroup(id);//выбираем группу
        Set<Contacts> contactBefore1 = new HashSet<>();
        Set<Contacts> contactAfter1 = new HashSet<>();

        //получаем список контактов у группы до удаления
        for ( GroupData result : groupAllBefore) {

            if (result.getId() == id){
                Contacts contactBefore = result.getContacts();//список "до"
                contactBefore1.add(result.getContacts().without(deletedContact));

            }
        }

        app.contact().selectContactById(deletedContact.getId());
        app.contact().submitRemoveTo();
        app.contact().homePage();

        Groups groupAllAfter = app.db().groups();
        //список контактов "после"
        for ( GroupData result1 : groupAllAfter) {
            if (result1.getId() == id){
                Contacts contactAfter = result1.getContacts();//список "после"
                contactAfter1.add(result1.getContacts());
            }
        }

        //и сравнить полученные списки
        assertThat(contactAfter1, equalTo(contactBefore1));


    }
}
