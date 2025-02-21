package com.example.utils.fotify_sca.issues_byCategory.javaFX;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication; 
import org.springframework.context.annotation.ImportRuntimeHints;

@SpringBootApplication 
@ImportRuntimeHints(NativeHintsConfiguration.class)
public class Application {
 

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
