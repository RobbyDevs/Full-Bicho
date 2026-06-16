import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Bet, BetStats, BetType } from '../../core/models/bet.model';
import { AnimalService } from '../../core/services/animal.service';
import { AuthService } from '../../core/services/auth.service';
import { BetService } from '../../core/services/bet.service';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './history.component.html'
})
export class HistoryComponent implements OnInit {
  private readonly auth = inject(AuthService);
  private readonly betService = inject(BetService);
  private readonly animalService = inject(AnimalService);

  bets: Bet[] = [];
  stats: BetStats = this.betService.calculateStats([]);
  loading = false;

  ngOnInit(): void {
    this.loadBets();
  }

  loadBets(): void {
    const user = this.auth.currentUser;

    if (!user) {
      return;
    }

    this.loading = true;
    this.betService.findByUserId(user.userId).subscribe(bets => {
      this.loading = false;
      this.bets = this.sortBets(bets);
      this.stats = this.betService.calculateStats(bets);
    });
  }

  betLabel(bet: Bet): string {
    const typeLabels: Record<BetType, string> = {
      GROUP: 'Grupo',
      TENS: 'Dezena',
      HUNDREDS: 'Centena',
      THOUSANDS: 'Milhar'
    };

    const number = this.animalService.padNumber(bet.chosenNumber, bet.type);
    const animal = bet.type === 'GROUP'
      ? this.animalService.getByGroup(bet.chosenNumber)
      : this.animalService.getAnimalFromNumber(bet.chosenNumber);

    return `${typeLabels[bet.type]}: ${number}${animal ? ` (${animal.name})` : ''}`;
  }

  resultLabel(bet: Bet): string {
    if (bet.status === 'PENDING') {
      return 'pendente';
    }

    if (bet.status === 'LOSE') {
      return 'perdeu';
    }

    return `ganhou ${this.formatCurrency(bet.payout)}`;
  }

  statusClass(status?: string): string {
    if (status === 'WIN') {
      return 'status-win';
    }
    if (status === 'LOSE') {
      return 'status-lose';
    }
    return 'status-pending';
  }

  positionLabel(position?: string | null): string {
    const labels: Record<string, string> = {
      HEAD: 'Cabeça',
      SECOND: '2ª rodada',
      THIRD: '3ª rodada',
      FOURTH: '4ª rodada',
      FIFTH: '5ª rodada'
    };

    return position ? labels[position] || position : '-';
  }

  trackBet(_: number, bet: Bet): number {
    return bet.betId;
  }

  private sortBets(bets: Bet[]): Bet[] {
    return [...bets].sort((a, b) => {
      const dateA = a.createdAt ? new Date(a.createdAt).getTime() : 0;
      const dateB = b.createdAt ? new Date(b.createdAt).getTime() : 0;
      return dateB - dateA || (b.betId || 0) - (a.betId || 0);
    });
  }

  private formatCurrency(value: number): string {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(value || 0);
  }
}
