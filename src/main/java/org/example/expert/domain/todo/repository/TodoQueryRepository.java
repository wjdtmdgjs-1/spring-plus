package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.dto.response.TodoProjectionDto;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TodoQueryRepository  {

    Todo findByIdWithUser(long todoId);

    Page<TodoProjectionDto> search(Pageable pageable, String title, String nickname, LocalDateTime startDate, LocalDateTime endDate);
}
