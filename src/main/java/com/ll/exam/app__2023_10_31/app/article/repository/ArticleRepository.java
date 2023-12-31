package com.ll.exam.app__2023_10_31.app.article.repository;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByIdDesc();
}
