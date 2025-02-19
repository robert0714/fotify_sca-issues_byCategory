package com.example.utils.fotify_sca.issues_byCategory.javaFX;

//import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public final class FileChooserSample extends Application {

//    private Desktop desktop = Desktop.getDesktop();
    private final Map<String,String> map= new HashMap ();

    @Override
    public void start(final Stage stage) {
        stage.setTitle("選擇有XML資料夾");
        final DirectoryChooser directoryChooser =
                new DirectoryChooser();
        final Button openMultipleButton = new Button("請選擇目錄");
        final Button execButton = new Button("執行");

        openMultipleButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {

                        directoryChooser.setTitle("選擇fortifySCA xml資料夾");
                        directoryChooser.setInitialDirectory(
                                new File(System.getProperty("user.home"))
                        );
                        final File selectedDirectory =
                                directoryChooser.showDialog(stage);
                        if (selectedDirectory != null) {
                            map.put("selected" ,  selectedDirectory.getAbsolutePath());
//                            openDirectory(selectedDirectory);
                        }
                    }
                });
        execButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        String selected = map.get("selected");
                        System.out.println("你使用的是: %s".formatted(selected));
                    }
                });

        final GridPane inputGridPane = new GridPane();

        GridPane.setConstraints(openMultipleButton, 0, 0);
        GridPane.setConstraints(execButton, 1, 0);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(openMultipleButton ,execButton );

        final Pane rootGroup = new VBox(12);
        rootGroup.getChildren().addAll(inputGridPane);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));

        stage.setScene(new Scene(rootGroup));
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


//    private void openDirectory(File file) {
//        try {
//            desktop.open(file);
//        } catch (IOException ex) {
//            Logger.getLogger(
//                    FileChooserSample.class.getName()).log(
//                    Level.SEVERE, null, ex
//            );
//        }
//    }
}