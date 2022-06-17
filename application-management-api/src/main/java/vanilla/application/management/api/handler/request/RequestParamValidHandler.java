package vanilla.application.management.api.handler.request;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RequestParamValidHandler {

    @Pointcut("execution(* vanilla.application.management.api.service..*Controller.*(..)) && @annotation(vanilla.application.management.api.handler.request.RequestParamValid)")
    public void RequestParamValidHandle() {}
    
    @Before("RequestParamValidHandle")
    public void before(JoinPoint joinPoint) throws Exception {
        Map<String, RequestParam> params = new HashMap<String, RequestParam>();
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            
        }
    }
}
