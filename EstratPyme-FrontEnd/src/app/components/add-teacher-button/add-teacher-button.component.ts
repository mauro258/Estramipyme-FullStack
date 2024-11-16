import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-add-teacher-button',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './add-teacher-button.component.html',
  styleUrls: ['./add-teacher-button.component.scss']
})
export class AddTeacherButtonComponent {
  showForm = false;
  teacherForm: FormGroup;
  teacherFound = false;
  teacherName: string | null = null; 
  showErrorModal = false;
  showSuccessModal = false;
  errorMessage = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.teacherForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      password: ['', Validators.required],
      telephone: ['', Validators.required]
    });
  }

  addTeacher() {
    console.log(this.teacherForm.valid);
    if (this.teacherForm.invalid) {
        this.showErrorModal = true;
        this.errorMessage = 'Por favor, complete todos los campos requeridos.';
        return;
    }

    const teacherData = this.teacherForm.value;

    
    const teacherDataMapped = {
        ...teacherData,
        
        type_user: { id: 3 },           
        empresa: { id: 5 }             
    };

    console.log('Datos del estudiante después del mapeo:', teacherDataMapped);

    this.http.post('http://localhost:8080/api/teachers', teacherDataMapped).subscribe({
        next: (response) => {
            console.log('profesor creado exitosamente');
            this.teacherName = teacherData.firstName;
            this.showSuccessModal = true;
            this.teacherForm.reset();
        },
        error: (error) => {
            console.error(error);
            this.showErrorModal = true;
            this.errorMessage = 'Error al crear el profesor.';
        }
    });
}

  closeErrorModal() {
    this.showErrorModal = false;
    this.errorMessage = '';
  }

  closeSuccessModal() {
    this.showSuccessModal = false;
    this.teacherName = null;
  }
}

  