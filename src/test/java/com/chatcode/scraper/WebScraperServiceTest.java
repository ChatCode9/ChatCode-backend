package com.chatcode.scraper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebScraperServiceTest {
    @Test
    public void test() {
        String link = "/magazine/detail/2610/";

        String[] a = link.split("/");

        System.out.println(a[2]);

    }
}