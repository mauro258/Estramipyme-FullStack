package com.estramipyme.estramipyme_API.controllers;

import java.util.List;
import java.util.Optional;

import com.estramipyme.estramipyme_API.models.Students;
import com.estramipyme.estramipyme_API.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.models.Admin;
import com.estramipyme.estramipyme_API.models.TypeUser;
import com.estramipyme.estramipyme_API.services.AdminService;
import com.estramipyme.estramipyme_API.services.AdminServiceImpl;

@RestController
@RequestMapping("/api/administradores")
public class AdminController {
    private final AdminService adminService;
    private final TypeUserRepository typeUserRepository;
    private final  AdminServiceImpl  adminServiceImpl;

    @Autowired
    public AdminController(AdminService adminService, TypeUserRepository typeUserRepository, AdminServiceImpl   adminServiceImpl) {
        this.adminService = adminService;
        this.typeUserRepository = typeUserRepository;
        this.adminServiceImpl = adminServiceImpl;
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

    @GetMapping("email/")
    public ResponseEntity<List<Admin>> getAdminByEmail(@RequestParam String email) {
        List<Admin> admins = adminServiceImpl.findByEmail(email);
        return ResponseEntity.ok(admins);
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
