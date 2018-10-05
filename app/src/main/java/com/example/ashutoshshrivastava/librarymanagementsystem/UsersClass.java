package com.example.ashutoshshrivastava.librarymanagementsystem;

public class UsersClass {
    String name;
    String emailText;
    String mobnumberText;

   public UsersClass(){

   }

    public String getName() {
        return name;
    }

    public String getEmailText() {
        return emailText;
    }

    public String getMobnumberText() {
        return mobnumberText;
    }

    public UsersClass(String  name, String emailText , String mobnumberText) {
        this.name=name;
        this.emailText=emailText;
        this.mobnumberText=mobnumberText;

    }
}
