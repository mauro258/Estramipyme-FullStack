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
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      password: ['', Validators.required],
      telefono: ['', Validators.required]
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

    
    const studentDataMapped = {
        ...studentData,
        
        typeUser: { id: 2 },           
        empresa: { id: 7 }             
    };

    console.log('Datos del estudiante después del mapeo:', studentDataMapped);

    this.http.post('http://localhost:8080/api/students', studentDataMapped).subscribe({
        next: (response) => {
            console.log('Estudiante creado exitosamente');
            this.studentName = studentData.firstName;
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
  
  
  

  
}
