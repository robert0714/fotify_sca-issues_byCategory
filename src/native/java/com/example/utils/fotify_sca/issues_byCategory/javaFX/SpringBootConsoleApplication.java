package com.example.utils.fotify_sca.issues_byCategory.javaFX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import com.example.utils.fotify_sca.issues_byCategory.service.LogicProcessImpl;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


/***
 * Generate qualified `src\main\resources\META-INF\native-image` data <br/>
 * You have to run once scenario about operations in order to let aot to audit beahvior 
 * 
 * ***/
@SpringBootApplication
@ImportRuntimeHints(NativeHintsConfiguration.class)
public class SpringBootConsoleApplication {

	public static void main(String[] args) throws Exception {

		LogicProcessImpl service = new LogicProcessImpl();
		URL folderUrl = LogicProcessImpl.class.getResource("/examples");
		Path startPath = Paths.get(Objects.requireNonNull(folderUrl).toURI());
		service.process(startPath);

		SpringApplication.run(SpringBootConsoleApplication.class, args);
	}
}
