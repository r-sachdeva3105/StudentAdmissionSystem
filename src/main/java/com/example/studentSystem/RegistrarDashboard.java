package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegistrarDashboard {

    private final GridPane grid;
    private Runnable logoutAction;

    public RegistrarDashboard() {

        Label header = new Label("Hi, Registrar");
        header.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        header.setMaxWidth(Double.MAX_VALUE);

        Button logoutBtn = new Button("Logout");
        logoutBtn.setMinWidth(80);

        TableView<Applicant> applicantView = new TableView<>();
        applicantView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        applicantView.setMaxWidth(Double.MAX_VALUE);
        TableColumn<Applicant, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantID().asObject());
        TableColumn<Applicant, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantName());
        TableColumn<Applicant, Integer> phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantPhone().asObject());
        TableColumn<Applicant, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getApplicantEmail());
        applicantView.getColumns().addAll(idColumn, nameColumn, phoneColumn, emailColumn);

        Button updateBtn = new Button("Update");
        updateBtn.setMinWidth(80);

        Button deleteBtn = new Button("Delete");
        deleteBtn.setMinWidth(80);

        Button addBtn = new Button("Add");
        addBtn.setMinWidth(80);

        Button reportBtn = new Button("Report");
        reportBtn.setMinWidth(80);

        ChoiceBox<String> status = new ChoiceBox<>();
        status.getItems().addAll("Accepted", "Rejected", "Conditionally Accepted");
        status.setPrefWidth(170);

        Button submitBtn = new Button("Submit");
        submitBtn.setMinWidth(80);

        HBox row1 = new HBox();
        row1.setSpacing(10);
        HBox.setHgrow(header, Priority.ALWAYS);
        HBox.setHgrow(logoutBtn, Priority.ALWAYS);

        HBox row2 = new HBox();
        row2.setSpacing(10);
        HBox.setHgrow(applicantView, Priority.ALWAYS);

        HBox row3 = new HBox();
        row3.setSpacing(10);

        HBox row4 = new HBox();
        row4.setSpacing(10);

        row1.getChildren().addAll(header, logoutBtn);
        row2.getChildren().addAll(applicantView);
        row3.getChildren().addAll(updateBtn, deleteBtn,addBtn,reportBtn);
        row4.getChildren().addAll(status, submitBtn);

        grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(row1,0,0);
        grid.add(row2,0,1);
        grid.add(row3,0,2);
        grid.add(row4,0,3);

        logoutBtn.setOnAction(actionEvent -> {
            if (logoutAction != null) {
                logoutAction.run();
            }
        });
    }

    public GridPane getView() {
        return grid;
    }
    public void setLogoutAction(Runnable logoutAction) {
        this.logoutAction = logoutAction;
    }
}
