

package com.fidnortech.xjx.aspect;

import com.fidnortech.xjx.annotation.LogRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;


@Aspect
public class LogRecordAspect {
    private static final Logger log = LoggerFactory.getLogger(LogRecordAspect.class);





    public LogRecordAspect() {
    }

    @Pointcut("@annotation(com.fidnortech.xjx.annotation.LogRecord) ")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = null;

        try {
            result = point.proceed();
        } catch (Exception var8) {
            long time = System.currentTimeMillis() - beginTime;
            this.saveSysLog(point, var8, time);
            throw var8;
        }
        long time = System.currentTimeMillis() - beginTime;
        this.saveSysLog(point, result, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, Object result, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogRecord annotation = method.getAnnotation(LogRecord.class);
        if (annotation.isRecord()) {
            String modular = annotation.modular();
            String className = method.getDeclaringClass().getName();
            String methodName = method.getName();

            //次数写入保存日志的逻辑

            

        }
    }
}
