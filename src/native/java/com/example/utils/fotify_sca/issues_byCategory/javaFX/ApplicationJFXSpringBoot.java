package com.example.utils.fotify_sca.issues_byCategory.javaFX;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import javafx.event.ActionEvent;
import javafx.event.EventHandler; 
import javafx.geometry.Insets; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage; 
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext; 
import org.springframework.context.annotation.ImportRuntimeHints;
import org.springframework.util.StringUtils;


import com.example.utils.fotify_sca.issues_byCategory.service.LogicProcessImpl; 
/**
 * In order to let graalvm successfully compile , comment this !  
 * **/ 
@SpringBootApplication 
@ImportRuntimeHints(NativeHintsConfiguration.class)
public class ApplicationJFXSpringBoot extends javafx.application.Application 
{	 
    
	private final Map<String,String> map = new HashMap<String,String>  ();
    private LogicProcessImpl service = new LogicProcessImpl();     
    private ConfigurableApplicationContext ctx;
    
    public static void main(String[] args) {
    	 System.out.println("-----------------------------------------------------------------launched.");
    	 String tag = System.getenv("native.image") ;
        if (StringUtils.isEmpty(tag)) {
        	System.out.println("native.image: %s".formatted(tag));
            // 只有在執行時才啟動 JavaFX，而非編譯時
            launch(args);
        } else {
            System.out.println("Compiling Native Image... No UI launched.");
         // 初始化 Spring Application Context 避免 AOT 編譯錯誤
            new SpringApplicationBuilder(ApplicationJFXSpringBoot.class).run();
        }
    }
    public void start(Stage stage) throws IOException {  
        fileChooser(stage);
    } 
    
    public void init() throws Exception {
    	this.ctx = new SpringApplicationBuilder(ApplicationJFXSpringBoot.class).run();
    }

    public void stop() {        
        // Close this application context,  
        // destroys all beans in its bean factory 
        this.ctx.close();
    }
    
    public void fileChooser(final Stage stage) {
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
                        }
                    }
                });
        execButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        String selected = map.get("selected");
                        System.out.println("你使用的是: %s".formatted(selected));
                        service.process(new File(selected).toPath());                        
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
}
