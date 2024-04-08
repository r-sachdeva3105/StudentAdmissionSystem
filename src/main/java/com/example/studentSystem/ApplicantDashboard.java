package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicantDashboard {

    private final GridPane grid;
    private Runnable logoutAction;

    public ApplicantDashboard(UserData userData) {
        Label header = new Label("Hi, " + userData.getName());
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        header.setMaxWidth(Double.MAX_VALUE);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(80);

        ImageView applicantImg = new ImageView();

        Text name = new Text();
        name.setWrappingWidth(200);

        Text email = new Text();
        email.setWrappingWidth(200);

        Text phone = new Text();
        phone.setWrappingWidth(200);

        Text dob = new Text();
        dob.setWrappingWidth(200);

        Text gender = new Text();
        gender.setWrappingWidth(200);

        Text program1 = new Text("Program 1");
        program1.setWrappingWidth(100);

        Text program2 = new Text("Program 2");
        program2.setWrappingWidth(100);

        Text program3 = new Text("Program 3");
        program3.setWrappingWidth(100);

        Button updateBtn = new Button("Update");
        updateBtn.setMinWidth(80);

        Button uploadBtn = new Button("Upload");
        uploadBtn.setMinWidth(80);

        Button printBtn = new Button("Print");
        printBtn.setMinWidth(80);

        HBox row1 = new HBox();
        row1.setSpacing(10);
        HBox.setHgrow(header, Priority.ALWAYS);
        HBox.setHgrow(logoutBtn, Priority.ALWAYS);

        HBox row2 = new HBox();
        row2.setSpacing(10);

        HBox row3 = new HBox();
        row3.setSpacing(10);

        HBox row4 = new HBox();
        row4.setSpacing(10);

        HBox row5 = new HBox();
        row5.setSpacing(10);

        row1.getChildren().addAll(header, logoutBtn);
        row2.getChildren().addAll(applicantImg, name, email);
        row3.getChildren().addAll(phone, dob, gender);
        row4.getChildren().addAll(program1, program2, program3);
        row5.getChildren().addAll(updateBtn, uploadBtn, printBtn);

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

        logoutBtn.setOnAction(actionEvent -> {
            Main.showLoginPage();
        });

        // Fetch and display applicant data
        try {
            Applicant applicant = getApplicantData(userData.getId());
            name.setText("Name: " + applicant.getApplicantName().get());
            email.setText("Email: " + applicant.getApplicantEmail().get());
            phone.setText("Phone: " + applicant.getApplicantPhone().get());
            dob.setText("Date of Birth: " + applicant.getDob().get());
            gender.setText("Gender: " + applicant.getGender().get());
            program1.setText("Program 1: " + applicant.getProgram1().get());
            program2.setText("Program 2: " + applicant.getProgram2().get());
            program3.setText("Program 3: " + applicant.getProgram3().get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GridPane getView() {
        return grid;
    }

    private Applicant getApplicantData(int userID) throws SQLException {
        String sql = "SELECT * FROM applicants WHERE ID = ?";
        try (PreparedStatement statement = Main.connection.prepareStatement(sql)) {
            statement.setInt(1, userID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("ID");
                    String firstName = resultSet.getString("firstName");
                    String lastName = resultSet.getString("lastName");
                    String name = firstName + " " + lastName;
                    String email = resultSet.getString("emailAddress");
                    String phone = resultSet.getString("phoneNumber");
                    String dob = resultSet.getString("dob");
                    String gender = resultSet.getString("gender");
                    String program1 = resultSet.getString("fieldOfStudy1");
                    String program2 = resultSet.getString("fieldOfStudy2");
                    String program3 = resultSet.getString("fieldOfStudy3");
                    return new Applicant(id, name, phone, email, program1, program2, program3, dob, gender);
                } else {
                    throw new SQLException("No applicant found with ID: " + userID);
                }
            }
        }
    }
}