package film;

import film.api.helper.FileSystemHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootApplication
@Configuration
public class BootifulApplication {

	public static void main(String[] args) {
		try {
			setup();
			SpringApplication.run(BootifulApplication.class, args);
		} catch (Exception e) {
			System.out.println("error setting up service, shutting down ...");
		}
	}

	public static void setup() throws IOException {

		// Create static file dir if not exist
		Files.createDirectories(Paths.get(FileSystemHelper.STATIC_FILES_DIR));
		System.out.println("static file dir is created at: " + FileSystemHelper.STATIC_FILES_DIR);
	}
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}

}
