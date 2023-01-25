package com.example.todowithauthorization.services;

import com.example.todowithauthorization.models.Task;
import com.example.todowithauthorization.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    public List<Task> findAllByUserId(long userId) {
        return taskRepository.findAllByUserId(userId);
    }
    public void save(Task task) {
        taskRepository.save(task);
    }
    public Optional<Task> findById(long id) {
        return taskRepository.findById(id);
    }
    public void update(Task updatedTask) {

        taskRepository.save(updatedTask);
    }
    public void delete(long id) {
        taskRepository.deleteById(id);
    }
    public void delete(Task task) {
        taskRepository.delete(task);
    }

}
