package org.irbis.parser.service;

import org.irbis.parser.model.Article;
import org.irbis.parser.model.Author;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteParser {

    public List<Article> parse(String html) {
        Document document = Jsoup.parse(html);
        Elements articles = document.select("article");
        List<Article> articleList = new ArrayList<>();

        for (Element article : articles) {
            String title = article.select("h2 a span").text();
            String author = article.select(".tm-user-info__username").text();
            String views = article.select(".tm-icon-counter__value").text()
                    .replace(".", "")
                    .replace("K", "00");
            String rating = article.select(".tm-votes-meter__value_rating").text();
            String commentsCount = article.select(".tm-article-comments-counter-link__value").text();
            Article articleObj = new Article(
                    title,
                    new Author(author),
                    Integer.parseInt(views),
                    Integer.parseInt(rating),
                    Integer.parseInt(commentsCount)
            );
            articleList.add(articleObj);
        }
        return articleList;
    }
}
