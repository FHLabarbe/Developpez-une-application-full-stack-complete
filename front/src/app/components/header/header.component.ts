import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  showNav = false;
  showProfile = false;
  sourceImage = "/assets/icon_user.png";

  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateHeader(event.url);
      }
    });
  }

  ngOnInit(){
    this.updateHeader(this.router.url);
  }

  redirectToMe(){
    this.router.navigate(['/me']);
  }

  updateHeader(url: string): void {
    console.log("url détectée par updateHeader :",url);
    if (url.includes('/login') || url.includes('/register')) {
      this.showNav = false;
      this.showProfile = false;
    }
    else if (url.includes('/me')){
      this.sourceImage = '/assets/icon_user_purple.png'
    }
    else {
      this.sourceImage = "/assets/icon_user.png";
      this.showNav = true;
      this.showProfile = true;
    } 
  }
}