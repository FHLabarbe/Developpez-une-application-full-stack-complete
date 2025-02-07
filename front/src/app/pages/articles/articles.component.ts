import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Article } from 'src/app/models/article';
import { ArticleService } from 'src/app/services/article.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class ArticleListComponent implements OnInit {
  articles: Article[] = [];

  constructor(private articleService: ArticleService) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  redirect(): void {
    
  }

  loadArticles(): void {
    this.articleService.getArticles().subscribe({
      next: (data) => this.articles = data,
      error: (err) => console.error("Erreur lors du chargement des articles", err)
    });
  }
}