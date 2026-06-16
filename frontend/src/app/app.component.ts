import { Component, inject } from '@angular/core';
import { AsyncPipe, NgIf } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './core/services/auth.service';
import { LayoutComponent } from './layout/layout.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [AsyncPipe, LayoutComponent, NgIf, RouterOutlet],
  templateUrl: './app.component.html'
})
export class AppComponent {
  private readonly auth = inject(AuthService);
  readonly user$ = this.auth.currentUser$;
}

