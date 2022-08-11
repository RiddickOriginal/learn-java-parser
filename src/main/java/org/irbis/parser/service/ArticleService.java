package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    public List<Article> getTopTenArticles() {
        return articleRepository.findAll().stream()
                .sorted((a1, a2) -> a2.getRating() - a1.getRating())
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Article> getAllByAuthor(String name) {
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        if (optionalAuthor.isEmpty()) {
            return List.of();
        }
        Author author = optionalAuthor.get();
        return articleRepository.getAllByAuthor(author);
    }
}
