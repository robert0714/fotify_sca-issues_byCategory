package com.example.utils.fotify_sca.issues_byCategory.javaFX;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    @FXML
    private Label label;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        label.setText("Hello, jpackage!\nRunning on JavaFX: " + javafxVersion + " and Java: " + javaVersion + ".");
    }
}
