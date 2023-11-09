package com.ll.exam.app__2023_10_31.app.comment.controller;

import com.ll.exam.app__2023_10_31.app.article.entity.Article;
import com.ll.exam.app__2023_10_31.app.article.service.ArticleService;
import com.ll.exam.app__2023_10_31.app.base.dto.RsData;
import com.ll.exam.app__2023_10_31.app.comment.dto.request.CommentDto;
import com.ll.exam.app__2023_10_31.app.comment.entity.Comment;
import com.ll.exam.app__2023_10_31.app.comment.service.CommentService;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import com.ll.exam.app__2023_10_31.app.member.service.MemberService;
import com.ll.exam.app__2023_10_31.app.security.entity.MemberContext;
import com.ll.exam.app__2023_10_31.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/{articleId}")
    public ResponseEntity<RsData> createComment(
            @AuthenticationPrincipal MemberContext memberContext,
            @PathVariable Article articleId,
            @Valid @RequestBody CommentDto commentDto) {

        // 현재 로그인한 사용자 정보를 가져옴
        Member author = memberService.findByUsername(memberContext.getUsername()).orElseThrow();

        // CommentService의 create 메서드를 사용하여 댓글 생성
        Comment comment = commentService.write(articleId, author, commentDto.getContent());

        // 댓글이 성공적으로 생성되면 ID나 다른 정보를 반환하거나, 성공 여부에 따른 응답을 작성
        if (comment != null) {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "S-1",
                            "댓글이 성공적으로 생성되었습니다."
                    )
            );
        } else {
            return Util.spring.responseEntityOf(
                    RsData.of(
                            "F-3",
                            "댓글 생성 중 오류가 발생했습니다."
                    )
            );
        }
    }
}
