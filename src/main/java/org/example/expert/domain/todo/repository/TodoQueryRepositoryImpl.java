package org.example.expert.domain.todo.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.expert.domain.todo.dto.response.QTodoProjectionDto;
import org.example.expert.domain.todo.dto.response.TodoProjectionDto;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static org.example.expert.domain.comment.entity.QComment.comment;
import static org.example.expert.domain.manager.entity.QManager.manager;
import static org.example.expert.domain.todo.entity.QTodo.todo;
import static org.example.expert.domain.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class TodoQueryRepositoryImpl implements TodoQueryRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Todo findByIdWithUser(long todoId){
        return queryFactory.select(todo)
                .from(todo)
                .leftJoin(todo.user).fetchJoin()
                .where(todoIdEq(todoId))
                .fetchOne();
    }

    @Override
    public Page<TodoProjectionDto> search(Pageable pageable, String title, String nickname, LocalDateTime startDate, LocalDateTime endDate){
        List<TodoProjectionDto> result = queryFactory.select(
                new QTodoProjectionDto(
                        todo.title,
                        todo.managers.size(),
                        todo.comments.size()
                )
                )
                .from(todo)
                .leftJoin(todo.managers,manager)
                .leftJoin(manager.user, user)
                .leftJoin(todo.comments,comment)
                .where(
                        titleContain(title),
                        nicknameContain(nickname),
                        createAtBetween(startDate, endDate)
                )
                .groupBy(todo.id)
                .orderBy(todo.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = queryFactory
                .select(Wildcard.count)
                .from(todo)
                .where( titleContain(title),
                        nicknameContain(nickname),
                        createAtBetween(startDate, endDate))
                .fetchOne();

        return new PageImpl<>(result, pageable,  totalCount != null ? totalCount : 0);
    }

    private BooleanExpression titleContain(String title){
        return title !=null ? todo.title.contains(title) : null;
    }

    private BooleanExpression nicknameContain(String nickname){
        return nickname != null ? user.nickname.contains(nickname) : null;
    }

    private BooleanExpression createAtBetween(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate !=null && endDate!=null){
            return  todo.createdAt.between(startDate, endDate);
        }else {
            return null;
        }
    }
    private BooleanExpression todoIdEq(Long todoId){
        return todoId!=null ? todo.id.eq(todoId) : null;
    }
}
