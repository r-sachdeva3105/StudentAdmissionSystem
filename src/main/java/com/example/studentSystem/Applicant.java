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

    // Constructor
    public Applicant(int applicantID, String applicantName, String applicantPhone, String applicantEmail) {
        this.applicantID = new SimpleIntegerProperty(applicantID);
        this.applicantName = new SimpleStringProperty(applicantName);
        this.applicantPhone = new SimpleStringProperty(applicantPhone);
        this.applicantEmail = new SimpleStringProperty(applicantEmail);
    }

    private StringProperty program1;
    private StringProperty program2;
    private StringProperty program3;

    public Applicant(String program1, String program2, String program3) {
        this.program1 = new SimpleStringProperty(program1);
        this.program2 = new SimpleStringProperty(program2);
        this.program3 = new SimpleStringProperty(program3);
    }

    private StringProperty dob;
    private StringProperty gender;


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

}
