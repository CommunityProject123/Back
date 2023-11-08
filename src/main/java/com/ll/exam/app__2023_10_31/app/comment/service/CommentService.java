package com.ll.exam.app__2023_10_31.app.comment.service;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.repository.ArticleRepository;
import com.ll.exam.app__2023_10_31.app.comment.entity.Comment;
import com.ll.exam.app__2023_10_31.app.comment.repository.CommentRepository;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment write(Article article, Member author, String content) {
        Comment comment = Comment.builder()
                .author(author)
                .content(content)
                .build();
        article.addComment(comment);

        commentRepository.save(article);

        return comment;
    }
}
