package org.irbis.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(generator = "article_id_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "article_id_gen", sequenceName = "article_id_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String author;
    private Integer views;
    private Integer rating;
    private Integer commentsCount;

    public Article(String title, String author, Integer views, Integer rating, Integer commentsCount) {
        this.title = title;
        this.author = author;
        this.views = views;
        this.rating = rating;
        this.commentsCount = commentsCount;
    }
}
