package net.onlineStore.configuration;

import net.onlineStore.filters.CategoryProducerFilter;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import net.onlineStore.filters.StoreFilter;
import net.onlineStore.listeners.ApplicationListener;

import javax.servlet.*;
import java.util.EnumSet;

public class StoreWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        WebApplicationContext ctx = createWebApplicationContext(container);

        container.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE));
        container.addListener(new ContextLoaderListener(ctx));
        container.addListener(ctx.getBean(ApplicationListener.class));

        registerFilters(container, ctx);
        registerSpringMVCDispatcherServlet(container, ctx);
    }

    private WebApplicationContext createWebApplicationContext(ServletContext container) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("net.onlineStore.configuration");
        ctx.setServletContext(container);
        ctx.refresh();
        return ctx;
    }

    private void registerFilters(ServletContext container, WebApplicationContext ctx) {
        registerFilter(container, ctx.getBean(StoreFilter.class));
        registerFilter(container, ctx.getBean(CategoryProducerFilter.class));
        registerFilter(container, new CharacterEncodingFilter("UTF-8", true));
        registerFilter(container, new RequestContextFilter());
        registerFilter(container, new DelegatingFilterProxy("springSecurityFilterChain"), "springSecurityFilterChain");
        registerFilter(container, buildConfigurableSiteMeshFilter(), "sitemesh");
    }

    private void registerFilter(ServletContext container, Filter filter, String... filterNames) {
        String filterName = filterNames.length > 0 ? filterNames[0] : filter.getClass().getSimpleName();
        container.addFilter(filterName, filter).addMappingForUrlPatterns(null, true, "/*");
    }

    private void registerSpringMVCDispatcherServlet(ServletContext container, WebApplicationContext ctx) {
        ServletRegistration.Dynamic servlet =
                container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

    //TODO: Вместо template -> tags или fragments?
    private ConfigurableSiteMeshFilter buildConfigurableSiteMeshFilter() {
        return new ConfigurableSiteMeshFilter() {
            @Override
            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
                builder
                        .addDecoratorPath("/*", "/WEB-INF/JSP/page-template.jsp")
                        .addDecoratorPath("/admin*", "/WEB-INF/JSP/admin-template.jsp")
                        .addDecoratorPath("/manager/ajax/*", "/WEB-INF/JSP/fragment-template.jsp")
                        .addDecoratorPath("/admin/ajax/*", "/WEB-INF/JSP/fragment-template.jsp")
                        .addDecoratorPath("/ajax/*", "/WEB-INF/JSP/fragment-template.jsp");
            }
        };
    }
}
