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

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.studentSystem.Main.connection;

public class UpdateApplicant {
    private final GridPane grid;

    public UpdateApplicant(int id, UserData userData) {
        Applicant applicant = getApplicantDetailsFromDatabase(id);

        Label header = new Label("Update Applicant Details");
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));

        Label nameLabel = new Label("Full Name: ");
        nameLabel.setPrefWidth(120);
        nameLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField name = new TextField(applicant.getApplicantName().get());
        name.setPrefWidth(200);

        Label emailLabel = new Label("Email: ");
        emailLabel.setPrefWidth(120);
        emailLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField email = new TextField(applicant.getApplicantEmail().get());
        email.setPrefWidth(200);

        Label phoneLabel = new Label("Phone No: ");
        phoneLabel.setPrefWidth(120);
        phoneLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField phone = new TextField(applicant.getApplicantPhone().get());
        phone.setPrefWidth(200);

        Label dobLabel = new Label("Date of Birth: ");
        dobLabel.setPrefWidth(120);
        dobLabel.setAlignment(Pos.CENTER_RIGHT);
        DatePicker dob = new DatePicker(LocalDate.parse(applicant.getDob().getValue()));
        dob.setPrefWidth(200);

        Label genderLabel = new Label("Gender: ");
        genderLabel.setPrefWidth(120);
        genderLabel.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> gender = new ChoiceBox<>();
        gender.getItems().addAll("Male", "Female", "Other");
        gender.setValue(applicant.getGender().getValue());
        gender.setPrefWidth(200);

        Label gpaLabel = new Label("GPA:");
        gpaLabel.setPrefWidth(120);
        gpaLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField gpa = new TextField(applicant.getGpa().get());
        gpa.setPrefWidth(200);

        Label streetLabel = new Label("Street:");
        streetLabel.setPrefWidth(120);
        streetLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField street = new TextField(applicant.getStreet().get());
        street.setPrefWidth(200);

        Label cityLabel = new Label("City:");
        cityLabel.setPrefWidth(120);
        cityLabel.setAlignment(Pos.CENTER_RIGHT);
        TextField city = new TextField(applicant.getCity().get());
        city.setPrefWidth(200);

        Label countryLabel = new Label("Country:");
        countryLabel.setPrefWidth(120);
        countryLabel.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> country = new ChoiceBox<>();
        country.getItems().addAll("Canada", "USA", "UK", "Australia", "India");
        country.setValue(applicant.getCountry().get());
        country.setPrefWidth(200);

        Label program1Label = new Label("Program 1:");
        program1Label.setPrefWidth(120);
        program1Label.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> program1 = new ChoiceBox<>();
        program1.setValue(applicant.getProgram1().get());
        program1.setPrefWidth(200);

        Label program2Label = new Label("Program 2:");
        program2Label.setPrefWidth(120);
        program2Label.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> program2 = new ChoiceBox<>();
        program2.setValue(applicant.getProgram2().get());
        program2.setPrefWidth(200);

        Label program3Label = new Label("Program 3:");
        program3Label.setPrefWidth(120);
        program3Label.setAlignment(Pos.CENTER_RIGHT);
        ChoiceBox<String> program3 = new ChoiceBox<>();
        program3.setValue(applicant.getProgram3().get());
        program3.setPrefWidth(200);


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

        HBox row8 = new HBox();
        row8.setSpacing(10);
        row8.setAlignment(Pos.CENTER);

        row1.getChildren().addAll(header);
        row2.getChildren().addAll(nameLabel, name, emailLabel, email, phoneLabel, phone);
        row3.getChildren().addAll(dobLabel, dob, genderLabel, gender, gpaLabel, gpa);
        row4.getChildren().addAll(streetLabel, street, cityLabel, city, countryLabel, country);
        row5.getChildren().addAll(program1Label, program1, program2Label, program2, program3Label, program3);
        row6.getChildren().addAll(accuracyCheck);
        row7.getChildren().addAll(error);
        row8.getChildren().addAll(submitBtn);

        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
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

        ObservableList<String> programOptions = FXCollections.observableArrayList(getProgramOptionsFromDatabase());
        program1.setItems(programOptions);
        program2.setItems(programOptions);
        program3.setItems(programOptions);


        submitBtn.setOnAction(actionEvent -> {
            if (name.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() ||
                    dob.getValue() == null || gender.getValue() == null || street.getText().isEmpty() ||
                    city.getText().isEmpty() || country.getValue() == null || program1.getValue() == null ||
                    program2.getValue() == null || program3.getValue() == null || !accuracyCheck.isSelected()) {
                error.setVisible(true);
            } else {
                error.setVisible(false);
                try {
                    Date sqlDate = Date.valueOf(dob.getValue());
                    String userType = userData.getUserType();

                    if (updateApplicant(id, name.getText(), email.getText(), phone.getText(),
                            sqlDate, gender.getValue(), gpa.getText(),
                            street.getText(), city.getText(), country.getValue(),
                            program1.getValue(), program2.getValue(), program3.getValue())) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Applicant details updated successfully!");
                        alert.showAndWait();
//                        Main.showAdminDashboardPage(userData);
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
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to update applicant details!");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while updating applicant details!");
                    alert.showAndWait();
                }
            }
        });
    }

    public GridPane getView() {
        return grid;
    }

    private List<String> getProgramOptionsFromDatabase() {
        List<String> programOptions = new ArrayList<>();
        try {
            String query = "SELECT program_name FROM fieldofstudy";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                programOptions.add(resultSet.getString("program_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programOptions;
    }

    private Applicant getApplicantDetailsFromDatabase(int id) {
        Applicant applicant = new Applicant();
        try {
            String query = "SELECT * FROM applicants WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                applicant.setApplicantID(id);
                applicant.setApplicantName(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
                applicant.setApplicantEmail(resultSet.getString("emailAddress"));
                applicant.setApplicantPhone(resultSet.getString("phoneNumber"));
                applicant.setDob(resultSet.getDate("dob").toString());
                applicant.setGender(resultSet.getString("gender"));
                applicant.setGpa(resultSet.getString("gpa"));
                applicant.setStreet(resultSet.getString("street"));
                applicant.setCity(resultSet.getString("city"));
                applicant.setCountry(resultSet.getString("country"));
                applicant.setProgram1(resultSet.getString("fieldOfStudy1"));
                applicant.setProgram2(resultSet.getString("fieldOfStudy2"));
                applicant.setProgram3(resultSet.getString("fieldOfStudy3"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applicant;
    }

    private boolean updateApplicant(int id, String name, String email, String phone,
                                    Date dob, String gender, String gpa,
                                    String street, String city, String country,
                                    String program1, String program2, String program3) throws SQLException {

        String[] nameParts = name.split("\\s+", 2); // Split at the first occurrence of whitespace
        String firstName = "";
        String lastName = "";
        if (nameParts.length > 0) {
            firstName = nameParts[0];
            if (nameParts.length > 1) {
                lastName = nameParts[1];
            }
        }

        try {
            String sql1 = "UPDATE user_id SET Username = ?, EmailAddress = ? WHERE ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql1)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setInt(3, id);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        try {
            String sql = "UPDATE applicants SET firstName = ?, lastName = ?, emailAddress = ?, phoneNumber = ?, " +
                    "dob = ?, gender = ?, gpa = ?, street = ?, city = ?, country = ?, fieldOfStudy1 = ?, " +
                    "fieldOfStudy2 = ?, fieldOfStudy3 = ? WHERE ID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, email);
                statement.setString(4, phone);
                statement.setDate(5, dob);
                statement.setString(6, gender);
                statement.setString(7, gpa);
                statement.setString(8, street);
                statement.setString(9, city);
                statement.setString(10, country);
                statement.setString(11, program1);
                statement.setString(12, program2);
                statement.setString(13, program3);
                statement.setInt(14, id);

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
