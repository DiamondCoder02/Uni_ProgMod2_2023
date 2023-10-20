package com.lesson7.Aspect;

import com.lesson7.Todo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class LoggingAspectGetUser {
    @Before("execution(* com.lesson7.TodoController.getUser(Long)) && args(itemId)")
    public void logBeforeGetUser(JoinPoint joinPoint, Long itemId) {
        String infoMessage = "\nMethod" + joinPoint.getSignature().getName() + "\n" +
                "\nTask ID:" + itemId + " searching...";
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.lesson7.TodoController.getUser(Long)) && args(itemId)", returning = "response", argNames = "itemId,response")
    public void logAfterGetUser(Long itemId, ResponseEntity<Todo> response) {
        Todo user = response.getBody();
        log.info("Task with ID={} found: [name: '{}', Description: '{}', Deadline: '{}', Priority: '{}', Creator Name: '{}']", 
            Objects.requireNonNull(user).getId(), user.getName(), user.getDescription(), user.getDeadline(), user.getPriority(), user.getCreatorName());
    }
}