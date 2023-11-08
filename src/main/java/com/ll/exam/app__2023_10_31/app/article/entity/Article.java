package com.ll.exam.app__2023_10_31.app.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ll.exam.app__2023_10_31.app.base.entity.BaseEntity;
import com.ll.exam.app__2023_10_31.app.comment.entity.Comment;
import com.ll.exam.app__2023_10_31.app.member.entity.Member;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Article extends BaseEntity {
    @ManyToOne
    private Member author;
    private String subject;
    private String content;

    @OneToMany(mappedBy = "article", cascade = {CascadeType.ALL})
    private List<Comment> commentList = new ArrayList<>();

    public void addComment(Comment comment) {
        comment.setArticle(this);
        getCommentList().add(comment);
    }


    public Article(long id) {
        super(id);
    }
}
