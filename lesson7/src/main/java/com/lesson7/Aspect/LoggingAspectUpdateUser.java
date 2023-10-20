package com.lesson7.Aspect;

import com.lesson7.Todo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class LoggingAspectUpdateUser {
    @Before(value = "execution(* com.lesson7.TodoController.updateTodo(..)) && args(id, updatedUser)", argNames = "joinPoint,id,updatedUser")
    public void logBeforeUpdateUser(JoinPoint joinPoint, Long id, Todo updatedTodo) {
        String infoMessage = "\nMethod: " + joinPoint.getSignature().getName() + "\n" +
            "\nOld ID: " + updatedTodo.getId() +
            "\nOld Name: " + updatedTodo.getName() +
            "\nOld Description: " + updatedTodo.getDescription() +
            "\nOld Deadline: " + updatedTodo.getDeadline() +
            "\nOld Priority: " + updatedTodo.getPriority() +
            "\nOld Creator Name: " + updatedTodo.getCreatorName();
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.lesson7.TodoController.updateTodo(..)) && args(itemId, ..)", returning = "response", argNames = "response,itemId")
    public void logAfterModifyItem(ResponseEntity<Todo> response, Long itemId) {
        Todo user = response.getBody();
        log.info("\nTask with ID={} updated: \n[name: '{}', Description: '{}', Deadline: '{}', Priority: '{}', Creator Name: '{}']", 
            Objects.requireNonNull(user).getId(), user.getName(), user.getDescription(), user.getDeadline(), user.getPriority(), user.getCreatorName());
    }
}