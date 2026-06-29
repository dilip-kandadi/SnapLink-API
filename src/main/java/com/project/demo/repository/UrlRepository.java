package com.project.demo.repository;

import com.project.demo.URLSummary;
import com.project.demo.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url,Long> {

    List<URLSummary> findAllBy();
}
