package org.irbis.parser.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Article {
    private String title;
    private String author;
    private Integer views;
    private Integer rating;
    private Integer commentsCount;
}
