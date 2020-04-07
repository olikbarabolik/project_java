package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {

    private int id;
    private String firstname;
    private String middlename;
    private String lastname;
    private String address;
    private String email;
    private String mobile;
    private String group;

    public ContactData (String firstname, String middlename, String lastname, String address, String email, String mobile){
        this.id = 0;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

    public ContactData (int id, String firstname, String middlename, String lastname, String address, String email, String mobile){
        this.id = id;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.mobile = mobile;
    }

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
    public String getGroup(){ return group;}

    public void setId(int id){
        this.id = id;
    }

}



