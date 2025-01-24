import { Component, OnInit } from '@angular/core';
import { Theme, ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html'
})
export class ThemeListComponent implements OnInit {

  themes: Theme[] = [];
  newTheme: Theme = { name: '', description: '' };

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.loadThemes();
    console.log("Le composant est bien chargé");
  }

  loadThemes(): void {
    this.themeService.getAll().subscribe({
      next: (data) => this.themes = data,
      error: (err) => console.error('Erreur getAll', err)
    });
  }

  addTheme(): void {
    this.themeService.createTheme(this.newTheme).subscribe({
      next: (created) => {
        console.log('Created theme: ', created);
        this.loadThemes();
        this.newTheme = { name: '', description: '' };
      },
      error: (err) => console.error('Erreur createTheme', err)
    });
  }
}