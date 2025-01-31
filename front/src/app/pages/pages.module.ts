import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { LoginComponent } from './login/login.component';
import { ThemeListComponent } from './theme-list/theme-list.component';
import { WelcomeComponent } from './welcome/welcome.component';



@NgModule({
  declarations: [
    RegisterComponent,
    LoginComponent,
    WelcomeComponent,
    ThemeListComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  exports: [
    ThemeListComponent
  ]
})
export class PagesModule { }
