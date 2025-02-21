import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Theme } from '../models/theme';
import { Subscription } from '../models/subscription';

@Injectable({
  providedIn: 'root',
})
export class ThemeService {
  private baseUrl = 'http://localhost:8080/api/themes';

  constructor(private http: HttpClient) {}

  public getThemes(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.baseUrl);
  }

  public subscribeToTheme(themeId: number): Observable<any> {
    const url = `${this.baseUrl}/${themeId}/subscribe`;
    return this.http.post(url, {});
  }

  public unsubscribeFromTheme(themeId: number): Observable<any> {
    const url = `${this.baseUrl}/${themeId}/subscribe`;
    return this.http.delete(url);
  }

  public getSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(`${this.baseUrl}/subscribe`);
  }
}

export { Theme };
