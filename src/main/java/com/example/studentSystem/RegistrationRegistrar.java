package com.example.studentSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.studentSystem.Main.connection;

public class RegistrationRegistrar {
    private final GridPane grid;

    public RegistrationRegistrar(int id, UserData userData) {
        Label header = new Label("Registrar Registration");
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Label nameLabel = new Label("Full Name: ");
        nameLabel.setPrefWidth(120);
        nameLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField name = new TextField();
        name.setPrefWidth(200);

        Label emailLabel = new Label("Email: ");
        emailLabel.setPrefWidth(120);
        emailLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField email = new TextField();
        email.setPrefWidth(200);

        Label phoneLabel = new Label("Phone No: ");
        phoneLabel.setPrefWidth(120);
        phoneLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField phone = new TextField();
        phone.setPrefWidth(200);

        Label dobLabel = new Label("Date of Birth: ");
        dobLabel.setPrefWidth(120);
        dobLabel.setAlignment(Pos.CENTER_RIGHT);
        DatePicker dob = new DatePicker();
        dob.setPrefWidth(200);

        Label genderLabel = new Label("Gender: ");
        genderLabel.setPrefWidth(120);
        genderLabel.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> gender = new ChoiceBox<>();
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setPrefWidth(200);

        Label streetLabel = new Label("Street:");
        streetLabel.setPrefWidth(120);
        streetLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField street = new TextField();
        street.setPrefWidth(200);

        Label cityLabel = new Label("City:");
        cityLabel.setPrefWidth(120);
        cityLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField city = new TextField();
        city.setPrefWidth(200);

        Label countryLabel = new Label("Country:");
        countryLabel.setPrefWidth(120);
        countryLabel.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> country = new ChoiceBox<>();
        country.getItems().addAll("Canada", "USA", "UK", "Australia", "India");
        country.setPrefWidth(200);

        CheckBox accuracyCheck = new CheckBox("I declare that the information provided is accurate to the best of my knowledge");

        Label error = new Label("Fields should not be empty");
        error.setTextFill(Color.RED);
        error.setVisible(false);

        Button submitBtn = new Button("Submit");
        submitBtn.setMinWidth(80);

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

        row1.getChildren().addAll(header);
        row2.getChildren().addAll(nameLabel, name, emailLabel, email, phoneLabel, phone);
        row3.getChildren().addAll(dobLabel, dob, genderLabel, gender);
        row4.getChildren().addAll(streetLabel, street, cityLabel, city, countryLabel, country);
        row5.getChildren().addAll(accuracyCheck);
        row6.getChildren().addAll(error);
        row7.getChildren().addAll(submitBtn);

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

        submitBtn.setOnAction(actionEvent -> {
            if (name.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() ||
                    dob.getValue() == null || gender.getValue() == null || street.getText().isEmpty() ||
                    city.getText().isEmpty() || country.getValue() == null || !accuracyCheck.isSelected()) {
                error.setVisible(true);
            } else {
                error.setVisible(false);
                try {
                    // Convert LocalDate to java.sql.Date
                    Date sqlDate = Date.valueOf(dob.getValue());

                    // Call insertApplicant method
                    if (insertRegistrar(id, name.getText(), email.getText(), phone.getText(),
                            sqlDate, gender.getValue(),
                            street.getText(), city.getText(), country.getValue())) {
                        // If insertion is successful, show a success message
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Registrar registered successfully!");
                        alert.showAndWait();
                        Main.showAdminDashboardPage(userData);
                    } else {
                        // If insertion fails, show an error message
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to register applicant!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    // If an SQL exception occurs, show an error message
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while registering applicant!");
                    alert.showAndWait();
                }
            }
        });
    }

    public GridPane getView() {
        return grid;
    }

    public boolean insertRegistrar(int id, String name, String email, String phone,
                                   Date dob, String gender,
                                   String street, String city, String country) throws SQLException {

        // Split the name into first name and last name
        String[] nameParts = name.split("\\s+", 2);
        String firstName = "";
        String lastName = "";
        if (nameParts.length > 0) {
            firstName = nameParts[0];
            if (nameParts.length > 1) {
                lastName = nameParts[1];
            }
        }

        try {
            // Insert into user_id table
            String sql1 = "INSERT INTO user_id (Username, EmailAddress, Password, UserType) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql1)) {
                statement.setString(1, name);  // Use the full name as the username
                statement.setString(2, email);
                statement.setString(3, "a");   // Default password (you may want to change this)
                statement.setString(4, "registrar");

                // Execute the query to insert into user_id table
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted == 0) {
                    return false; // Insertion failed
                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            return false;
        }

        try {
            System.out.println(id);
            // Insert into registrar table
            String sql = "INSERT INTO registrar (ID, firstName, lastName, emailAddress, phoneNumber, dob, gender, street, city, country) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                // Set values for each parameter in the SQL query
                statement.setInt(1, id);
                statement.setString(2, firstName);
                statement.setString(3, lastName);
                statement.setString(4, email);
                statement.setString(5, phone);
                statement.setDate(6, dob);
                statement.setString(7, gender);
                statement.setString(8, street);
                statement.setString(9, city);
                statement.setString(10, country);

                // Execute the SQL query and get the number of rows affected
                int rowsInserted = statement.executeUpdate();

                // Return true if at least one row is inserted, false otherwise
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            e.printStackTrace();
            return false;
        }
    }
}


