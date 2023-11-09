package com.ll.exam.app__2023_10_31;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.repository.ArticleRepository;
import com.ll.exam.app__2023_10_31.app.comment.repository.CommentRepository;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import com.ll.exam.app__2023_10_31.app.member.repository.MemberRepository;
import com.ll.exam.app__2023_10_31.app.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleRepositoryTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MemberRepository memberRepository;

    private static long lastSampleDataId;


    @BeforeEach
    void beforeEach() {
    }


    @Test
    void save() {
        Article a1 = new Article();
        a1.setSubject("Test Subject 111");
        a1.setContent("Test Subject 111");
        a1.setAuthor(new Member(2L));
        articleRepository.save(a1);

        Article a2 = new Article();
        a2.setSubject("Test Subject 222");
        a2.setContent("Test Subject 222");
        a2.setAuthor(new Member(2L));
        articleRepository.save(a2);

        System.out.println("a1.getId() :: " + a1.getId());
        System.out.println("a2.getId() :: " + a2.getId());

        assertThat(a2.getId()).isEqualTo(a1.getId() + 1);

    }

}
