import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, map, Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Bet, BetRequest, BetStats, BetStatus } from '../models/bet.model';

@Injectable({ providedIn: 'root' })
export class BetService {
  private readonly apiUrl = `${environment.apiUrl}/app/user/bet`;

  constructor(private readonly http: HttpClient) {}

  placeBet(payload: BetRequest): Observable<string> {
    return this.http.post(`${this.apiUrl}/placeBet`, payload, {
      responseType: 'text'
    });
  }

  findByUserId(userId: number): Observable<Bet[]> {
    const params = new HttpParams().set('userId', userId);
    return this.http.get<Bet[]>(`${this.apiUrl}/findBetsByUserId`, { params }).pipe(
      map(bets => bets ?? []),
      catchError(() => of([]))
    );
  }

  findByStatus(status: BetStatus): Observable<Bet[]> {
    const params = new HttpParams().set('betStatus', status);
    return this.http.get<Bet[]>(`${this.apiUrl}/findBetsByStatus`, { params }).pipe(
      map(bets => bets ?? []),
      catchError(() => of([]))
    );
  }

  calculateStats(bets: Bet[]): BetStats {
    const total = bets.length;
    const wins = bets.filter(bet => bet.status === 'WIN').length;
    const losses = bets.filter(bet => bet.status === 'LOSE').length;
    const pending = bets.filter(bet => bet.status === 'PENDING').length;
    const totalStaked = bets.reduce((sum, bet) => sum + Number(bet.amount || 0), 0);
    const totalWon = bets.reduce((sum, bet) => sum + Number(bet.payout || 0), 0);
    const totalLost = bets
      .filter(bet => bet.status === 'LOSE')
      .reduce((sum, bet) => sum + Number(bet.amount || 0), 0);

    return {
      total,
      wins,
      losses,
      pending,
      winRate: total ? (wins / total) * 100 : 0,
      totalStaked,
      totalWon,
      totalLost,
      profit: totalWon - totalStaked
    };
  }
}
