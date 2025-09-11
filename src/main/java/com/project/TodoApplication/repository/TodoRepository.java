package com.project.TodoApplication.repository;

import com.project.TodoApplication.model.Todo;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query("UPDATE todo SET status_id = :statusId WHERE id = :id")
    @Modifying
    void updateStatus(long id, int statusId);

    @Query("UPDATE todo SET title = :title WHERE id = :id")
    @Modifying
    void updateTitle(long id, String title);

    @Query("UPDATE todo SET description = :description WHERE id = :id")
    @Modifying
    void updateDescription(long id, String description);
}
