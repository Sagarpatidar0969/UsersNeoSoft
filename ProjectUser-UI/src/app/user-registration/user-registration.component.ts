import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpServiceService } from '../http-service.service';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent implements OnInit {

  userForm!: FormGroup;
  submitted = false;
  private baseUrl = 'http://localhost:8084/user'; // backend URL

  constructor(private fb: FormBuilder, private httpService: HttpServiceService) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      mobileNo: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      dob: ['', Validators.required],
      flag: [1]  // default
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.userForm.controls; }

  onSubmit(): void {
    this.submitted = true;

    // stop here if form is invalid
    if (this.userForm.invalid) {
      alert('Please fill all required fields correctly!');
      return;
    }

    const headers = { 'Content-Type': 'application/json' };
    this.httpService.post<any>(`${this.baseUrl}/add`, this.userForm.value, headers)
      .subscribe({
        next: (res: any) => {
          console.log('User Created Successfully:', res);
          alert('User Registered Successfully!');
          this.onReset();
        },
        error: (err: any) => {
          console.error('Error while creating user:', err);
          alert('Failed to register user!');
        }
      });
  }

  onReset(): void {
    this.submitted = false;
    this.userForm.reset({ flag: 1 });
  }
}
