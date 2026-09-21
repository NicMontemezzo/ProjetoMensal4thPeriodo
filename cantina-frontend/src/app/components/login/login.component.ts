import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  senha: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {
    this.errorMessage = '';

    this.authService.login({ username: this.username, senha: this.senha }).subscribe({
      next: (response) => {
        console.log('Login realizado com sucesso', response);
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error('Erro no login', err);
        this.errorMessage = 'Usuário ou senha inválidos';
      }
    });
  }
}