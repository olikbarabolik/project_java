package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstname;
    private String lastname;

    private String middlename;
    private String address;
    private String email;
    private String mobilePhone;

    private String homePhone;
    private String workPhone;

    private String allPhones;
    private String allEmail;

    private String email2;
    private String email3;



    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    public int getId (){
        return id;
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
    public String getMobilePhone(){ return mobilePhone;}
    public String getEmail2() {  return email2;   }
    public String getEmail3() { return email3;  }
    public String getAllEmail() { return allEmail;  }
    public String getAllPhones() {  return allPhones;    }
    public String getHomePhone() {  return homePhone;   }
    public String getWorkPhone() {  return workPhone;    }


    /* email */

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllEmail(String allEmail) {
        this.allEmail = allEmail;
        return this;
    }

    /*ид, имена*/

    public ContactData withId(int id){
        this.id = id;
        return this;
    }

    public ContactData withName(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastName(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    /* Адреса */

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    /* Мобильные */

    public ContactData withMobile(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }





}



