import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ThemeListComponent } from './pages/theme-list/theme-list.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';
import { ArticleListComponent } from './pages/articles/articles.component';
import { ArticleCreateComponent } from './pages/article-create/article-create.component';
import { MeComponent } from './pages/me/me.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'themeList', component: ThemeListComponent, canActivate: [AuthGuard] },
  { path: 'articles', component: ArticleListComponent, canActivate: [AuthGuard] },
  { path: 'articles/create', component: ArticleCreateComponent, canActivate: [AuthGuard]},
  { path: 'me', component: MeComponent, canActivate: [AuthGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})

export class AppRoutingModule {}
