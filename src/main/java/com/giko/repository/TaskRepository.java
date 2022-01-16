package com.giko.repository;

import com.giko.dto.CountType;
import com.giko.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT * FROM task ORDER BY DUE_DATE DESC", nativeQuery = true)
    public List<Task> getAllTaskDueDateDesc();

    @Query(value = "SELECT new com.giko.dto.CountType(COUNT(*)/(SELECT COUNT(*) FROM Task) * 100,type) FROM Task GROUP BY type")
    public List<CountType> getPercentageGroupByType();
}
