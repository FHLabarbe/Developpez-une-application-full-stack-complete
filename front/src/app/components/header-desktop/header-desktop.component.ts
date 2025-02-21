import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header-desktop',
  templateUrl: './header-desktop.component.html',
  styleUrls: ['./header-desktop.component.scss'],
})
export class HeaderDesktopComponent implements OnInit {
  showNav = false;
  showProfile = false;
  sourceImage = '/assets/icon_user.png';

  constructor(private router: Router) {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateHeader(event.url);
      }
    });
  }

  ngOnInit() {
    this.updateHeader(this.router.url);
  }

  redirectToMe() {
    this.router.navigate(['/me']);
  }

  redirectToArticles() {
    this.router.navigate(['/articles']);
  }

  updateHeader(url: string): void {
    if (url.includes('/login') || url.includes('/register')) {
      this.showNav = false;
      this.showProfile = false;
    } else if (url.includes('/me')) {
      this.sourceImage = '/assets/icon_user_purple.png';
      this.showNav = true;
      this.showProfile = true;
    } else {
      this.sourceImage = '/assets/icon_user.png';
      this.showNav = true;
      this.showProfile = true;
    }
  }
}
