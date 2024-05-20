package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Scrap;
import com.chatcode.dto.ScrapResponseDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.ArticleReadRepository;
import com.chatcode.repository.avatar.AvatarReadRepository;
import com.chatcode.repository.scrap.ScrapReadRepository;
import com.chatcode.repository.scrap.ScrapWriteRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapWriteRepository scrapWriteRepository;
    private final ScrapReadRepository scrapReadRepository;
    private final ArticleReadRepository articleReadRepository;
    private final AvatarReadRepository avatarReadRepository;

    @Transactional
    public ScrapResponseDTO scrap(Long articleId, Long loginAvatar) {
        Avatar avatar = avatarReadRepository.findById(loginAvatar)
                .orElseThrow(() -> new ContentNotFoundException("Avatar not found"));

        Article article = articleReadRepository.findById(articleId)
                .orElseThrow(() -> new ContentNotFoundException("Article not found"));

        Scrap scrap = Scrap.builder()
                .avatar(avatar)
                .article(article)
                .version(0L)
                .build();
        scrapWriteRepository.save(scrap);

        return ScrapResponseDTO.of(scrap, article, avatar);
    }

    @Transactional
    public List<ScrapResponseDTO> getScrapList(Long avatarId) {
        List<Scrap> scrapList = scrapReadRepository.findByAvatarId(avatarId);

        return scrapList.stream()
                .map(scrap -> ScrapResponseDTO.of(scrap,
                        articleReadRepository.findById(scrap.getArticle().getId())
                                .orElseThrow(() -> new ContentNotFoundException("Article not found")),
                        avatarReadRepository.findById(avatarId)
                                .orElseThrow(() -> new ContentNotFoundException("Avatar not found"))))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteScrap(Long avatarId, Long articleId) {
        Scrap scrap = scrapReadRepository.findByAvatarIdAndArticleId(avatarId, articleId)
                .orElseThrow(() -> new ContentNotFoundException("Scrap not found"));

        scrapWriteRepository.delete(scrap);
    }
}
