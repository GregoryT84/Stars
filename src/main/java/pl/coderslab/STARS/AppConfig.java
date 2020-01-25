package pl.coderslab.STARS;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.validation.Validator;
import java.util.Locale;

@Configuration
@EnableJpaRepositories(basePackages = "pl.coderslab.STARS.repositories")
public class AppConfig implements WebMvcConfigurer {
//    @Bean(name = "localResolver")
//    public LocaleContextResolver getLocaleContextResolver() {
//        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(new Locale("pl", "PL"));
//        return sessionLocaleResolver;
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:validationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public Validator validator() {
        LocalValidatorFactoryBean lvfb = new LocalValidatorFactoryBean();
        lvfb.setValidationMessageSource(messageSource());
        return lvfb;
    }
}
