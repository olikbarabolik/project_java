package ru.stqa.pft.addressbook.model;

public class ContactData {

    private String firstname;
    private String middlename;
    private String lastname;

    public ContactData (String firstname, String middlename, String lastname){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
    }
    public String getFirstName (){
        return firstname;
    }
    public String getMiddleName(){
        return middlename;
    }
    public String getLastName(){
        return lastname;
    }
}



