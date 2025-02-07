import { Component, OnInit } from '@angular/core';
import { unsubscribe } from 'diagnostics_channel';
import { Theme } from 'src/app/models/theme';
import { ThemeService } from 'src/app/services/theme.service';

@Component({
  selector: 'app-theme-list',
  templateUrl: './theme-list.component.html',
  styleUrls: ['./theme-list.component.scss']
})
export class ThemeListComponent implements OnInit {
  themes: Theme[] = [];

  constructor(private themeService: ThemeService) {}

  ngOnInit(): void {
    this.loadThemes();
  }

  loadThemes(): void {
    this.themeService.getThemes().subscribe({
      next: (data) => this.themes = data,
      error: (err) => console.error("Erreur lors du chargement des thèmes", err)
    });
  }

  subscribeToTheme(themeId: number): void {
  this.themeService.subscribeToTheme(themeId).subscribe({
    next: () => alert("Abonnement réussi !"),
    error: (err) => console.error("Erreur lors de l'abonnement", err)
  });
  }

  unsubscribe(themeId: number): void {
    this.themeService.unsubscribeFromTheme(themeId).subscribe({
      next: () => {
        alert("Désabonnement réussi!");
      },
      error: (err) => {
        console.error("Erreur de désabonnement", err);
        alert("Erreur : " + err.error.message || err.message);
      }
    });
  }
}
