package com.accelerator.framework.spring.boot.autoconfigure;

import com.accelerator.framework.message.MessageProvider;
import com.accelerator.framework.message.NLS;
import com.accelerator.framework.message.SpringMessageProvider;
import com.accelerator.framework.spring.ApplicationContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;


@Configuration
public class ApplicationContextConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public ApplicationContextHolder applicationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

    @Bean(name = NLS.MESSAGE_PROVIDER_BEAN_NAME)
    public MessageProvider messageProvider(MessageSource messageSource) {
        SpringMessageProvider messageProvider = new SpringMessageProvider();
        messageProvider.setMessageSource(messageSource);
        return messageProvider;
    }

    @Bean(name = ConfigurationPropertiesBindingPostProcessor.VALIDATOR_BEAN_NAME)
    public LocalValidatorFactoryBean configurationPropertiesValidator(MessageSource messageSource,
            ApplicationContext applicationContext) {
        LocalValidatorFactoryBean configurationPropertiesValidator = new LocalValidatorFactoryBean();
        configurationPropertiesValidator.setValidationMessageSource(messageSource);
        configurationPropertiesValidator.setApplicationContext(applicationContext);
        return configurationPropertiesValidator;
    }

}