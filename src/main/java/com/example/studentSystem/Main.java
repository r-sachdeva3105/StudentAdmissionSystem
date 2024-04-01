package com.example.studentSystem;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        stage.setTitle("Student Admission System");
        showLoginPage();
    }

    private void showLoginPage() {
        Login login = new Login();
        Scene scene = new Scene(login.getView());
        stage.setScene(scene);
        stage.show();
        login.setSignupAction(this::showSignupPage);
        login.setLoginAction(this::showAdminDashboardPage);
    }

    private void showSignupPage() {
        SignUp signup = new SignUp();
        Scene scene = new Scene(signup.getView());
        stage.setScene(scene);
        stage.show();
        signup.setLoginAction(this::showLoginPage);
        signup.setRegistrationAction(this::showRegistrationPage);
    }

    private void showRegistrationPage() {
        Registration registration = new Registration();
        Scene scene = new Scene(registration.getView());
        stage.setScene(scene);
        stage.show();
    }

    private void showRegistrarDashboardPage() {
        RegistrarDashboard registrar = new RegistrarDashboard();
        Scene scene = new Scene(registrar.getView());
        stage.setScene(scene);
        stage.show();
        registrar.setLogoutAction(this::showLoginPage);
    }

    private void showAdminDashboardPage() {
        AdminDashboard admin = new AdminDashboard();
        Scene scene = new Scene(admin.getView());
        stage.setScene(scene);
        stage.show();
        admin.setLogoutAction(this::showLoginPage);
    }

    public static void main(String[] args) {
        launch();
    }
}