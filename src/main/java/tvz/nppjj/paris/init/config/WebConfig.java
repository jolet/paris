package tvz.nppjj.paris.init.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("forward:/static/index.html");
                
                registry.addViewController("/eventsAll").setViewName("forward:/static/eventsAll.html");
                
                registry.addViewController("/eventNew").setViewName("forward:/static/newEvent.html");
            }
        };
    }
}
