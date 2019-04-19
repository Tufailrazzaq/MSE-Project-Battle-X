package com.example.omen.battlex;

public class TeamMember {

    private String name;
    private String nic;
    private String contactNo;
    private String email;

    public TeamMember() {
    }

    public TeamMember(String name, String nic, String contactNo, String email) {
        this.name = name;
        this.nic = nic;
        this.contactNo = contactNo;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getNic() {
        return nic;
    }

    public String getContactNo() {
        return contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
