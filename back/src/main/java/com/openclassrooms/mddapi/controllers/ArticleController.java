package com.openclassrooms.mddapi.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.ArticleDetailDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.services.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleDetailDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO, Principal principal) {
        ArticleDTO createdArticle = articleService.createArticle(articleDTO, principal.getName());
        return ResponseEntity.ok(createdArticle);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailDTO> getArticleDetail(@PathVariable Integer id) {
        ArticleDetailDTO detail = articleService.getArticleDetail(id);
        return ResponseEntity.ok(detail);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Integer id, @RequestBody CommentDTO commentDTO,
            Principal principal) {
        String userEmail = principal.getName();
        CommentDTO savedComment = articleService.addCommentToArticle(id, commentDTO.getContent(), userEmail);
        return ResponseEntity.ok(savedComment);
    }
}