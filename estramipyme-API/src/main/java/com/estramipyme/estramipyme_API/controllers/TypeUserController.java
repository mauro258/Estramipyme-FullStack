package com.estramipyme.estramipyme_API.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estramipyme.estramipyme_API.models.TypeUser;
import com.estramipyme.estramipyme_API.services.TypeUserService;

@RestController
@RequestMapping("/api/typeusers")
public class TypeUserController {

    @Autowired
    private TypeUserService typeUserService;

    @GetMapping
    public ResponseEntity<List<TypeUser>> getAllTypeUsers() {
        return ResponseEntity.ok(typeUserService.getAllTypeUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeUser> getTypeUserById(@PathVariable Long id) {
        TypeUser typeUser = typeUserService.getTypeUserById(id);
        return typeUser != null ? ResponseEntity.ok(typeUser) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TypeUser> createTypeUser(@RequestBody TypeUser typeUser) {
        return ResponseEntity.ok(typeUserService.createTypeUser(typeUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeUser> updateTypeUser(@PathVariable Long id, @RequestBody TypeUser updatedTypeUser) {
        TypeUser updatedTypeUserResponse = typeUserService.updateTypeUser(id, updatedTypeUser);
        return updatedTypeUserResponse != null ? ResponseEntity.ok(updatedTypeUserResponse)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeUser(@PathVariable Long id) {
        typeUserService.deleteTypeUser(id);
        return ResponseEntity.noContent().build();
    }
}