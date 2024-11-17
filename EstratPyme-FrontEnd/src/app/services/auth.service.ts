import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { catchError, forkJoin, map, Observable, of } from 'rxjs';
import { Admin } from '../models/admin';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/studens';
  private baseUrlAdmin= "http://localhost:8080/api"

  isLoggedIn:boolean=false;

  constructor(private http: HttpClient) { }

  registerUser(userDetails: any) {
    return this.http.post(this.baseUrl, userDetails)
  }

  login(email: string, password: string): Observable<User | null> {
    return this.http.get<User[]>(`${this.baseUrl}/email/?email=${email}`).pipe(
      map(usuarios => {
        if (usuarios.length > 0) {
          const user = usuarios[0];
          // Compara la contraseña ingresada con la almacenada en la base de datos
          if (password === user.password) {
            return user;
          } else {
            return null;
          }
        } else {
          return null;
        }
      }),
      catchError(() => of(null))
    );
  }

  loginAdmin(email:string,password:string): Observable<User|null>{
  const adminRequest = this.http.get<Admin[]>(`${this.baseUrlAdmin}/administradores/email/?email=${email} `);
  const techerRequest = this.http.get<Admin[]> (`${this.baseUrlAdmin}/teachers/email/?email=${email}`);

    return forkJoin([adminRequest, techerRequest]).pipe(
      map(([admins,teachers]) => {
        let validUser : Admin | null = null;

        //buscar en la tabla de administradores
        if (admins.length > 0){
          const admin = admins[0];
          if(password===admin.password){
            validUser = admin;
          }
        }
        // si no se encuentra en Administradores, buscar en teachers
        if (!validUser && teachers.length > 0){
          const teacher = teachers[0];
          if (password === teacher.password) {
            validUser = teacher;
          }
        }
        return validUser;

      }),

        
      catchError(()=> of(null))
    );
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/${id}`);
  }

  getLogin(){
    return this.isLoggedIn
  }
}

