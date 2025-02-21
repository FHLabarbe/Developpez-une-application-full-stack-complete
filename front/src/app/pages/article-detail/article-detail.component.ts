import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ArticleDetail } from 'src/app/models/article-detail';
import { CommentDTO } from 'src/app/models/commentDTO';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-article-detail',
  templateUrl: './article-detail.component.html',
  styleUrls: ['./article-detail.component.scss'],
})
export class ArticleDetailComponent implements OnInit {
  articleDetail?: ArticleDetail;
  commentForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService,
    private fb: FormBuilder
  ) {
    this.commentForm = this.fb.group({
      content: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (!localStorage.getItem('reloaded')) {
      localStorage.setItem('reloaded', 'true');
      window.location.reload();
    } else {
      localStorage.removeItem('reloaded');
      const articleId = Number(this.route.snapshot.paramMap.get('id'));
      this.loadArticleDetail(articleId);
    }
  }

  loadArticleDetail(articleId: number): void {
    this.articleService.getArticleDetail(articleId).subscribe({
      next: (data) => (this.articleDetail = data),
      error: (err) =>
        console.error(
          "Erreur lors du chargement des détails de l'article",
          err
        ),
    });
  }

  submitComment(): void {
    if (this.commentForm.valid && this.articleDetail) {
      const articleId = this.articleDetail.id;
      const content = this.commentForm.value.content;
      this.articleService.addComment(articleId, content).subscribe({
        next: (newComment: CommentDTO) => {
          this.articleDetail?.comments.push(newComment);
          this.commentForm.reset();
        },
        error: (err) => {
          console.error("Erreur lors de l'ajout du commentaire", err);
          alert("Erreur lors de l'ajout du commentaire");
        },
      });
    }
  }
}
