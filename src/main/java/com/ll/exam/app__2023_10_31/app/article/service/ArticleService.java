package com.ll.exam.app__2023_10_31.app.article.service;

import com.ll.exam.app__2023_10_31.app.article.dto.request.ArticleDto;
import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.repository.ArticleRepository;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import com.ll.exam.app__2023_10_31.app.security.entity.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article write(Member author, String subject, String content) {
        Article article = Article.builder()
                .author(author)
                .subject(subject)
                .content(content)
                .build();

        articleRepository.save(article);

        return article;
    }

    public List<Article> findAll() {
        return articleRepository.findAllByOrderByIdDesc();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public void delete(Article article) {
        articleRepository.delete(article);
    }

    public boolean actorCanDelete(MemberContext memberContext, Article article) {
        return memberContext.getId() == article.getAuthor().getId();
    }

    public void modify(Article article, ArticleDto articleDto) {
        article.setSubject(articleDto.getSubject());
        article.setContent(articleDto.getContent());
        articleRepository.save(article);
    }

    public boolean actorCanModify(MemberContext memberContext, Article article) {
        return actorCanDelete(memberContext, article);
    }
}
