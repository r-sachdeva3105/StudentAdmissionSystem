package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.FileWriter;
import java.io.IOException;

public class ApplicantDashboard {

    private final GridPane grid;
    private Runnable logoutAction;

    private Text imagePathText;


    public ApplicantDashboard(UserData userData) {
        Label header = new Label("Hi, " + userData.getName());
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        header.setMaxWidth(Double.MAX_VALUE);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(80);

        ImageView applicantImg = new ImageView();

        imagePathText = new Text();
        imagePathText.setWrappingWidth(400);

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
        grid.add(imagePathText, 0, 5); // Add below the other rows




        logoutBtn.setOnAction(actionEvent -> {
            Main.showLoginPage();
        });

        printBtn.setOnAction(event -> printDetails(userData.getId()));

        uploadBtn.setOnAction(actionEvent -> {
            // Create a FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Image File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
            );

            // Show the file chooser dialog
            File selectedFile = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());

            // If the user selects a file
            if (selectedFile != null) {
                // Get the selected file path
                String imagePath = ((File) selectedFile).getAbsolutePath();

                // Update the imagePath column in the applicants table
                try {
                    updateImagePath(userData.getId(), imagePath);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Handle the exception
                }
            }
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

        updateBtn.setOnAction(actionEvent -> {
            Applicant applicant = null;
            try {
                applicant = getApplicantData(userData.getId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (applicant != null) {
                int applicantID = applicant.getApplicantID().get();
                try {
                    Main.showUpdateApplicantPage(applicantID, userData);
                } catch (Exception e ) {
                    System.out.println(e);
                }
            }
        });
    }



    private void printDetails(int id) {
        try {
            Applicant applicant = getApplicantData(id);

            // Format the data
            StringBuilder message = new StringBuilder();
            message.append("<html><head><title>Student Registration Management System</title>");
            message.append("<style>body { font-family: Calibri, sans-serif; text-align: center; background-image: url('background.jpg'); background-size: 40%; background-repeat: no-repeat; background-position: center top; padding-top: 4em; } table { margin: auto; } th:nth-child(1), td:nth-child(1) { width: 1.5in; } </style>");
            message.append("</head><body>");
            message.append("<h2>Student Registration Management System</h2>");
            message.append("<h3>Academic registration report generated ").append(java.time.LocalDate.now()).append("</h3>");
            message.append("<table border='1' style='margin: auto;'>"); // Add style for centering the table
            message.append("<tr><td>Name</td><td>").append(applicant.getApplicantName().get()).append("</td></tr>");
            message.append("<tr><td>Email</td><td>").append(applicant.getApplicantEmail().get()).append("</td></tr>");
            message.append("<tr><td>Phone</td><td>").append(applicant.getApplicantPhone().get()).append("</td></tr>");
            message.append("<tr><td>Date of Birth</td><td>").append(applicant.getDob().get()).append("</td></tr>");
            message.append("<tr><td>Gender</td><td>").append(applicant.getGender().get()).append("</td></tr>");
            message.append("<tr><td>Program 1</td><td>").append(applicant.getProgram1().get()).append("</td></tr>");
            message.append("<tr><td>Program 2</td><td>").append(applicant.getProgram2().get()).append("</td></tr>");
            message.append("<tr><td>Program 3</td><td>").append(applicant.getProgram3().get()).append("</td></tr>");
            message.append("</table></body></html>");

            // Generate the file name
            String fileName = applicant.getApplicantName().get() + "_Date_" + java.time.LocalDate.now() + "_Academic_Registration.html";

            // Write the data to an HTML file
            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(message.toString());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }

            // Show an alert
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Applicant Details");
            alert.setHeaderText("Applicant Information");
            alert.setContentText("Data has been written to " + fileName);
            alert.showAndWait();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }



    public GridPane getView() {
        return grid;
    }

    private void updateImagePath(int userID, String imagePath) throws SQLException {
        String sql = "UPDATE applicants SET imagePath = ? WHERE ID = ?";
        try (PreparedStatement statement = Main.connection.prepareStatement(sql)) {
            statement.setString(1, imagePath);
            statement.setInt(2, userID);
            statement.executeUpdate();

            imagePathText.setText("Uploaded Image Path: " + imagePath);

        }
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