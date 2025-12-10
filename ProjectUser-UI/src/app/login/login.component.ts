import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpServiceService } from '../http-service.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm!:FormGroup;
  baseUrl = 'http://localhost:8084/auth';

  constructor( private fb : FormBuilder,private http :HttpServiceService,private router :Router) { }

  ngOnInit(): void {

    this.loginForm = this.fb.group({
      email: ['',[Validators.required,Validators.email]],
      password:['',Validators.required]
    });


  }
  get f(){
    return this.loginForm.controls;
  }

  onLogin(){
    if(this.loginForm.invalid){
      return;
    }
    this.http.post<any>(`${this.baseUrl}/login`,this.loginForm.value).subscribe({
      next:(res) =>{
        alert('Login Successful');
         localStorage.setItem('user', JSON.stringify(res));
      this.router.navigate(['/dashboard']);
          },
          error:() =>{
            alert('Invalid Email or Password');
          }
    });

  }

}
