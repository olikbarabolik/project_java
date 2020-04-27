package ru.stqa.pft.addressbook.model;

import java.beans.Transient;


import java.io.Serializable;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.sun.javafx.beans.IDProperty;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import javax.persistence.ManyToMany;

@Entity
@Table(name="addressbook")
public class ContactData {

    @Id
    @Column(name="id")
    private int id;

    @Expose
    @Column(name="firstname")
    private String firstname;

    @Expose
    @Column(name="lastname")
    private String lastname;

    @Expose
    transient private String middlename;

    @Expose
    transient private String address;
    @Expose
    transient private String email;

    //@Transient
    //transient private String group;

    @Expose
    @Column(name="mobile")
    @Type(type = "text")
    private String mobilePhone;

    @Column(name="home")
    @Type(type = "text")
    private String homePhone;

    @Column(name="work")
    @Type(type = "text")
    private String workPhone;

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name="id"), inverseJoinColumns = @JoinColumn(name="group_id"))
    private Set<GroupData> groups = new HashSet<>();

    public Groups getGroups() {
        return new Groups(groups);
    }

    public void withGroup(Set<GroupData> groups) {
        this.groups = groups;
        //return groups;
    }

    //@Transient
    transient private String allPhones;
    transient  private String allEmail;

    transient private String email2;
    transient private String email3;

    @Column(name="photo")
    @Type(type = "text")
    private String photo;


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
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
    public File getPhoto() { return new File(photo);  }

    /*photo*/

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

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



