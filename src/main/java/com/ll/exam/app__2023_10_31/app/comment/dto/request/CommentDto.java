package com.ll.exam.app__2023_10_31.app.comment.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CommentDto {
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
}
