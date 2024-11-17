import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterOutlet, ActivatedRoute } from '@angular/router';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { NavbarComponent } from '../../components/navbar/navbar.component';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterOutlet, ReactiveFormsModule, CommonModule, NavbarComponent],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent implements OnInit {
  registerForm = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', Validators.required),
    typeUser: new FormControl('0', Validators.required), // Tipo de empresa (natural o jurídica)
    id: new FormControl('', Validators.required),
    sizeCompany: new FormControl('0', Validators.required), // Tamaño de la empresa (número de empleados)
    sector: new FormControl('0', Validators.required), // Sector de la empresa
    sizeCompanyModal: new FormControl('0', Validators.required),
    sectorModal: new FormControl('0', Validators.required),
    typeUserModal: new FormControl('0', Validators.required),
  });

  errorMessage: string | null = null;
  isTypeUserModalOpen = false;
  isSizeCompanyModalOpen = false;
  isSectorModalOpen = false;

  constructor(
    private http: HttpClient,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      if (params) {
        // Asignar los valores de los parámetros a los selectores correspondientes
        this.registerForm.patchValue({
          typeUser: params['tipoEmpresa'] || '0', // Tipo de empresa (natural o jurídica)
          sizeCompany: params['numeroEmpleados'] || '0', // Tamaño de la empresa (número de empleados)
          sector: params['sectorEmpresa'] || '0', // Sector de la empresa
        });
      }
    });
  }

  register() {
    // Obtener los controles del formulario
    const nameControl = this.registerForm.get('name') as AbstractControl;
    const emailControl = this.registerForm.get('email') as AbstractControl;
    const passwordControl = this.registerForm.get(
      'password'
    ) as AbstractControl;
    const typeUserControl = this.registerForm.get(
      'typeUser'
    ) as AbstractControl;
    const idControl = this.registerForm.get('id') as AbstractControl;
    const sizeCompanyControl = this.registerForm.get(
      'sizeCompany'
    ) as AbstractControl;
    const sectorControl = this.registerForm.get('sector') as AbstractControl;

    // Verificar que no se haya seleccionado la opción por defecto "0"
    if (
      sectorControl.value === '0' ||
      sizeCompanyControl.value === '0' ||
      typeUserControl.value === '0'
    ) {
      this.showMessage('Por favor, selecciona una opción.', 'error');
      return;
    }



  const sectorValue = Number(sectorControl.value);

    // Lógica para asignar el ID del sector basado en la opción seleccionada
  let sectorId: number;
  switch (sectorValue) {
    case 1:
      sectorId = 6;
      break;
    case 2:
      sectorId = 7;
      break;
    case 3:
      sectorId = 8;
      break;
    case 4 :
      sectorId = 9;
      break;
    default:
      sectorId = 0;
      break; // Valor por defecto en caso de que no se seleccione correctamente
  }

 


    // Si el formulario es válido
    if (this.registerForm.valid) {
      // Crear el objeto userData con los valores del formulario
      const userData = {
        nombreEmpresa: this.registerForm.get('name')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      identificacion: this.registerForm.get('id')?.value,
      sizeCompany: this.registerForm.get('sizeCompany')?.value,
      sector: { id: sectorId}, // Convertir sector a objeto
      tipoPersona: { idTipoPersona: this.registerForm.get('typeUser')?.value }, // Convertir tipoPersona a objeto
      test: { idTest: 1 } // Convertir a número
      };

      // Verifica los datos antes de enviarlos
      console.log(
        'Datos del formulario antes de enviar:',
        JSON.stringify(userData)
      );

      // Realiza la solicitud POST directamente en el componente
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

      this.http
        .post('http://localhost:8080/api/Empresas', userData, { headers })
        .subscribe({
          next: (response) => {
            console.log('Registro exitoso:', response);
            this.router.navigateByUrl('/login');
          },
          error: (error) => {
            console.error('Error al intentar registrar:', error);
            this.showMessage(
              'Ocurrió un error al intentar registrar el usuario. Por favor, intenta nuevamente.',
              'error'
            );
          },
        });
    } else {
      this.showMessage(
        'Formulario inválido. Por favor, revisa los campos.',
        'error'
      );
      this.setInvalidClass(
        nameControl,
        emailControl,
        passwordControl,
        typeUserControl,
        idControl,
        sizeCompanyControl,
        sectorControl
      );
    }
  }

  setInvalidClass(...controls: AbstractControl[]) {
    controls.forEach((control) => {
      if (control?.invalid) {
        control.markAsTouched();
      }
    });
  }

  showMessage(message: string, type: 'success' | 'error') {
    const messageDiv = document.getElementById(
      type === 'success' ? 'message' : 'error-message'
    );

    if (messageDiv) {
      messageDiv.textContent = message;
      messageDiv.style.display = 'block';
      setTimeout(() => {
        messageDiv.style.display = 'none';
      }, 5000);
    }
  }

  openTypeUserModal() {
    this.registerForm.get('typeUserModal')?.setValue('0'); // Reiniciar valor
    this.isTypeUserModalOpen = true;
  }

  closeTypeUserModal() {
    this.isTypeUserModalOpen = false;
  }

  confirmTypeUserAction() {
    const selectedValue = this.registerForm.get('typeUserModal')?.value;
    if (selectedValue) {
      this.registerForm.get('typeUser')?.setValue(selectedValue);
    }
    this.closeTypeUserModal();
  }

  openSizeCompanyModal() {
    this.registerForm.get('sizeCompanyModal')?.setValue('0'); // Reiniciar valor
    this.isSizeCompanyModalOpen = true;
  }

  closeSizeCompanyModal() {
    this.isSizeCompanyModalOpen = false;
  }

  confirmSizeCompanyAction() {
    const selectedValue = this.registerForm.get('sizeCompanyModal')?.value;
    if (selectedValue) {
      this.registerForm.get('sizeCompany')?.setValue(selectedValue);
    }
    this.closeSizeCompanyModal();
  }

  openSectorModal() {
    this.registerForm.get('sectorModal')?.setValue('0'); // Reiniciar valor
    this.isSectorModalOpen = true;
  }

  closeSectorModal() {
    this.isSectorModalOpen = false;
  }

  confirmSectorAction() {
    const selectedValue = this.registerForm.get('sectorModal')?.value;
    if (selectedValue) {
      this.registerForm.get('sector')?.setValue(selectedValue);
    }
    this.closeSectorModal();
  }
}
