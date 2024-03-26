package com.example.studentSystem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Login {
    private final GridPane grid;
    private Runnable signupAction;

    public Login() {

        Label header = new Label("Login");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));

        Label unameLabel = new Label("Username: ");
        unameLabel.setPrefWidth(100);
        TextField username = new TextField();
        username.setPrefWidth(200);

        Label passLabel = new Label("Password: ");
        passLabel.setPrefWidth(100);
        PasswordField password = new PasswordField();
        password.setPrefWidth(200);

        Button loginBtn = new Button("Login");
        loginBtn.setMinWidth(80);

        Label signUpLabel = new Label("If you are a new applicant, please register: ");
        Button signUpBtn = new Button("SignUp");

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

        row1.getChildren().addAll(header);
        row2.getChildren().addAll(unameLabel, username);
        row3.getChildren().addAll(passLabel, password);
        row4.getChildren().addAll(loginBtn);
        row5.getChildren().addAll(signUpLabel, signUpBtn);

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

        loginBtn.setOnAction(event -> {
        });

        signUpBtn.setOnAction(actionEvent -> {
            if (signupAction != null) {
                signupAction.run();
            }
        });
    }

    public GridPane getView() {
        return grid;
    }

    public void setSignupAction(Runnable signupAction) {
        this.signupAction = signupAction;
    }
}
