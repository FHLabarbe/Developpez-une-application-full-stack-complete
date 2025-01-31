import { Component } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  showNav = false;
  showProfile = false;

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateHeader(event.url);
      }
    });
  }

  updateHeader(url: string): void {
  
    if (url.includes('/login') || url.includes('/register')) {
      this.showNav = false;
      this.showProfile = false;
    }
    else {
      this.showNav = true;
      this.showProfile = true;
    }
  }
}