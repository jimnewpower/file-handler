package com.primalimited.filehandler;

import com.primalimited.filehandler.configuration.FileUploadConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableConfigurationProperties({FileUploadConfiguration.class})
public class FileHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileHandlerApplication.class, args);
	}
}
