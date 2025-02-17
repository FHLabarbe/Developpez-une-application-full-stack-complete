import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
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
  sortControl = new FormControl('dateDesc');

  constructor(private articleService: ArticleService, private router: Router) {}

  ngOnInit(): void {
    this.loadArticles();
  }

  toDetails(articleId?: number) {
    this.router.navigate([`/articles-details/${articleId}`]);
  }

  loadArticles(): void {
    this.articleService.getArticles().subscribe({
      next: (data) => (this.articles = data),
      error: (err) =>
        console.error('Erreur lors du chargement des articles', err),
    });
  }

  onSortChange(sort: string): void {
    if (sort === 'dateDesc') {
      this.articles.sort((a, b) => new Date(b.createdAt!).getTime() - new Date(a.createdAt!).getTime());
    } else if (sort === 'dateAsc') {
      this.articles.sort((a, b) => new Date(a.createdAt!).getTime() - new Date(b.createdAt!).getTime());
    }
  }
}
