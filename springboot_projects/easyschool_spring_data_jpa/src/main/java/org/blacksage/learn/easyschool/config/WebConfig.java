package org.blacksage.learn.easyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("home");
                registry.addViewController("/home").setViewName("home");
                registry.addViewController("").setViewName("home");
                registry.addViewController("/courses").setViewName("courses");
                registry.addViewController("/about").setViewName("about");
        }
}
