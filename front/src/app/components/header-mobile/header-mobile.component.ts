import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header-mobile',
  templateUrl: './header-mobile.component.html',
  styleUrls: ['./header-mobile.component.scss'],
})
export class HeaderMobileComponent {
  showMenu = false;
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

  updateHeader(url: string): void {
    if (url.includes('/login') || url.includes('/register')) {
      this.showMenu = false;
    } else if (url.includes('/me')) {
      this.sourceImage = '/assets/icon_user_purple.png';
    } else {
      this.sourceImage = '/assets/icon_user.png';
      this.showMenu = true;
    }
  }
}
