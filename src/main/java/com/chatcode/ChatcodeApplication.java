package com.chatcode;

import com.chatcode.scraper.WebScraperService;
import lombok.RequiredArgsConstructor;
import org.jooq.Require;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class ChatcodeApplication {

    private final WebScraperService webScraperService;

    public static void main(String[] args) {
        SpringApplication.run(ChatcodeApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {
            webScraperService.scrapeData();
        };
    }
}
