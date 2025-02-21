import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Subscription } from '../models/subscription';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = 'http://localhost:8080/api/me';

  constructor(private http: HttpClient) {}

  getMe(): Observable<User> {
    return this.http.get<User>(this.baseUrl);
  }

  updateMe(userData: Partial<User>): Observable<User> {
    return this.http.put<User>(`${this.baseUrl}`, userData);
  }

  getUserSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(
      `http://localhost:8080/api/themes/subscribe`
    );
  }
}
