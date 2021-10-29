package vanilla.stocks.scheduler.task;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface VanillaScheduleScan {

    String name() default "";
    String type() default "time";
    int time() default -1;
    String cron() default "";
    boolean startUpRun() default false;
}
