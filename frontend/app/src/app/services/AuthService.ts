import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { UserLoginDTO } from '../dto/UserLoginDTO';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private API = 'http://localhost:8080/app/user';
  private USER_KEY = 'auth_user';

  constructor(private http: HttpClient) {}

  login(data: UserLoginDTO): Observable<User> {
    return this.http.post<User>(`${this.API}/login`, data);
  }

  setUser(user: User) {
    localStorage.setItem(this.USER_KEY, JSON.stringify(user));
  }

  getUser(): User | null {
    return JSON.parse(localStorage.getItem(this.USER_KEY) || 'null');
  }

  isLogged(): boolean {
    return !!this.getUser();
  }

  logout() {
    localStorage.removeItem(this.USER_KEY);
  }
}