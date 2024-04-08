package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.example.studentSystem.Main.connection;

public class SignUp {
    private final GridPane grid;
    private Runnable loginAction;
    private Runnable registrationAction;

    public SignUp() {

        Label header = new Label("Sign Up");
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Label nameLabel = new Label("Name: ");
        nameLabel.setPrefWidth(120);
        TextField name = new TextField();
        name.setPrefWidth(200);

        Label emailLabel = new Label("Email: ");
        emailLabel.setPrefWidth(120);
        TextField email = new TextField();
        email.setPrefWidth(200);

        Label passLabel = new Label("Password: ");
        passLabel.setPrefWidth(120);
        PasswordField password = new PasswordField();
        password.setPrefWidth(200);

        Label rePassLabel = new Label("Re-enter Password: ");
        rePassLabel.setPrefWidth(120);
        PasswordField rePass = new PasswordField();
        rePass.setPrefWidth(200);

        CheckBox termsCheck = new CheckBox("I agree to the terms and conditions");

        Button signupBtn = new Button("Sign Up");
        signupBtn.setMinWidth(80);

        Label error = new Label("Fields should not be empty");
        error.setTextFill(Color.RED);
        error.setVisible(false);

        Label loginLabel = new Label("If you already have an account, please login: ");
        Button loginBtn = new Button("Login");

        RadioButton applicantRadio = new RadioButton("Applicant");
        RadioButton registrarRadio = new RadioButton("Registrar");

        ToggleGroup userTypeGroup = new ToggleGroup();
        applicantRadio.setToggleGroup(userTypeGroup);
        registrarRadio.setToggleGroup(userTypeGroup);

        HBox row1 = new HBox();
        row1.setSpacing(10);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox();
        row2.setSpacing(10);

        HBox row3 = new HBox();
        row3.setSpacing(10);

        HBox row4 = new HBox();
        row4.setSpacing(10);

        HBox row5 = new HBox();
        row5.setSpacing(10);

        HBox row6 = new HBox();
        row6.setSpacing(10);
        row6.setAlignment(Pos.CENTER);

        HBox row7 = new HBox();
        row7.setSpacing(10);
        row7.setAlignment(Pos.CENTER);

        HBox row8 = new HBox();
        row8.setSpacing(10);
        row8.setAlignment(Pos.CENTER);

        HBox row9 = new HBox();
        row9.setSpacing(10);
        row9.setAlignment(Pos.CENTER);

        HBox row10 = new HBox();
        row10.setSpacing(10);
        row10.setAlignment(Pos.CENTER);

        row1.getChildren().addAll(header);
        row2.getChildren().addAll(nameLabel, name);
        row3.getChildren().addAll(emailLabel, email);
        row4.getChildren().addAll(passLabel, password);
        row5.getChildren().addAll(rePassLabel, rePass);
        row6.getChildren().addAll(termsCheck);
        row7.getChildren().addAll(applicantRadio, registrarRadio);
        row8.getChildren().addAll(signupBtn);
        row9.getChildren().addAll(error);
        row10.getChildren().addAll(loginLabel, loginBtn);

        grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(row1,0,0);
        grid.add(row2,0,1);
        grid.add(row3,0,2);
        grid.add(row4,0,3);
        grid.add(row5,0,4);
        grid.add(row6,0,5);
        grid.add(row7,0,6);
        grid.add(row8,0,7);
        grid.add(row9,0,8);
        grid.add(row10,0,9);

        signupBtn.setOnAction(actionEvent -> {
            if (name.getText().isEmpty() || email.getText().isEmpty() || password.getText().isEmpty() || rePass.getText().isEmpty() || !termsCheck.isSelected()) {
                error.setText("Fields should not be empty");
                error.setVisible(true);
            } else if (!password.getText().equals(rePass.getText())) {
                error.setText("Password Mismatch");
                error.setVisible(true);
            } else if (userTypeGroup.getSelectedToggle() == null) {
                error.setText("Please select user type");
                error.setVisible(true);
            } else {
                String userType = ((RadioButton) userTypeGroup.getSelectedToggle()).getText();
                try {
                    if (insertUser(name.getText(), email.getText(), password.getText(), userType)) {
                        if(userType.equals("Applicant")) {
                            Main.showRegistrationPage();
                        } else {
                            Main.showRegistrationRegistrarPage();
                        }
                        error.setVisible(false);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to insert user. Please try again.");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        loginBtn.setOnAction(actionEvent -> {
            Main.showLoginPage();
        });
    }

    public GridPane getView() {
        return grid;
    }

    public void setLoginAction(Runnable loginAction) {
        this.loginAction = loginAction;
    }

    public void setRegistrationAction(Runnable registrationAction) {
        this.registrationAction = registrationAction;
    }

    private boolean insertUser(String name, String email, String password, String userType) throws SQLException {
        String sql = "INSERT INTO user_id (Username, EmailAddress, Password, UserType) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, userType);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }
}
