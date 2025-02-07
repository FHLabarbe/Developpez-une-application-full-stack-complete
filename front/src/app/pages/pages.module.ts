import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

import { ArticleListComponent } from './articles/articles.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { ComponentsModule } from '../components/components.module';
import { ArticleCreateComponent } from './article-create/article-create.component';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select'
import { MeComponent } from './me/me.component';

@NgModule({
  declarations: [
    LoginComponent,
    ArticleCreateComponent,
    RegisterComponent,
    WelcomeComponent,
    ThemeListComponent,
    ArticleListComponent,
    HomeComponent,
    MeComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatOptionModule,
    RouterModule,
    ComponentsModule,
    MatSelectModule,
  ],
  exports: [
    LoginComponent,
    RegisterComponent,
    WelcomeComponent,
    ThemeListComponent,
    ArticleListComponent,
    HomeComponent,
    RouterModule,
    MeComponent
  ]
})
export class PagesModule { }