package com.project.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String longUrl;
    private LocalDateTime createdDate;
    @Column(nullable = false)
    private long clickCount=0;

    public long getClickCount()
    { return clickCount; }
    public void setClickCount(long clickCount)
    { this.clickCount = clickCount; }


}
