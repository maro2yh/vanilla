package vanilla.application.management.api;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceBean {

    @Autowired
    private MessageSource messageSource;
    
    public String getMessage(String code) {
        return getMessage(code, null, Locale.KOREAN);
    }
    
    public String getMessage(String code, Object[] args) {
        return getMessage(code, args, Locale.KOREAN);
    }
    
    public String getMessage(String code, Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }
}
