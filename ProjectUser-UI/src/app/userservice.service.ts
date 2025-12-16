import { Injectable } from '@angular/core';
import { HttpServiceService } from './http-service.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserserviceService {
  private baseUrl = 'http://localhost:8084/user';
  constructor(private httpService: HttpServiceService) {}
  
  getUsers(page: number, size: number): Observable<any> {
    return this.httpService.get(`${this.baseUrl}/list?page=${page}&size=${size}`);
  }
   getUserById(id: number): Observable<any> {
    return this.httpService.get(`${this.baseUrl}/${id}`);
  }

  createUser(user: any): Observable<any> {
    return this.httpService.post(`${this.baseUrl}/add`, user);
}
  
  updateUser(id: number, user: any): Observable<any> {
    return this.httpService.put(`${this.baseUrl}/update/${id}`, user);
  }

  deleteUser(userIds: number[]): Observable<void> {
    return this.httpService.post(`${this.baseUrl}/delete`,
      userIds
    );
  }

}

  
