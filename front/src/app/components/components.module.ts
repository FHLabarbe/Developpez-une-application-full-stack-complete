import { CommonModule, NgIf } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { BackButtonComponent } from '../components/back-button/back-button.component';
import { HeaderDesktopComponent } from './header-desktop/header-desktop.component';
import { HeaderMobileComponent } from './header-mobile/header-mobile.component';
import { HeaderComponent } from './header/header.component';
import { MatMenuModule } from '@angular/material/menu';

@NgModule({
  declarations: [
    BackButtonComponent,
    HeaderDesktopComponent,
    HeaderMobileComponent,
    HeaderComponent
  ],
  imports: [
    MatIconModule,
    NgIf,
    RouterModule,
    MatMenuModule,
    CommonModule
  ],
  exports: [
    BackButtonComponent,
    HeaderDesktopComponent,
    HeaderMobileComponent,
    HeaderComponent
  ]
})
export class ComponentsModule { }