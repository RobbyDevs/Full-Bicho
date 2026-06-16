import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../core/services/auth.service';
import { UserService } from '../../core/services/user.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
  readonly auth = inject(AuthService);
  private readonly fb = inject(FormBuilder);
  private readonly userService = inject(UserService);

  loading = false;
  alert: { type: 'success' | 'danger'; message: string } | null = null;

  form = this.fb.group({
    username: [''],
    email: ['', [Validators.email]],
    cpf: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });


  ngOnInit(): void {
    const user = this.auth.currentUser;

    if (!user) {
      return;
    }

    this.form.patchValue({
      username: user.username,
      email: user.email,
      cpf: user.cpf
    });
  }
  

  submit(): void {
    this.alert = null;

    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.loading = true;
    this.userService.update({
      username: this.form.value.username || '',
      email: this.form.value.email || '',
      cpf: this.form.value.cpf || '',
      password: this.form.value.password || ''
    }).subscribe({
      next: message => {
        this.loading = false;
        this.alert = { type: 'success', message: message || 'Dados atualizados.' };
        const cpf = this.form.value.cpf || '';
        this.userService.findByCpf(cpf).subscribe(user => this.auth.updateStoredUser(user));
      },
      error: error => {
        this.loading = false;
        this.alert = { type: 'danger', message: error?.error || 'Não foi possível atualizar.' };
      }
    });
  }
}
