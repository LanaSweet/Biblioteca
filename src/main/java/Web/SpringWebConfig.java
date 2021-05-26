package Web;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import Comunes.DateFormatter;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer{   
//extends WebMvcConfigurerAdapter implements ApplicationContextAware {

    @SuppressWarnings("unused")
	private ApplicationContext applicationContext;

    public SpringWebConfig() {
        super();
    }

    public void setApplicationContext(final ApplicationContext applicationContext)  throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Messages");
        return messageSource;
    }
    
    @Override   
    public void addFormatters(final FormatterRegistry registry) {   
//    	super.addFormatters(registry);       
    	registry.addFormatter(dateFormatter());  
    }
        
    @Bean    
    public DateFormatter dateFormatter() {  
    	return new DateFormatter();   
    } 
}
