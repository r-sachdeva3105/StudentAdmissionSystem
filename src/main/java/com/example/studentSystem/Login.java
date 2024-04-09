package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private final GridPane grid;
    private Connection connection;

    public Login(Connection connection) {
        this.connection = connection;

        Label header = new Label("Login");
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Label emailLabel = new Label("Email: ");
        emailLabel.setPrefWidth(80);
        TextField email = new TextField();
        email.setPrefWidth(200);

        Label passLabel = new Label("Password: ");
        passLabel.setPrefWidth(80);
        PasswordField password = new PasswordField();
        password.setPrefWidth(200);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(80);

        Label error = new Label("Fields should not be empty");
        error.setTextFill(Color.RED);
        error.setVisible(false);

        Label signUpLabel = new Label("If you are a new applicant, please register: ");
        Button signUpBtn = new Button("Sign Up");

        HBox row1 = new HBox();
        row1.setSpacing(10);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox();
        row2.setSpacing(10);

        HBox row3 = new HBox();
        row3.setSpacing(10);

        HBox row4 = new HBox();
        row4.setSpacing(10);
        row4.setAlignment(Pos.CENTER);

        HBox row5 = new HBox();
        row5.setSpacing(10);
        row5.setAlignment(Pos.CENTER);

        HBox row6 = new HBox();
        row6.setSpacing(10);
        row6.setAlignment(Pos.CENTER);

        row1.getChildren().addAll(header);
        row2.getChildren().addAll(emailLabel, email);
        row3.getChildren().addAll(passLabel, password);
        row4.getChildren().addAll(loginBtn);
        row5.getChildren().addAll(error);
        row6.getChildren().addAll(signUpLabel, signUpBtn);

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

        loginBtn.setOnAction(event -> {
            if (email.getText().isEmpty() || password.getText().isEmpty()) {
                error.setText("Fields should not be empty");
                error.setVisible(true);
            }
            else {
                try {
                    UserData userData = checkCredentials(email.getText(), password.getText());
                    if (userData != null) {
                        error.setVisible(false);
                        String userType = userData.getUserType();
                        System.out.println(userType);
                        if ("applicant".equals(userType)) {
                            Main.showApplicantDashboardPage(userData);
                        } else if ("registrar".equals(userType)) {
                            Main.showRegistrarDashboardPage(userData);
                        } else if ("admin".equals(userType)) {
                            Main.showAdminDashboardPage(userData);
                        } else {
                            // Handle other user types if needed
                        }
                    } else {
                        error.setText("Invalid email or password");
                        error.setVisible(true);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        signUpBtn.setOnAction(actionEvent -> {
            Main.showSignupPage();
        });
    }

    public GridPane getView() {
        return grid;
    }

    private UserData checkCredentials(String email, String password) throws SQLException {
        String sql = "SELECT * FROM user_id WHERE EmailAddress = ? AND Password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    UserData userData = new UserData();
                    userData.setId(resultSet.getInt("ID"));
                    userData.setEmail(resultSet.getString("EmailAddress"));
                    userData.setName(resultSet.getString("Username"));
                    String userType = resultSet.getString("UserType");
                    userData.setUserType(userType); // Assuming UserData has a setUserType method
                    return userData;
                } else {
                    return null;
                }
            }
        }
    }

}