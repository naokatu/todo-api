package com.example.todoapi.service.task;

import com.example.todoapi.repository.task.TaskRecord;
import com.example.todoapi.repository.task.TaskRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskEntity find(long taskId) {
        return taskRepository.select(taskId)
                .map(record -> new TaskEntity(record.getId(), record.getTitle()))
                .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    public TaskEntity create(@NotNull String title) {
        var record = new TaskRecord(null, title);
        taskRepository.insert(record);
        return new TaskEntity(record.getId(), record.getTitle());
    }
}
