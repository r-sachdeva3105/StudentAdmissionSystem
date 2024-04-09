package com.example.studentSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.studentSystem.Main.connection;

public class AdminDashboard {
    private final GridPane grid;
    Text program1;
    Text program2;
    Text program3;

    public AdminDashboard(UserData userData) {

        Label header = new Label("Hi, " + userData.getName());
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        header.setMaxWidth(Double.MAX_VALUE);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(80);

        ChoiceBox<String> filter = new ChoiceBox<>();
        filter.getItems().addAll("Applicants", "Registrars");
        filter.setValue("Registrars");
        filter.setPrefWidth(100);

        TableView<Applicant> applicantView = new TableView<>();
        applicantView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        applicantView.setMaxWidth(800);

        TableColumn<Applicant, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantID().asObject());

        TableColumn<Applicant, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantName());

        TableColumn<Applicant, String> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantPhone());

        TableColumn<Applicant, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantEmail());

        applicantView.getColumns().addAll(idColumn, nameColumn, phoneColumn, emailColumn);

//        filter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            if ("Applicants".equals(newValue)) {
//                try {
//                    ObservableList<Applicant> applicantsList = getApplicantsFromDatabase();
//                    applicantView.setItems(applicantsList);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        filter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                ObservableList<Applicant> applicantsList = getApplicantsFromDatabase(newValue);
                applicantView.setItems(applicantsList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        try {
            ObservableList<Applicant> applicantsList = getApplicantsFromDatabase("Registrars");
            applicantView.setItems(applicantsList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        applicantView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Applicant selectedApplicant = applicantView.getSelectionModel().getSelectedItem();
                try {
                    ObservableList<Applicant> applicantsList = getApplicantsFromDatabase("Registrars");
                    for (Applicant applicant : applicantsList) {
                        System.out.println("Applicant ID: " + applicant.getApplicantID().get());
                    }
                    getApplicantPrograms(selectedApplicant.getApplicantID().get());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Label program1Label = new Label("Program 1:");
        program1Label.setPrefWidth(100);

        Label program2Label = new Label("Program 2:");
        program2Label.setPrefWidth(100);

        Label program3Label = new Label("Program 3:");
        program3Label.setPrefWidth(100);

        program1 = new Text();
        program1.setWrappingWidth(100);

        program2 = new Text();
        program2.setWrappingWidth(100);

        program3 = new Text();
        program3.setWrappingWidth(100);

        Button updateBtn = new Button("Update");
        updateBtn.setMinWidth(80);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setMinWidth(80);

        Button reportBtn = new Button("Report");
        reportBtn.setMinWidth(80);

        ChoiceBox<String> status = new ChoiceBox<>();
        status.getItems().addAll("Accepted", "Rejected", "Conditionally Accepted");
        status.setPrefWidth(170);

        Button submitBtn = new Button("Submit");
        submitBtn.setMinWidth(80);

        Button addApplicantBtn = new Button("Add Applicant");
        addApplicantBtn.setMinWidth(80);

        Button addRegistrarBtn = new Button("Add Registrar");
        addRegistrarBtn.setMinWidth(80);

        HBox row1 = new HBox();
        row1.setSpacing(10);
        HBox.setHgrow(header, Priority.ALWAYS);
        HBox.setHgrow(logoutBtn, Priority.ALWAYS);

        HBox row2 = new HBox();
        row2.setSpacing(10);

        HBox row3 = new HBox();
        row3.setSpacing(10);
        HBox.setHgrow(applicantView, Priority.ALWAYS);

        HBox row4 = new HBox();
        row4.setSpacing(10);

        HBox row5 = new HBox();
        row5.setSpacing(10);

        HBox row6 = new HBox();
        row6.setSpacing(10);

        HBox row7 = new HBox();
        row7.setSpacing(10);

        HBox row8 = new HBox();
        row8.setSpacing(10);

        row1.getChildren().addAll(header, logoutBtn);
        row2.getChildren().addAll(filter);
        row3.getChildren().addAll(applicantView);
//        row4.getChildren().addAll(program1Label, program2Label, program3Label);
//        row5.getChildren().addAll(program1, program2, program3);
        filter.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("Applicants")) {
                row4.getChildren().addAll(program1Label, program2Label, program3Label);
                row5.getChildren().addAll(program1, program2, program3);
                row7.getChildren().addAll(status, submitBtn);
            } else {
                row4.getChildren().removeAll(program1Label, program2Label, program3Label);
                row5.getChildren().removeAll(program1, program2, program3);
                row7.getChildren().removeAll(status, submitBtn);
            }
        });
        row6.getChildren().addAll(updateBtn, deleteBtn, reportBtn);
//        row7.getChildren().addAll(status, submitBtn);
        row8.getChildren().addAll(addApplicantBtn, addRegistrarBtn);

        grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(row1, 0, 0);
        grid.add(row2, 0, 1);
        grid.add(row3, 0, 2);
        grid.add(row4, 0, 3);
        grid.add(row5, 0, 4);
        grid.add(row6, 0, 5);
        grid.add(row7, 0, 6);
        grid.add(row8, 0, 7);

        logoutBtn.setOnAction(actionEvent -> {
            Main.showLoginPage();
        });

        addApplicantBtn.setOnAction(actionEvent -> {
            Main.showRegistrationPage();
        });

        addRegistrarBtn.setOnAction(actionEvent -> {
            Main.showRegistrationRegistrarPage();
        });
        submitBtn.setOnAction(actionEvent -> {
            Applicant selectedApplicant = applicantView.getSelectionModel().getSelectedItem();

            if (selectedApplicant != null) {
                String selectedStatus = status.getValue();

                if (selectedStatus != null && !selectedStatus.isEmpty()) {
                    try {
                        updateApplicantStatus(selectedApplicant.getApplicantID().get(), selectedStatus);
                        System.out.println("Applicant status updated successfully.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("Failed to update applicant status.");
                    }
                } else {
                    System.out.println("Please select a status.");
                }
            } else {
                System.out.println("No applicant selected.");
            }
        });

        deleteBtn.setOnAction(actionEvent -> {
            Applicant selectedApplicant = applicantView.getSelectionModel().getSelectedItem();

            if (selectedApplicant != null) {
                int applicantID = selectedApplicant.getApplicantID().get();

                try {
                    deleteApplicantFromDatabase(applicantID);

                    applicantView.getItems().remove(selectedApplicant);

                    System.out.println("Applicant deleted successfully.");
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Failed to delete applicant.");
                }
            } else {
                System.out.println("No applicant selected.");
            }
        });

    }


    public GridPane getView() {
        return grid;
    }

//    private ObservableList<Applicant> getApplicantsFromDatabase() throws SQLException {
//        ObservableList<Applicant> applicantsList = FXCollections.observableArrayList();
//        String sql = "SELECT ID, firstName, lastName, emailAddress, phoneNumber FROM applicants";
//        try (PreparedStatement statement = connection.prepareStatement(sql);
//             ResultSet resultSet = statement.executeQuery()) {
//            while (resultSet.next()) {
//                int id = resultSet.getInt("ID");
//                String firstName = resultSet.getString("firstName");
//                String lastName = resultSet.getString("lastName");
//                String name = firstName + " " + lastName;
//                String phone = resultSet.getString("phoneNumber");
//                String email = resultSet.getString("emailAddress");
//                applicantsList.add(new Applicant(id, name, phone, email));
//            }
//        }
//        return applicantsList;
//    }

    private ObservableList<Applicant> getApplicantsFromDatabase(String newValue) throws SQLException {
        ObservableList<Applicant> applicantsList = FXCollections.observableArrayList();
        String tableName = newValue.equals("Applicants") ? "applicants" : "registrar";
        String sql = "SELECT ID, firstName, lastName, emailAddress, phoneNumber FROM " + tableName;
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String name = firstName + " " + lastName;
                String phone = resultSet.getString("phoneNumber");
                String email = resultSet.getString("emailAddress");
                applicantsList.add(new Applicant(id, name, phone, email));

            }
            return applicantsList;
        }
    }

    private void getApplicantPrograms(int applicantID) throws SQLException {
        String sql = "SELECT fieldOfStudy1, fieldOfStudy2, fieldOfStudy3 FROM applicants WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, applicantID);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String fieldOfStudy1 = resultSet.getString("fieldOfStudy1");
                    String fieldOfStudy2 = resultSet.getString("fieldOfStudy2");
                    String fieldOfStudy3 = resultSet.getString("fieldOfStudy3");

                    program1.setText(fieldOfStudy1);
                    program2.setText(fieldOfStudy2);
                    program3.setText(fieldOfStudy3);
                }
            }
        }
    }
    private void updateApplicantStatus(int applicantID, String status) throws SQLException {
        String sql = "UPDATE applicants SET application_status = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status);
            statement.setInt(2, applicantID);
            statement.executeUpdate();
        }
    }

    private void deleteApplicantFromDatabase(int applicantID) throws SQLException {
        String sql = "DELETE FROM applicants WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, applicantID);
            statement.executeUpdate();
        }
    }
}