package ru.stqa.pft.addressbook.model;

public class ContactData {

    private String firstname;
    private String middlename;
    private String lastname;
    private String address;
    private String email;
    private String mobile;
    private String work;

    public ContactData (String firstname, String middlename, String lastname, String address, String email, String mobile){
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
        this.work = work;

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
    public String getAddress(){
        return address;
    }
    public String getEmail(){
        return email;
    }
    public String getMobile(){ return mobile;}
    public String getWork(){ return work;}
}



