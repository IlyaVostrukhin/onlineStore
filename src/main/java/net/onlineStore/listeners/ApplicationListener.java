package net.onlineStore.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


@Slf4j
@Component
public class ApplicationListener implements ServletContextListener {

    //Считывает свойство и записывает в переменную
    @Value("${application.production}")
    private boolean isProduction;

    public void contextInitialized(ServletContextEvent sce) {
        //Переменная записывается в атрибут контекста. Обращаемся через $ во всем приложении
        sce.getServletContext().setAttribute("production", isProduction);
        log.info("Application started. Production is '{}'", isProduction);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Application destroyed");
    }

}