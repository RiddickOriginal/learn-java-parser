package org.irbis.parser.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SiteLoader {
    @Setter
    @Value("${habr-url}")
    private String siteUrl;
    private final RestTemplate restTemplate;

    public String load() {
        return restTemplate.getForObject(siteUrl, String.class, Map.of());
    }

}
