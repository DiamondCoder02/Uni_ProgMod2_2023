package com.lesson7.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class LoggingAspectDeleteTodo {
    @Before("execution(* com.lesson7.TodoController.deleteTodo(Long)) && args(itemId)")
    public void logBeforeDeleteUser(JoinPoint joinPoint, Long itemId) {
        String infoMessage = "\nMethod: " + joinPoint.getSignature().getName() + "\n" +
                "\nTask ID:" + itemId + " got deleted";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.lesson7.TodoController.deleteTodo(Long)) && args(itemId)", returning = "result", argNames = "itemId,result")
    public void logAfterDeleteUser(Long itemId, Object result) {
        if (result != null) {
            log.info("\nTask with ID={} got deleted", itemId);
        }
    }
}