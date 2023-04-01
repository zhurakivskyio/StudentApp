package com.example.studentapp.repository;

import com.example.studentapp.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE TaskModel t SET t.description = :description WHERE t.id = :id")
    int updateDescriptionById(@Param("id") Long id, @Param("description") String description);


    List<TaskModel> findByColor(String color);
}
