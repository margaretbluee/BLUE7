package com.example.blue7;

public class User {
    public String userName;
    public String userPassword;
    public String userEmail;

    public User(){

    }

    public User(String name, String password, String email){
        this.userName=name;
        this.userPassword=password;
        this.userEmail=email;
    }
}

// ...
// Initialize Firebase Auth
//mAuth = FirebaseAuth.getInstance();