import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from '../../../environments/environment';
import { LoginRequest, RegisterRequest, User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly apiUrl = environment.apiUrl;
  private readonly userKey = 'fullBicho.user';
  private readonly tokenKey = 'fullBicho.token';
  private readonly userSubject = new BehaviorSubject<User | null>(this.readStoredUser());

  readonly currentUser$ = this.userSubject.asObservable();

  constructor(private readonly http: HttpClient) {}

  get currentUser(): User | null {
    return this.userSubject.value;
  }

  get token(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  login(payload: LoginRequest): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/app/user/login`, payload).pipe(
      tap(user => this.setSession(user))
    );
  }

  register(payload: RegisterRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/app/user/saveUser`, payload, {
      responseType: 'text'
    });
  }

  setSession(user: User, token?: string): void {
    localStorage.setItem(this.userKey, JSON.stringify(user));
    if (token) {
      localStorage.setItem(this.tokenKey, token);
    }
    this.userSubject.next(user);
  }

  updateStoredUser(user: User): void {
    localStorage.setItem(this.userKey, JSON.stringify(user));
    this.userSubject.next(user);
  }

  logout(): void {
    localStorage.removeItem(this.userKey);
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem('fullBicho.activeDraw');
    this.userSubject.next(null);
  }

  isAuthenticated(): boolean {
    return !!this.currentUser;
  }

  isAdmin(): boolean {
    return this.currentUser?.role === 'ADMIN';
  }

  private readStoredUser(): User | null {
    const stored = localStorage.getItem(this.userKey);

    if (!stored) {
      return null;
    }

    try {
      return JSON.parse(stored) as User;
    } catch {
      localStorage.removeItem(this.userKey);
      return null;
    }
  }
}
