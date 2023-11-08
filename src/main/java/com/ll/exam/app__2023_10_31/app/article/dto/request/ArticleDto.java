package com.ll.exam.app__2023_10_31.app.article.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ArticleDto {
    @NotEmpty(message = "제목을 입력해주세요.")
    private String subject;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
}
