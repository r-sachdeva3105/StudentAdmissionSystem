package com.example.studentSystem;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

        Text name = new Text("Name: " + userData.getName());
        name.setWrappingWidth(200);

        Text email = new Text("Email: " + userData.getEmail());
        email.setWrappingWidth(200);

        Text phone = new Text("Phone Number");
        phone.setWrappingWidth(200);

        Text dob = new Text("Date of Birth");
        dob.setWrappingWidth(200);

        Text gender = new Text("Gender");
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
    }

    public GridPane getView() {
        return grid;
    }
//    public void setLogoutAction(Runnable logoutAction) {
//        this.logoutAction = logoutAction;
//    }
}
