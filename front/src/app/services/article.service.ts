import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../models/article';
import { ArticleDetail } from '../models/article-detail';
import { CommentDTO } from '../models/commentDTO';

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  private baseUrl = 'http://localhost:8080/api/articles';

  constructor(private http: HttpClient) {}

  getArticles(): Observable<Article[]> {
    return this.http.get<Article[]>(this.baseUrl);
  }

  createArticle(article: Article): Observable<Article> {
    return this.http.post<Article>(this.baseUrl, article);
  }

  getArticleDetail(articleId: number): Observable<ArticleDetail> {
    return this.http.get<ArticleDetail>(`${this.baseUrl}/${articleId}`);
  }

  addComment(articleId: number, content: string): Observable<CommentDTO> {
    return this.http.post<CommentDTO>(`${this.baseUrl}/${articleId}/comments`, {content});
  }

}