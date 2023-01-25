package com.example.todowithauthorization.repositories;

import com.example.todowithauthorization.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByUserId(long id);

}
