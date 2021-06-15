package com.example.login2;

public class academies {
    public String email, academyname, location;

    public academies() {

    }

    public academies(String email, String academyname, String location) {
        this.email = email;
        this.academyname = academyname;
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcademyname() {
        return academyname;
    }

    public void setAcademyname(String academyname) {
        this.academyname = academyname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
