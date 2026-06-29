package com.project.demo.service;

import com.project.demo.URLSummary;
import com.project.demo.model.Url;
import com.project.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class Urlservice {
    @Autowired
    private UrlRepository repo;

    @Autowired
    private UrlShortenerLogic logic;

    @Autowired
    private StringRedisTemplate template;

    public String create(String url) {
        Url urlObj = new Url();
        urlObj.setLongUrl(url);
        urlObj.setCreatedDate(LocalDateTime.now());
        Url saved = repo.save(urlObj);
        return logic.encode(saved.getId());
    }

    public String redirect(String shorturl) {

        String cachedurl = template.opsForValue().get(shorturl);
        if(cachedurl != null) {
            System.out.println("From redis");
            return cachedurl;
        }
        System.out.println("Cache Miss! Fetching from DB");
        long id = logic.decode(shorturl);
        Url url = repo.findById(id).get();
        String longurl = url.getLongUrl();
        url.setClickCount(url.getClickCount()+1);
        repo.save(url);

        template.opsForValue().set(shorturl, url.getLongUrl(),10, TimeUnit.MINUTES);
        return longurl;
    }

    public long getClickCount(String shorturl){
        long id = logic.decode(shorturl);
        Url url = repo.findById(id).get();
        return url.getClickCount();
    }

    public List<URLSummary> getAllUrls() {
        return repo.findAllBy();
    }
}
