package tvz.nppjj.paris.init.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import tvz.nppjj.paris.init.config.filter.JwtFilter;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("forward:/static/index.html");
            }
        };
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        // allowed: /changelog, /events, /events/:id, /eventspage , /login, /register /category, /eventsfilter
        registrationBean.addUrlPatterns(
                "/events/delete",
                "/events/update",
                "/events/user",
                "/events/save",
                "/tickets",
                "/users"
                );
        return registrationBean;
    }

}
