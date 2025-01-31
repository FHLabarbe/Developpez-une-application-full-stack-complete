import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserRegister } from '../models/user-register';
import { RegisterResponse } from '../models/register-response';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  private apiUrl = 'http://localhost:8080/api/auth/register';

  constructor(private http: HttpClient) {}

  register(userData: UserRegister): Observable<RegisterResponse> {
    return this.http.post<any>(this.apiUrl, userData);
  }
}