package com.ll.exam.app__2023_10_31.app.comment.controller;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.service.ArticleService;
import com.ll.exam.app__2023_10_31.app.base.dto.RsData;
import com.ll.exam.app__2023_10_31.app.comment.entity.Comment;
import com.ll.exam.app__2023_10_31.app.comment.service.CommentService;
import com.ll.exam.app__2023_10_31.app.member.service.MemberService;
import com.ll.exam.app__2023_10_31.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final MemberService memberService;

    @GetMapping("/{articleId}")
    public ResponseEntity<RsData> list(@PathVariable Long articleId) {
        List<Comment> comments = commentService.findByArticleId(articleId);

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "comments", comments
                        )
                )
        );
    }
}
