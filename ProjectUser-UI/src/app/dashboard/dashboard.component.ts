import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

 
  constructor(private router: Router) { }

  ngOnInit(): void {
   
    const user = localStorage.getItem('user');
    if (user) {
      const userData = JSON.parse(user);
     
    } else {
      
      this.router.navigate(['/login']);
    }
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigate(['/login']);
  }
}