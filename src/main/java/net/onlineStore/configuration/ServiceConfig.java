package net.onlineStore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@ComponentScan({
        "net.onlineStore.services.impl",
        "net.onlineStore.controllers",
        "net.onlineStore.filters",
        "net.onlineStore.listeners"
})
public class ServiceConfig {
    //Properties init. Static. Стандартный. Прыгает из проекта в проект.
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer conf = new PropertySourcesPlaceholderConfigurer();
        conf.setLocations(getResources());
        return conf;
    }

    private static Resource[] getResources() {
        return new Resource[]{new ClassPathResource("application.properties")};
    }
}
