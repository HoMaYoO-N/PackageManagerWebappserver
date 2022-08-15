package cmpt213.assignment4.packagedeliveries.webappserver.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

// Retrieved from stackoverflow https://stackoverflow.com/questions/36119852/spring-boot-actuator-pretty-print-json

@Configuration
public class MakeSpringPrettyPrintJSON extends WebMvcConfigurationSupport {
        @Override
        protected void extendMessageConverters( List<HttpMessageConverter<?>> converters ) {

            for ( HttpMessageConverter<?> converter : converters ) {

                if (converter instanceof MappingJackson2HttpMessageConverter jacksonConverter) {

                    jacksonConverter.setPrettyPrint( true );
                }
            }
        }
}
