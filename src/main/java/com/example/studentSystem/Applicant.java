package com.example.studentSystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Applicant {
    private IntegerProperty applicantID;
    private StringProperty applicantName;
    private StringProperty applicantPhone;


    private StringProperty applicantEmail;
    private StringProperty program1;
    private StringProperty program2;
    private StringProperty program3;

    private StringProperty gpa;
    private StringProperty street;
    private StringProperty city;
    private StringProperty country;
    private StringProperty dob;
    private StringProperty gender;

    // Constructor
    public Applicant() {
        // Initialize properties here if needed
        this.applicantID = new SimpleIntegerProperty();
        this.applicantName = new SimpleStringProperty();
        this.applicantPhone = new SimpleStringProperty();
        this.applicantEmail = new SimpleStringProperty();
        this.program1 = new SimpleStringProperty();
        this.program2 = new SimpleStringProperty();
        this.program3 = new SimpleStringProperty();
        this.gpa = new SimpleStringProperty();
        this.street = new SimpleStringProperty();
        this.city = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.dob = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
    }
    public Applicant(int applicantID, String applicantName, String applicantPhone, String applicantEmail) {
        this.applicantID = new SimpleIntegerProperty(applicantID);
        this.applicantName = new SimpleStringProperty(applicantName);
        this.applicantPhone = new SimpleStringProperty(applicantPhone);
        this.applicantEmail = new SimpleStringProperty(applicantEmail);
    }



    public Applicant(String program1, String program2, String program3) {
        this.program1 = new SimpleStringProperty(program1);
        this.program2 = new SimpleStringProperty(program2);
        this.program3 = new SimpleStringProperty(program3);
    }


    public Applicant(int applicantID, String applicantName, String applicantPhone, String applicantEmail,
                     String program1, String program2, String program3, String dob, String gender) {
        this.applicantID = new SimpleIntegerProperty(applicantID);
        this.applicantName = new SimpleStringProperty(applicantName);
        this.applicantPhone = new SimpleStringProperty(applicantPhone);
        this.applicantEmail = new SimpleStringProperty(applicantEmail);
        this.program1 = new SimpleStringProperty(program1);
        this.program2 = new SimpleStringProperty(program2);
        this.program3 = new SimpleStringProperty(program3);
        this.dob = new SimpleStringProperty(dob);
        this.gender = new SimpleStringProperty(gender);
    }


    public IntegerProperty getApplicantID() {
        return applicantID;
    }

    public StringProperty getApplicantName() {
        return applicantName;
    }

    public StringProperty getApplicantPhone() {
        return applicantPhone;
    }

    public StringProperty getApplicantEmail() {
        return applicantEmail;
    }

    public StringProperty getProgram1() {
        return program1;
    }

    public StringProperty getProgram2() {
        return program2;
    }

    public StringProperty getProgram3() {
        return program3;
    }

    public StringProperty getDob() {
        return dob;
    }

    public StringProperty getGender() {
        return gender;
    }

    public StringProperty getGpa() {
        return gpa;
    }
    public StringProperty getStreet() {
        return street;
    }
    public StringProperty getCity() {
        return city;
    }
    public StringProperty getCountry() {
        return country;
    }

    public void setGpa(String gpa) {
        this.gpa.set(gpa);
    }
    public void setStreet(String street) {
        this.street.set(street);
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public void setCountry(String country) {
        this.country.set(country);
    }


    public void setApplicantID(int applicantID) {
        this.applicantID.set(applicantID);
    }

    public void setApplicantName(String applicantName) {
        this.applicantName.set(applicantName);
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone.set(applicantPhone);
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail.set(applicantEmail);
    }

    public void setProgram1(String program1) {
        this.program1.set(program1);
    }

    public void setProgram2(String program2) {
        this.program2.set(program2);
    }

    public void setProgram3(String program3) {
        this.program3.set(program3);
    }

    public void setDob(String dob) {
        this.dob.set(dob);
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }


}
