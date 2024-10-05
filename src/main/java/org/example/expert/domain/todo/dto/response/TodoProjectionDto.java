package org.example.expert.domain.todo.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TodoProjectionDto {
    private final String title;
    private final Integer managerNum;
    private final Integer commentNum;

    // annotation 방법
    @QueryProjection
    public TodoProjectionDto(String title, Integer managerNum, Integer commentNum) {
        this.title = title;
        this.managerNum = managerNum;
        this.commentNum = commentNum;
    }
}