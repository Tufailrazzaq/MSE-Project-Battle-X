package com.example.omen.battlex;

public class ContactUsMessage {
    private String name;
    private String email;
    private String contactNo;
    private String message;
    private String id;

    public ContactUsMessage(String key, String name, String email, String contactNo, String message) {
        this.name = name;
        this.email = email;
        this.contactNo = contactNo;
        this.message = message;
        this.id = key;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getMessage() {
        return message;
    }
}
