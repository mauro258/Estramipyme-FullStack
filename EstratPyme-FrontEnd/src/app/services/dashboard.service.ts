import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { map, Observable } from 'rxjs';
import { Test } from '../models/test';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private baseUrl = 'http://localhost:8080/api/students';
  private baseUrlTest= 'https://estramipyme-api.vercel.app/tests'

  constructor(private http: HttpClient) {}

  getUsers() {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUsersWithTestDone(): Observable<User[]> {
    return this.getUsers().pipe(
      map((users: User[]) => users.filter(user => user.isTestDone))
    );
  }

  getTestById(id: string): Observable<Test | undefined> {
    return this.http.get<Test[]>(this.baseUrlTest).pipe(
      map(tests => tests.find(test => test.id_empresa === id)) // Encuentra el test con el id dado
    );
  }
}
