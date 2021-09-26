package com.example.loginversion_03;

public class MainModelAdmin {

    String fName, fPrice, furl;

    MainModelAdmin(){

    }

    public MainModelAdmin(String fName, String fPrice, String furl) {
        this.fName = fName;
        this.fPrice = fPrice;
        this.furl = furl;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getfPrice() {
        return fPrice;
    }

    public void setfPrice(String fPrice) {
        this.fPrice = fPrice;
    }

    public String getFurl() {
        return furl;
    }

    public void setFurl(String furl) {
        this.furl = furl;
    }
}
