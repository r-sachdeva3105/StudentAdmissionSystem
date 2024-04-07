package com.example.studentSystem;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Applicant {
    private final IntegerProperty applicantID;
    private final StringProperty applicantName;
    private final StringProperty applicantPhone;
    private final StringProperty applicantEmail;
//    private final StringProperty program1;
//    private final StringProperty program2;
//    private final StringProperty program3;

    // Constructor
    public Applicant(int applicantID, String applicantName, String applicantPhone, String applicantEmail) {
        this.applicantID = new SimpleIntegerProperty(applicantID);
        this.applicantName = new SimpleStringProperty(applicantName);
        this.applicantPhone = new SimpleStringProperty(applicantPhone);
//        this.program1 = new SimpleStringProperty(program1);
//        this.program2 = new SimpleStringProperty(program2);
//        this.program3 = new SimpleStringProperty(program3);
        this.applicantEmail = new SimpleStringProperty(applicantEmail);
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

//    public StringProperty getProgram1() {
//        return program1;
//    }
//
//    public StringProperty getProgram2() {
//        return program2;
//    }
//
//    public StringProperty getProgram3() {
//        return program3;
//    }
}
