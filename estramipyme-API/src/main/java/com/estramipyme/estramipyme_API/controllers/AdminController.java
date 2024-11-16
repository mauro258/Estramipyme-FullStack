package com.estramipyme.estramipyme_API.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.models.Admin;
import com.estramipyme.estramipyme_API.models.TypeUser;
import com.estramipyme.estramipyme_API.services.AdminService;

@RestController
@RequestMapping("/api/administradores")
public class AdminController {
    private final AdminService adminService;
    private final TypeUserRepository typeUserRepository;

    @Autowired
    public AdminController(AdminService adminService, TypeUserRepository typeUserRepository) {
        this.adminService = adminService;
        this.typeUserRepository = typeUserRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllAdmins() {
        try {
            List<Admin> admins = adminService.findAll();
            return ResponseEntity.ok(admins);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado al obtener los administradores");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdminById(@PathVariable Long id) {
        try {
            Admin admin = adminService.findById(id);
            if (admin == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Administrador no encontrado con ID: " + id);
            }
            return ResponseEntity.ok(admin);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado");
        }
    }

    @PostMapping
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin) {
        try {
            if (admin.getTypeUser() != null && admin.getTypeUser().getId() != null) {
                Optional<TypeUser> typeUserOptional = typeUserRepository.findById(admin.getTypeUser().getId());
                if (typeUserOptional.isPresent()) {
                    admin.setTypeUser(typeUserOptional.get());
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("TypeUser con ID " + admin.getTypeUser().getId() + " no encontrado.");
                }
            }
            Admin savedAdmin = adminService.save(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAdmin);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al crear el administrador.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        try {
            Admin existing = adminService.findById(id);
            if (existing != null) {
                if (admin.getTypeUser() != null && admin.getTypeUser().getId() != null) {
                    Optional<TypeUser> typeUserOptional = typeUserRepository.findById(admin.getTypeUser().getId());
                    if (typeUserOptional.isPresent()) {
                        admin.setTypeUser(typeUserOptional.get());
                    } else {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                .body("TypeUser con ID " + admin.getTypeUser().getId() + " no encontrado.");
                    }
                }
                admin.setId(id);
                Admin updatedAdmin = adminService.save(admin);
                return ResponseEntity.ok(updatedAdmin);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Administrador con ID " + id + " no encontrado.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error al intentar actualizar el administrador.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        try {
            Admin existing = adminService.findById(id);
            if (existing != null) {
                adminService.deleteById(id);
                return ResponseEntity.ok("Administrador con ID " + id + " eliminado correctamente.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Administrador con ID " + id + " no encontrado.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado al intentar eliminar el administrador.");
        }
    }

}
