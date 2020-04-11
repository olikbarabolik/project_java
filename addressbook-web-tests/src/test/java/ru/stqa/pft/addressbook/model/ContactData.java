package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstname;
    private String lastname;

    private String middlename;
    private String address;
    private String email;
    private String mobile;

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
    public String getMobile(){ return mobile;}

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

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }



}



