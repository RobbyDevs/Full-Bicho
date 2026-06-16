import { Component, inject } from '@angular/core';
import { AsyncPipe, CurrencyPipe, NgIf } from '@angular/common';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService } from '../core/services/auth.service';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [AsyncPipe, CurrencyPipe, NgIf, RouterLink, RouterLinkActive],
  templateUrl: './layout.component.html'
})
export class LayoutComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);
  readonly user$ = this.auth.currentUser$;

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
