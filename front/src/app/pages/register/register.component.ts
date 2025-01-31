import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserRegister } from 'src/app/models/user-register';
import { RegisterService } from 'src/app/services/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent{
  registerForm: FormGroup;
  registrationMessage: string | null = null;

  constructor(private location: Location, private fb: FormBuilder, private registerService: RegisterService, private router: Router) {
    this.registerForm = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(4)]]
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const userData: UserRegister = this.registerForm.value;
      this.registerService.register(userData).subscribe({
        next: (response) => {
          console.log('Inscription réussie', response);
          localStorage.setItem('token', response.token);
          this.router.navigate(['/login']);
        },
        error: (err) => {
          console.error('Erreur lors de l’inscription', err);
        }
      });
    }
  }

  goBack(): void {
    this.location.back();
  } 
}
