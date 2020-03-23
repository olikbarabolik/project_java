package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase {

    @Test
    public void testGroupDeletion() {
        app.selectGroup();
        app.deleteSelectedContact();
        app.clickOK();
    }
}

