package com.example.black.mysecrettodo.model;

public class User {

    int id;
    String password;

    public User(){

    }

    // constructors
    public User(String note) {
        this.id = id;
        this.password = note;
    }

    public User(int id, String note) {
        this.id = id;
        this.password = note;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String note) {
        this.password = note;
    }

    // getters
    public long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

}