import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Theme } from 'src/app/models/theme';
import { ArticleService } from 'src/app/services/article.service';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-article-create',
  templateUrl: './article-create.component.html',
  styleUrls: ['./article-create.component.scss']
})
export class ArticleCreateComponent implements OnInit {
  articleForm: FormGroup;
  themes: Theme[] = [];

  constructor(
    private fb: FormBuilder,
    private themeService: ThemeService,
    private articleService: ArticleService,
    private router: Router
  ) {

    this.articleForm = this.fb.group({
      themeId: [null, Validators.required],
      title: ['', [Validators.required, Validators.maxLength(200)]],
      content: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.loadThemes();
  }

  loadThemes(): void {
    this.themeService.getThemes().subscribe({
      next: (data) => this.themes = data,
      error: (err) => console.error("Erreur lors du chargement des thèmes", err)
    });
  }

  onSubmit(): void {
    if (this.articleForm.valid) {
      this.articleService.createArticle(this.articleForm.value).subscribe({
        next: (result) => {
          alert("Article créé avec succès !");
          this.router.navigate(['/articles']);
        },
        error: (err) => {
          console.error("Erreur lors de la création de l'article", err);
          alert("Erreur lors de la création de l'article");
        }
      });
    }
  }
}