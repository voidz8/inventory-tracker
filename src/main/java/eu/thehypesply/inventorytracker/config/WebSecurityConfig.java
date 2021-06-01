package eu.thehypesply.inventorytracker.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.PostConstruct;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier(userdet)


}
