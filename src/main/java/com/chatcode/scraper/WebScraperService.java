package com.chatcode.scraper;

import com.chatcode.domain.entity.BlogPost;
import com.chatcode.repository.blogpost.BlogPostRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebScraperService {

    private final BlogPostRepository blogPostRepository;

    public void scrapeData() {
        String url = "https://yozm.wishket.com/magazine/list/develop/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select(".list-item-link");

            List<BlogPost> blogPosts = new ArrayList<>();

            for (Element element : elements) {
                String title = element.select(".item-title").text();
                String link = element.select("a.item-title").attr("href");
                String thumbnail = element.select("img.thumbnail-image").attr("src");
                String desc = element.select(".item-description").text();
                long id = Long.parseLong(link.split("/")[3]);

                if(blogPostRepository.existsById(id)){
                   continue;
                }

                BlogPost post = new BlogPost();
                post.setId(id);
                post.setTitle(title);
                post.setLink(link);
                post.setThumbnail(thumbnail);
                post.setDescription(desc);

                blogPosts.add(post);
            }

            blogPostRepository.saveAll(blogPosts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
