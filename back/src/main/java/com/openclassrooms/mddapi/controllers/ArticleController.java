package com.openclassrooms.mddapi.controllers;

import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.services.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO, Principal principal) {
        ArticleDTO createdArticle = articleService.createArticle(articleDTO, principal.getName());
        return ResponseEntity.ok(createdArticle);
    }
}