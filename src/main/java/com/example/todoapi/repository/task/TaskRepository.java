package com.example.todoapi.repository.task;

import org.apache.ibatis.annotations.*;

import java.util.Optional;
import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("SELECT id, title FROM tasks WHERE id = #{taskId}")
    Optional<TaskRecord> select(long taskId);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("INSERT INTO tasks (title) VALUES (#{title})")
    void insert(TaskRecord record);

    @Select("SELECT id, title FROM tasks LIMIT #{limit} OFFSET #{offset}")
    List<TaskRecord> selectList(int limit, long offset);

    @Update("UPDATE tasks SET title = #{title} WHERE id = #{id}")
    void update(TaskRecord taskRecord);
}
