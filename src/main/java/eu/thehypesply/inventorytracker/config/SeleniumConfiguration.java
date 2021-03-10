package eu.thehypesply.inventorytracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfiguration {

    @PostConstruct
    void postConstruct(){
        System.setProperty("webdriver.chrome.drive", "C:/Users/renzo/OneDrive/Documenten/java belangrijk");
    }

    @Bean
    public ChromeDriver driver()
    }
}
