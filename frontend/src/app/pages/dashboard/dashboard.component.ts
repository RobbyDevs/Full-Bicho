import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Animal } from '../../core/models/animal.model';
import { Bet, BetStats, BetType } from '../../core/models/bet.model';
import { Draw, RoundDigit } from '../../core/models/draw.model';
import { User } from '../../core/models/user.model';
import { AnimalService } from '../../core/services/animal.service';
import { AuthService } from '../../core/services/auth.service';
import { BetService } from '../../core/services/bet.service';
import { DrawService } from '../../core/services/draw.service';
import { UserService } from '../../core/services/user.service';

interface DashboardAlert {
  type: 'success' | 'danger' | 'warning' | 'info';
  message: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  private readonly fb = inject(FormBuilder);
  private readonly auth = inject(AuthService);
  private readonly userService = inject(UserService);
  private readonly betService = inject(BetService);
  private readonly drawService = inject(DrawService);
  private readonly animalService = inject(AnimalService);

  readonly animals = this.animalService.animals;
  readonly betTypes: Array<{ value: BetType; label: string; helper: string }> = [
    { value: 'GROUP', label: 'Grupo', helper: 'Escolha um animal ou digite de 1 a 25.' },
    { value: 'TENS', label: 'Dezena', helper: 'Digite de 00 a 99. ' },
    { value: 'HUNDREDS', label: 'Centena', helper: 'Digite de 000 a 999. ' },
    { value: 'THOUSANDS', label: 'Milhar', helper: 'Digite de 0000 a 9999. ' }
  ];

  user: User | null = null;
  activeDraw: Draw | null = null;
  latestDraw: Draw | null = null;

  selectedAnimal: Animal | null = null;
  bets: Bet[] = [];
  stats: BetStats = this.betService.calculateStats([]);
  resultDigits: RoundDigit[] = [];
  resultBet: Bet | null = null;

  draws: Draw[] = [];
  selectedDrawId: number | null = null;
  loadingDraws = false;



  alert: DashboardAlert | null = null;
  loading = false;
  creatingDraw = false;
  placingBet = false;
  showConfirmation = false;
  showResult = false;

  form = this.fb.group({
    type: ['GROUP' as BetType, Validators.required],
    chosenNumber: [null as number | null, Validators.required],
    amount: [10, [Validators.required, Validators.min(1)]]
  });


  ngOnInit(): void {
    this.user = this.auth.currentUser;
    this.form.controls.type.valueChanges.subscribe(() => this.adjustChosenNumber());
    this.loadDashboard();
    this.loadLatestDraw();


  }

  private getLatestDraw(draws: Draw[]): Draw | null {
  if (!draws.length) {
    return null;
  }

  return [...draws].sort((a, b) => {
    const idA = a.drawId || 0;
    const idB = b.drawId || 0;

    return idB - idA;
  })[0];
}


  private loadLatestDraw(): void {
  this.drawService.findAllDraws().subscribe({
    next: draws => {
      const latest = this.getLatestDraw(draws);

      this.latestDraw = latest;
      this.activeDraw = latest?.status === 'OPEN' ? latest : null;
    },
    error: () => {
      this.latestDraw = null;
      this.activeDraw = null;
    }
  });
}

  

  selectAnimal(animal: Animal): void {
    this.selectedAnimal = animal;

    if (this.form.controls.type.value === 'GROUP') {
      this.form.controls.chosenNumber.setValue(animal.group);
    }
  }

  closeAlert(): void {
  this.alert = null;
}


  openConfirmation(): void {
    this.alert = null;

    if (!this.user) {
      this.alert = { type: 'danger', message: 'Sessão inválida. Entre novamente.' };
      return;
    }

    if (!this.latestDraw) {
      this.alert = { type: 'warning', message: 'Nenhum sorteio cadastrado no sistema.' };
      return;
    }

    if (!this.isChosenNumberValid()) {
      this.form.controls.chosenNumber.setErrors({ invalidNumber: true });
      this.form.markAllAsTouched();
      return;
    }

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.showConfirmation = true;
  }

  

  placeBet(): void {
    if (!this.user || !this.latestDraw || this.form.invalid) {
      return;
    }

    const payload = this.form.getRawValue();
    this.placingBet = true;
    this.alert = null;

    this.betService.placeBet({
      userId: this.user.userId,
      drawId: this.latestDraw.drawId,
      type: payload.type || 'GROUP',
      chosenNumber: Number(payload.chosenNumber),
      amount: Number(payload.amount)
    }).subscribe({
      next: message => {
        this.placingBet = false;
        this.showConfirmation = false;
        this.alert = { type: 'success', message: message || 'Aposta registrada com sucesso.' };
        this.loadDashboard();
      },
      error: error => {
        this.placingBet = false;
        this.showConfirmation = false;
        this.alert = { type: 'danger', message: error?.error || 'Não foi possível registrar a aposta.' };
      }
    });
  }


  closeConfirmation(): void {
    this.showConfirmation = false;
  }

  selectedTypeLabel(): string {
    const type = this.form.controls.type.value || 'GROUP';
    return this.betTypes.find(item => item.value === type)?.label || type;
  }

  selectedTypeHelper(): string {
    const type = this.form.controls.type.value || 'GROUP';
    return this.betTypes.find(item => item.value === type)?.helper || '';
  }

  chosenDisplay(): string {
    const number = Number(this.form.controls.chosenNumber.value ?? 0);
    const type = this.form.controls.type.value || 'GROUP';

    if (type === 'GROUP') {
      const animal = this.animalService.getByGroup(number);
      return animal ? `${number} - ${animal.name}` : String(number);
    }

    return this.animalService.padNumber(number, type);
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

    return `${typeLabels[bet.type]} ${number}${animal ? ` (${animal.name})` : ''}`;
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

  statusClass(status?: string): string {
    if (status === 'WIN') {
      return 'status-win';
    }
    if (status === 'LOSE') {
      return 'status-lose';
    }
    return 'status-pending';
  }

  trackAnimal(_: number, animal: Animal): number {
    return animal.group;
  }

  trackBet(_: number, bet: Bet): number {
    return bet.betId;
  }

  private loadDashboard(): void {
    this.loading = true;
    this.loadUser();

    if (!this.user) {
      this.loading = false;
      return;
    }

    this.betService.findByUserId(this.user.userId).subscribe(bets => {
      this.loading = false;
      this.bets = this.sortBets(bets);
      this.stats = this.betService.calculateStats(bets);
    });
  }

  private loadUser(): void {
    if (!this.user?.cpf) {
      return;
    }

    this.userService.findByCpf(this.user.cpf).subscribe({
      next: user => {
        this.user = user;
        this.auth.updateStoredUser(user);
      }
    });
  }

  private adjustChosenNumber(): void {
    const type = this.form.controls.type.value;

    if (type === 'GROUP' && this.selectedAnimal) {
      this.form.controls.chosenNumber.setValue(this.selectedAnimal.group);
      return;
    }

    this.form.controls.chosenNumber.setValue(null);
  }

  private isChosenNumberValid(): boolean {
    const type = this.form.controls.type.value || 'GROUP';
    const number = Number(this.form.controls.chosenNumber.value);

    if (!Number.isInteger(number) || number < 0) {
      return false;
    }

    const limits: Record<BetType, number> = {
      GROUP: 25,
      TENS: 99,
      HUNDREDS: 999,
      THOUSANDS: 9999
    };

    if (type === 'GROUP') {
      return number >= 1 && number <= 25;
    }

    return number <= limits[type];
  }


  private sortBets(bets: Bet[]): Bet[] {
    return [...bets].sort((a, b) => {
      const dateA = a.createdAt ? new Date(a.createdAt).getTime() : 0;
      const dateB = b.createdAt ? new Date(b.createdAt).getTime() : 0;
      return dateB - dateA || (b.betId || 0) - (a.betId || 0);
    });
  }
}
