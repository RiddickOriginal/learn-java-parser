package org.irbis.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class LearnJavaParserApplication implements CommandLineRunner {
    private final SiteLoader loader;
    private final SiteParser parser;
    private final Storage storage;

    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        SpringApplication.run(LearnJavaParserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List<String> urls = List.of(
                "https://habr.com/ru/hub/read/",
                "https://habr.com/ru/hub/pm/",
                "https://habr.com/ru/hub/python/",
                "https://habr.com/ru/hub/java/",
                "https://habr.com/ru/hub/ruby/",
                "https://habr.com/ru/hub/cpp/",
                "https://habr.com/ru/hub/haskell/",
                "https://habr.com/ru/hub/career/",
                "https://habr.com/ru/hub/lib/",
                "https://habr.com/ru/hub/ui/"
        );

        List<Runnable> tasks = new ArrayList<>();
        for (String url : urls) {
            tasks.add(() -> {
                log.info("start work with {}", url);
                String load = loader.load(url);
                List<Article> articles = parser.parse(load);
                storage.save(articles);
            });
        }

        log.info("loading start");
        tasks.forEach(executorService::submit);
        log.info("loading end");

        executorService.shutdown();
        System.exit(0);
    }
}
