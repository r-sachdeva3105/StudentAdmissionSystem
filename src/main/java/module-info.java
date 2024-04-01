module com.example.studentadmissionsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studentSystem to javafx.fxml;
    exports com.example.studentSystem;
}