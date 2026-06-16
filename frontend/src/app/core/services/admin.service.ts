import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class AdminService {
  private readonly apiUrl = `${environment.apiUrl}/app/admin`;

  constructor(private readonly http: HttpClient) {}

  findAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/findAllUsers`).pipe(
      map(users => users ?? []),
      catchError(() => of([]))
    );
  }

  findUserByCpf(cpf: string): Observable<User> {
    const params = new HttpParams().set('cpf', cpf);
    return this.http.get<User>(`${this.apiUrl}/findUserByCpf`, { params });
  }
}
