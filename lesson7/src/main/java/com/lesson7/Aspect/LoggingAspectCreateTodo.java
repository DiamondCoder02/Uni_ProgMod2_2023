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
public class LoggingAspectCreateTodo {
    @Before("execution(* com.lesson7.TodoController.CreateTodo(..)) && args(toDo)")
    public void logBeforeCreateTodo(JoinPoint joinPoint, Todo toDo) {
        String infoMessage = "\nMethod: " + joinPoint.getSignature().getName() + "\n" +
                "\nTask ID: " + toDo.getId() +
                "\nTask Name: " + toDo.getName() +
                "\nTask Description: " + toDo.getDescription() +
                "\nTask Deadline: " + toDo.getDeadline() +
                "\nTask Priority: " + toDo.getPriority() +
                "\nTask Creator Name: " + toDo.getCreatorName();
        log.info(infoMessage);
    }

    @AfterReturning(pointcut = "execution(* com.lesson7.TodoController.CreateTodo(..))", returning = "result")
    public void logAfterCreateTodo(ResponseEntity<Todo> result) {
        Todo toDo = result.getBody();
        log.info("Todo added: [ID={} | name: '{}', Description: '{}', Deadline: '{}', Priority: '{}', Creator Name: '{}']", 
            Objects.requireNonNull(toDo).getId(), toDo.getName(), toDo.getDescription(), toDo.getDeadline(), toDo.getPriority(), toDo.getCreatorName());
    }
}