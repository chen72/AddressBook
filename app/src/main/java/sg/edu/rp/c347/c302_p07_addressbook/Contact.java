package sg.edu.rp.c347.c302_p07_addressbook;

import java.io.Serializable;

public class Contact implements Serializable{
    private int id;
    private String firstname;
    private String lastname;
    private String home;
    private String mobile;
    private String address;
    private String country;
    private String postalcode;
    private String email;

    public Contact(){}

    public Contact(int id,String firstname,String lastname,String mobile){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobile = mobile;
    }

    public Contact(int id, String firstname, String lastname, String home, String mobile, String address, String country, String postalcode, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.home = home;
        this.mobile = mobile;
        this.address = address;
        this.country = country;
        this.postalcode = postalcode;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
