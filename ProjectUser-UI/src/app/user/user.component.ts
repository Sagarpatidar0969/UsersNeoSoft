import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserserviceService } from '../userservice.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userForm!: FormGroup;
  save: boolean = false;
  userId: number | null = null;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserserviceService,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    // Initialize Reactive Form
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mobileNo: ['', Validators.required],
      dob: ['', Validators.required],
      flag: [1, Validators.required],
      password: ['', Validators.required]
    });

    // Get ID from route
    this.userId = this.route.snapshot.params['id'];
    if (this.userId) {
      this.save = true;
      this.loadUser(this.userId);
    }
  }

  loadUser(id: number) {
    this.userService.getUserById(id).subscribe(
      (data) => this.userForm.patchValue(data), // Patch values to reactive form
      (error) => console.error('Error loading user:', error)
    );
  }

  onSubmit() {
    if (this.userForm.invalid) {
      this.userForm.markAllAsTouched();
      return;
    }

    if (this.save) {
      // Update user
      this.userService.updateUser(this.userId!, this.userForm.value).subscribe(
        () => {
          alert('User updated successfully!');
          this.router.navigate(['/user-list']);
        },
        (error) => console.error('Error updating user:', error)
      );
    } else {
      // Add new user
      this.userService.createUser(this.userForm.value).subscribe(
        () => {
          alert('User added successfully!');
          this.router.navigate(['/user-list']);
        },
        (error) => console.error('Error adding user:', error)
      );
    }
  }
}
