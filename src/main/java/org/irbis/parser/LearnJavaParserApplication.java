package org.irbis.parser;

import lombok.RequiredArgsConstructor;
import org.irbis.parser.model.Article;
import org.irbis.parser.repository.ArticleRepository;
import org.irbis.parser.service.SiteLoader;
import org.irbis.parser.service.SiteParser;
import org.irbis.parser.service.Storage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class LearnJavaParserApplication implements CommandLineRunner {
    private final SiteLoader loader;
    private final SiteParser parser;
    private final Storage storage;

    public static void main(String[] args) {
        SpringApplication.run(LearnJavaParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String load = loader.load();
        List<Article> articleList = parser.parse(load);

        storage.save(articleList);

//        System.exit(0);
    }
}
