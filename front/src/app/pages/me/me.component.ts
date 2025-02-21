import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'src/app/models/subscription';
import { User } from 'src/app/models/user';
import { ThemeService } from 'src/app/services/theme.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss'],
})
export class MeComponent implements OnInit {
  userForm: FormGroup;
  subscriptions: Subscription[] = [];
  userId?: number;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private themeService: ThemeService,
    private router: Router
  ) {
    this.userForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {
    this.loadUser();
  }

  loadUser(): void {
    this.userService.getMe().subscribe({
      next: (user: User) => {
        this.userId = user.id;
        this.userForm.patchValue({
          username: user.username,
          email: user.email,
        });
        this.loadSubscriptions();
      },
      error: (err) => {
        console.error('Erreur getMe', err);
      },
    });
  }

  loadSubscriptions(): void {
    this.userService.getUserSubscriptions().subscribe({
      next: (subs) => (this.subscriptions = subs),
      error: (err) => {
        console.error('Erreur getUserSubscriptions', err);
      },
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  unsubscribe(sub: Subscription): void {
    const themeId = sub.theme.id;
    this.themeService.unsubscribeFromTheme(themeId).subscribe({
      next: () => {
        alert('Désabonnement réussi !');
        this.subscriptions = this.subscriptions.filter((s) => s !== sub);
        this.loadSubscriptions();
      },
      error: (err) => {
        console.error('Erreur lors du désabonnement', err);
        alert('Impossible de se désabonner');
      },
    });
  }

  saveUser(): void {
    if (this.userForm.valid) {
      this.userService.updateMe(this.userForm.value).subscribe({
        next: (updatedUser) => {
          alert('Profil mis à jour !');
          this.loadUser();
        },
        error: (err) => {
          console.error('Erreur updateMe', err);
          alert('Erreur lors de la mise à jour du profil');
        },
      });
    }
  }
}
