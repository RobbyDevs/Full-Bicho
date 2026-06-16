import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { User, UserUpdateRequest } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class UserService {
  private readonly apiUrl = `${environment.apiUrl}/app/user`;

  constructor(private readonly http: HttpClient) {}

  findByCpf(cpf: string): Observable<User> {
    const params = new HttpParams().set('cpf', cpf);
    return this.http.get<User>(`${this.apiUrl}/findUserByCpf`, { params });
  }

  update(payload: UserUpdateRequest): Observable<string> {
    return this.http.patch(`${this.apiUrl}/updateUser`, payload, {
      responseType: 'text'
    });
  }
}
