package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.Scrap;
import com.chatcode.dto.ScrapResponseDto;
import com.chatcode.handler.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.ArticleWriteRepository;
import com.chatcode.repository.avatar.AvatarWriteRepository;
import com.chatcode.repository.scrap.ScrapReadRepository;
import com.chatcode.repository.scrap.ScrapWriteRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapWriteRepository scrapWriteRepository;
    private final ScrapReadRepository scrapReadRepository;
    private final AvatarWriteRepository avatarWriteRepository;
    private final ArticleWriteRepository articleWriteRepository;

    @Transactional
    public ScrapResponseDto scrap(Long articleId, Long avatarId) {
        Avatar avatar = avatarWriteRepository.getReferenceById(avatarId);
        Article article = articleWriteRepository.getReferenceById(articleId);

        Scrap scrap = Scrap.builder()
                .avatar(avatar)
                .article(article)
                .build();
        scrapWriteRepository.save(scrap);

        return ScrapResponseDto.of(scrap);
    }

    @Transactional
    public List<ScrapResponseDto> getScrapList(Long avatarId) {
        return scrapReadRepository.getScrapList(avatarId);
    }

    @Transactional
    public void deleteScrap(Long articleId) {
        Optional<Scrap> scrap = scrapWriteRepository.findByArticleId(articleId);
        if (scrap.isPresent()) {
            scrapWriteRepository.delete(scrap.get());
            return;
        }
        throw new ContentNotFoundException("Scrap not found");
    }
}
