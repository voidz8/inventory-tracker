package eu.thehypesply.inventorytracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableMongoRepositories("eu.thehypesply.inventorytracker.repository")
public class InventoryTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryTrackerApplication.class, args);
	}

	@PostConstruct
	private void seleniumConfig(){
		System.setProperty("webdriver.chrome.drive", "C:/Users/renzo/OneDrive/Documenten/programming");
	}

	@PostConstruct
	private void initDatabase(){

	}
}
