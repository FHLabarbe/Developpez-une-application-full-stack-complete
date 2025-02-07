import { NgIf } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { BackButtonComponent } from '../components/back-button/back-button.component';
import { HeaderComponent } from './header/header.component';

@NgModule({
  declarations: [
    BackButtonComponent,
    HeaderComponent
  ],
  imports: [
    MatIconModule,
    NgIf,
    RouterModule,
  ],
  exports: [
    BackButtonComponent,
    HeaderComponent,
  ]
})
export class ComponentsModule { }