package com.chatcode.service;

import com.chatcode.domain.entity.Article;
import com.chatcode.domain.entity.Avatar;
import com.chatcode.domain.entity.scrap.Scrap;
import com.chatcode.dto.scrap.ScrapRequestDTO;
import com.chatcode.dto.scrap.ScrapResponseDTO;
import com.chatcode.exception.common.ContentNotFoundException;
import com.chatcode.repository.article.ArticleReadRepository;
import com.chatcode.repository.avatar.AvatarReadRepository;
import com.chatcode.repository.scrap.ScrapReadRepository;
import com.chatcode.repository.scrap.ScrapWriteRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
    public ScrapResponseDTO scrap(ScrapRequestDTO scrapRequestDTO) {
        LocalDateTime now = LocalDateTime.now();

        Optional<Avatar> optionalAvatar = avatarReadRepository.findById(scrapRequestDTO.getAvatarId());
        Avatar avatar = optionalAvatar.orElseThrow(() -> new ContentNotFoundException("Avatar not found"));

        Long articleId = scrapRequestDTO.getArticleId();
        if (articleId == null) {
            throw new ContentNotFoundException("Article ID is null");
        }
        Optional<Article> optionalArticle = articleReadRepository.findById(articleId);
        if (!optionalArticle.isPresent()) {
            throw new ContentNotFoundException("Article not found");
        }
        Article article = optionalArticle.get();

        Scrap scrap = Scrap.builder()
                .avatarId(avatar)
                .articleId(article)
                .dateCreated(now)
                .build();
        scrapWriteRepository.save(scrap);

        return ScrapResponseDTO.builder()
                .avatarId(scrap.getAvatarId().getId())
                .articleId(scrap.getArticleId().getId())
                .dateCreated(scrap.getDateCreated())
                .build();
    }

    @Transactional
    public List<ScrapResponseDTO> getScrapList(Long avatarId) {
        List<Scrap> scrapList = scrapReadRepository.findByAvatarId(avatarId);

        List<ScrapResponseDTO> responseDTOList = scrapList.stream()
                .map(scrap -> {
                    Long articleId = scrap.getArticleId().getId();
                    Optional<Article> optionalArticle = articleReadRepository.findById(articleId);
                    if (!optionalArticle.isPresent()) {
                        throw new ContentNotFoundException("Article not found");
                    }
                    Article article = optionalArticle.get();
                    return ScrapResponseDTO.builder()
                            .avatarId(scrap.getAvatarId().getId())
                            .articleId(article.getId())
                            .articleTitle(article.getTitle())
                            .dateCreated(scrap.getDateCreated())
                            .build();
                })
                .collect(Collectors.toList());

        return responseDTOList;
    }


    @Transactional
    public void deleteScrap(Long avatarId, Long articleId) {
        Optional<Scrap> scrap = scrapReadRepository.findByAvatarIdAndArticleId(avatarId, articleId);
        if (scrap.isPresent()) {
            scrapWriteRepository.delete(scrap.get());
        } else {
            throw new ContentNotFoundException("Article not found");
        }
    }
}
