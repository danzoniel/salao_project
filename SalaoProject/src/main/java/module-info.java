module com.mycompany.salaoproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.mycompany.salaoproject to javafx.fxml;
    exports com.mycompany.salaoproject;
}
