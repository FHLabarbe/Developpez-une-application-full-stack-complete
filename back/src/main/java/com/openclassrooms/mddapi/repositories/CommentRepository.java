package com.openclassrooms.mddapi.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.mddapi.entities.Comment;
import com.openclassrooms.mddapi.entities.Article;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByArticle(Article article);
}