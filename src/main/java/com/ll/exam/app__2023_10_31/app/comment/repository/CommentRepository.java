package com.ll.exam.app__2023_10_31.app.comment.repository;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Article, Long> {
    List<Comment> findAllByOrderByIdDesc();
}

