import { Component, OnInit } from '@angular/core';
import { UserserviceService } from '../userservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  users: any[] = [];
  totalPages: number = 0;
  page: number = 0;
  size: number = 5;

  constructor(private userService: UserserviceService,private router: Router) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers() {
    this.userService.getUsers(this.page, this.size)
      .subscribe({
        next: (response) => {
          console.log(response);  
          this.users = response.content;
          this.totalPages = response.totalPages;
        },
        error: (err) => console.error(err)
      });
  }

  nextPage() {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadUsers();
    }
  }

  prevPage() {
    if (this.page > 0) {
      this.page--;
      this.loadUsers();
    }
  }
  addUser(){
    this.router.navigate(['/user']);
  }
  editUser(user:any){
    this.router.navigate(['/user',user.id]);
    
  }
}
