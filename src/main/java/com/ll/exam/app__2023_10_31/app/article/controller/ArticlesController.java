package com.ll.exam.app__2023_10_31.app.article.controller;

import com.ll.exam.app__2023_10_31.app.article.dto.request.ArticleDto;
import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.service.ArticleService;
import com.ll.exam.app__2023_10_31.app.base.dto.RsData;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import com.ll.exam.app__2023_10_31.app.member.service.MemberService;
import com.ll.exam.app__2023_10_31.app.security.entity.MemberContext;
import com.ll.exam.app__2023_10_31.util.Util;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticlesController {
    private final ArticleService articleService;
    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<RsData> list() {
        List<Article> articles = articleService.findAll();

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "articles", articles
                        )
                )
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<RsData> detail(@PathVariable Long id) {
        Article article = articleService.findById(id).orElse(null);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-1",
                            "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        return Util.spring.responseEntityOf(
                RsData.successOf(
                        Util.mapOf(
                                "article", article
                        )
                )
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RsData> delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @AuthenticationPrincipal MemberContext memberContext
    ) {
        Article article = articleService.findById(id).orElse(null);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-1",
                            "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        if (articleService.actorCanDelete(memberContext, article) == false) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-2",
                            "삭제 권한이 없습니다."
                    )
            );
        }

        articleService.delete(article);

        return Util.spring.responseEntityOf(
                RsData.of(
                        "S-1",
                        "해당 게시물이 삭제되었습니다."
                )
        );
    }

    @PatchMapping("{id}")
    public ResponseEntity<RsData> modify(@PathVariable Long id, @AuthenticationPrincipal MemberContext memberContext, @Valid @RequestBody ArticleDto articleDto) {
        Article article = articleService.findById(id).orElse(null);

        if (article == null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-1",
                            "해당 게시물은 존재하지 않습니다."
                    )
            );
        }

        if (articleService.actorCanModify(memberContext, article) == false) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-2",
                            "수정 권한이 없습니다."
                    )
            );
        }

        articleService.modify(article, articleDto);

        return Util.spring.responseEntityOf(
                RsData.of(
                        "S-1",
                        "해당 게시물이 수정되었습니다."
                )
        );
    }

    @PostMapping("")
    public ResponseEntity<RsData> create(@AuthenticationPrincipal MemberContext memberContext, @Valid @RequestBody ArticleDto articleDto) {

        Member author = memberService.findByUsername(memberContext.getUsername()).orElseThrow();

        // ArticleService의 write 메서드를 사용하여 글 생성
        Article article = articleService.write(author, articleDto.getSubject(), articleDto.getContent());

        // article이 성공적으로 생성되면 ID나 다른 정보를 반환하거나, 성공 여부에 따른 응답을 작성
        if (article != null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "S-1",
                            "글이 성공적으로 생성되었습니다."
                    )
            );
        } else {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-3",
                            "글 생성 중 오류가 발생했습니다."
                    )
            );
        }
    }
}
