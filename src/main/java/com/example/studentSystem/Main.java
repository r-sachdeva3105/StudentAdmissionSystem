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
        showLoginPage();
    }

    private void showLoginPage() {
        stage.setTitle("Login Page");
        Login login = new Login();
        Scene scene = new Scene(login.getView(), 500, 300);
        stage.setScene(scene);
        stage.show();
        login.setSignupAction(() -> showSignupPage());
    }

    private void showSignupPage() {
        stage.setTitle("Signup Page");
        SignUp signup = new SignUp();
        Scene scene = new Scene(signup.getView(), 500, 300);
        stage.setScene(scene);
        stage.show();
        signup.setSignupAction(() -> showLoginPage());
    }

    public static void main(String[] args) {
        launch();
    }
}