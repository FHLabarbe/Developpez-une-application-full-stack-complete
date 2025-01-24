import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Theme {
  id?: number;
  name: string;
  description?: string;
}

@Injectable({
  providedIn: 'root'
})
export class ThemeService {

  private baseUrl = 'http://localhost:8080/api/themes';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Theme[]> {
    return this.http.get<Theme[]>(this.baseUrl);
  }

  createTheme(theme: Theme): Observable<Theme> {
    return this.http.post<Theme>(this.baseUrl, theme);
  }
}