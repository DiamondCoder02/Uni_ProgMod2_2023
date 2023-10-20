package com.lesson7;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;

public class Todo {
    private Long id;
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    private Long name;
    @NotNull(message = "Description cannot be null")
    @NotBlank(message = "Description cannot be blank")
    private Long description;
    @NotNull(message = "Date cannot be null")
    @NotBlank(message = "Date cannot be blank")
    @Future(message = "Date cannot be in the past")
    private Date deadline;
    @NotNull(message = "Priority cannot be null")
    @NotBlank(message = "Priority cannot be blank")
    @Size(min = 1, max = 9, message = "Priority must be between 1 and 9")
    private Integer priority;
    @NotNull(message = "Creator name cannot be null")
    @NotBlank(message = "Creator name cannot be blank")
    private Long creatorName;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getName() {
        return name;
    }
    public void setName(Long name) {
        this.name = name;
    }
    public Long getDescription() {
        return description;
    }
    public void setDescription(Long description) {
        this.description = description;
    }
    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Long getCreatorName() {
        return creatorName;
    }
    public void setCreatorName(Long creatorName) {
        this.creatorName = creatorName;
    }
}