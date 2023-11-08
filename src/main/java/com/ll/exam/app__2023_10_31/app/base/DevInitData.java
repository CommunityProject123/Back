package com.ll.exam.app__2023_10_31.app.base;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.service.ArticleService;
import com.ll.exam.app__2023_10_31.app.comment.service.CommentService;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import com.ll.exam.app__2023_10_31.app.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class DevInitData {
    @Bean
    CommandLineRunner initData(MemberService memberService, ArticleService articleService, CommentService commentService, PasswordEncoder passwordEncoder) {
        String password = passwordEncoder.encode("1234");
        return args -> {
            Member member1 = memberService.join("user1", password, "user1@test.com");
            Member member2 = memberService.join("user2", password, "user2@test.com");

            Article article1 = articleService.write(member1, "제목 1", "내용 1");
            Article article2 = articleService.write(member1, "제목 2", "내용 2");
            Article article3 = articleService.write(member2, "제목 3", "내용 3");
            Article article4 = articleService.write(member2, "제목 4", "내용 4");

            commentService.write(article1, member1, "내용 1");
            commentService.write(article1, member1, "내용 2");
            commentService.write(article1, member2, "내용 3");
            commentService.write(article1, member2, "내용 4");
        };
    }
}
