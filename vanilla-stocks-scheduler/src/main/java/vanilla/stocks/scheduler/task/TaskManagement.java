package vanilla.stocks.scheduler.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import vanilla.commons.util.calendar.VanillaCalendarUtils;

@Slf4j
@Service
public class TaskManagement {
    
    @Autowired
    private ApplicationContext applicationContext;
    
    private List<IVanillaSchedule> shcedulerList = new ArrayList<IVanillaSchedule>();

    @PostConstruct
    public void init() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(VanillaScheduleScan.class));
        List<String> classNames = scanner.findCandidateComponents("vanilla.stocks.scheduler").stream().map(BeanDefinition::getBeanClassName).filter(Objects::nonNull).collect(Collectors.toList());
        List<Class<?>> classes = new ArrayList<Class<?>>();
        
        for (String className : classNames) {
            log.debug("The class {} was scanned to the scheduler.", className);
            
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                log.error(e.getMessage(), e);
            }
        }
        
        if (classes.isEmpty()) {
            return;
        }
        
        for (Class<?> aClass : classes) {
            VanillaScheduleScan annotation = aClass.getAnnotation(VanillaScheduleScan.class);
            String type = annotation.type();
            IVanillaSchedule scheduler = null;
            
            if (type.equalsIgnoreCase("time")) {
                int time = annotation.time();
                
                if (time < 0) {
                    log.error("IllegalArgumentsException - The time is not valid.");
                    continue;
                }
                
                scheduler = (IVanillaSchedule) applicationContext.getBean(aClass);
                scheduler.start(time);
                
                if (annotation.startUpRun()) {
                    scheduler.run();
                }
                
                shcedulerList.add(scheduler);
            } else if (type.equalsIgnoreCase("cron")) {
                String cron = annotation.cron();
                
                if (StringUtils.isEmpty(cron)) {
                    log.error("IllegalArgumentsException - The cron is not valid.");
                    continue;
                }
                
                scheduler = (IVanillaSchedule) applicationContext.getBean(aClass);
                scheduler.start(cron);
                
                if (annotation.startUpRun()) {
                    scheduler.run();
                }
                
                shcedulerList.add(scheduler);
            } else {
                log.error("IllegalArgumentsException - Not supported type.");
            }
        }
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void execute() {
        if (!VanillaCalendarUtils.todayIsHoliday()) {
            return;
        }
        
        log.info("Today is holiday.");
        
        
    }
}
