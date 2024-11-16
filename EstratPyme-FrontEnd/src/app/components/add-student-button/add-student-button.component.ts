import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-student-button',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-student-button.component.html',
  styleUrls: ['./add-student-button.component.scss']
})
export class AddStudentButtonComponent {
  showForm = false;
  studentForm: FormGroup;
  studentFound = false;
  studentName: string | null = null;
  showErrorModal = false;
  showSuccessModal = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.studentForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      password: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }

  addStudent() {
    console.log(this.studentForm.valid);
    if (this.studentForm.invalid) {
        this.showErrorModal = true;
        this.errorMessage = 'Por favor, complete todos los campos requeridos.';
        return;
    }

    const studentData = this.studentForm.value;

    // Mapear firstName a nombre y lastName a apellido antes de enviarlo
    const studentDataMapped = {
        ...studentData,
        nombre: studentData.firstName,   // Mapear firstName a nombre
        apellido: studentData.lastName,
        telefono: studentData.phone,
        typeUser: { id: 2 },             // Asignar tipo de usuario
        empresa: { id: 5 }               // Asignar empresa
    };

    console.log('Datos del estudiante después del mapeo:', studentDataMapped);

    this.http.post('http://localhost:8080/api/students', studentDataMapped).subscribe({
        next: (response) => {
            console.log('Estudiante creado exitosamente');
            this.studentName = studentData.firstName; // Capturar el nombre del estudiante
            this.showSuccessModal = true;
            this.studentForm.reset();
        },
        error: (error) => {
            console.error(error);
            this.showErrorModal = true;
            this.errorMessage = 'Error al crear el estudiante.';
        }
    });
}

  

  closeErrorModal() {
    this.showErrorModal = false;
    this.errorMessage = '';
  }

  closeSuccessModal() {
    this.showSuccessModal = false;
  }
  
  addStudentToProject() {
    if (this.studentFound) {
      this.http.patch(`https://estramipyme-api.vercel.app/students/${this.studentName}`, { haceParteProyecto: true }).subscribe({
        next: () => {
          this.showSuccessModal = true;
          this.studentFound = false;
          this.studentForm.reset();
        },
        error: () => {
          alert('Error al agregar el estudiante al proyecto');
        }
      });
    }
  }
  

  
}
