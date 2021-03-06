package github.zhanggang.version1.config;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Component
public class DataSourceAOP {

    @Pointcut("@annotation(github.zhanggang.version1.config.Slave)")
    public void readPointcut() {
    }

    @Pointcut("@annotation(github.zhanggang.version1.config.Master)")
    public void writePointcut() {
    }

    @Before("readPointcut()")
    public void readAdvise() {
        DynamicSwitchDBTypeUtil.slave();
    }

    @Before("writePointcut()")
    public void writeAdvise() {
        DynamicSwitchDBTypeUtil.master();
    }
}
