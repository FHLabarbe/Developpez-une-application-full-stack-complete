package com.openclassrooms.mddapi.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleDetailDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.entities.Article;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.Theme;
import com.openclassrooms.mddapi.entities.User;
import com.openclassrooms.mddapi.mappers.ArticleMapper;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.repositories.ArticleRepository;
import com.openclassrooms.mddapi.repositories.CommentRepository;
import com.openclassrooms.mddapi.repositories.ThemeRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public ArticleService(ArticleRepository articleRepository, ArticleMapper articleMapper,
            ThemeRepository themeRepository, UserRepository userRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
        this.themeRepository = themeRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    public List<ArticleDTO> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articleMapper.toDtoList(articles);
    }

    @Transactional
    public ArticleDTO createArticle(ArticleDTO articleDTO, String email) {
        Optional<Theme> themeOpt = themeRepository.findById(articleDTO.getThemeId());
        if (themeOpt.isEmpty()) {
            throw new RuntimeException("Thème introuvable avec l'ID " + articleDTO.getThemeId());
        }
        Theme theme = themeOpt.get();

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable avec l'email " + email);
        }

        Article article = articleMapper.toEntity(articleDTO);
        article.setAuthor(user);
        article.setTheme(theme);

        Article savedArticle = articleRepository.save(article);
        return articleMapper.toDto(savedArticle);
    }

    public ArticleDetailDTO getArticleDetail(Integer articleId) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RuntimeException("Article introuvable"));
        List<Comment> comments = commentRepository.findByArticle(article);
        ArticleDetailDTO detailDTO = articleMapper.toDetailDto(article);
        detailDTO.setComments(commentMapper.toDtoList(comments));
        return detailDTO;
    }

    @Transactional
    public CommentDTO addCommentToArticle(Integer articleId, String content, String userEmail) {
        Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RuntimeException("Article introuvable"));
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("Utilisateur introuvable");
        }
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setUser(user);
        comment.setContent(content);
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }
}