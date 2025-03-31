package com.example.todoapi.service.task;

public class TaskEntityNotFoundException extends RuntimeException {

    private long taskId;

    public TaskEntityNotFoundException(long taskId) {
        super("Task with ID " + taskId + " not found");
        this.taskId = taskId;
    }
}
