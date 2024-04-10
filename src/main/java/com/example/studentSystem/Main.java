package com.example.studentSystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    public static final String DATABASE_NAME = "student_db";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "jdbc3306";
    public static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME;
    private static Stage stage;
    public static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Student Admission System");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DATABASE_NAME, DB_USERNAME, DB_PASSWORD);
            showLoginPage();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void showLoginPage() {
        Login login = new Login(connection);
        Scene scene = new Scene(login.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Login");
        stage.show();
    }

    public static void showSignupPage() {
        SignUp signup = new SignUp();
        Scene scene = new Scene(signup.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Signup");
        stage.show();
    }

    public static void showRegistrationPage(int id) {
        Registration registration = new Registration(id);
        Scene scene = new Scene(registration.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Applicant Registration");
        stage.show();
    }

    public static void showRegistrationRegistrarPage(int id, UserData userData) {
        RegistrationRegistrar register = new RegistrationRegistrar(id, userData);
        Scene scene = new Scene(register.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Registrar Registration");
        stage.show();
    }

    public static void showRegistrarDashboardPage(UserData userData) {
        RegistrarDashboard registrar = new RegistrarDashboard(userData);
        Scene scene = new Scene(registrar.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Registrar");
        stage.show();
    }

    public static void showAdminDashboardPage(UserData userData) {
        AdminDashboard admin = new AdminDashboard(userData);
        Scene scene = new Scene(admin.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Admin");
        stage.show();
    }

    public static void showApplicantDashboardPage(UserData userData) {
        ApplicantDashboard applicant = new ApplicantDashboard(userData);
        Scene scene = new Scene(applicant.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Applicant");
        stage.show();
    }

    public static void showUpdateApplicantPage(int id, UserData userData) {
        UpdateApplicant update = new UpdateApplicant(id, userData);
        Scene scene = new Scene(update.getView());
        stage.setScene(scene);
        stage.setTitle("Student Admission System - Update Applicant");
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}