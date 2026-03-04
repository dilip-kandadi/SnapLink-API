package com.project.demo.controller;

import com.project.demo.service.Urlservice;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;


@RestController
public class Urlcontroller {

    @Autowired
    private Urlservice service;

    @PostMapping("/api/shorten")
    public String create(@RequestBody Map<String,String> request){
        String url =  request.get("longUrl");
        return service.create(url);

    }

    @GetMapping("/{shorturl}")
    public void redirect(@PathVariable String shorturl, HttpServletResponse response) throws IOException {
        String longurl = service.redirect(shorturl);

        response.sendRedirect(longurl);
    }

    @GetMapping("/stats/{shorturl}")
    public long getUrlStats(@PathVariable String shorturl){
        return service.getClickCount(shorturl);
    }

}
