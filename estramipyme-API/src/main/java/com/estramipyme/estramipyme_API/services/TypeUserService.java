package com.estramipyme.estramipyme_API.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.models.TypeUser;

@Service
public class TypeUserService {

    @Autowired
    private TypeUserRepository typeUserRepository;

    public List<TypeUser> getAllTypeUsers() {
        return typeUserRepository.findAll();
    }

    public TypeUser getTypeUserById(Long id) {
        return typeUserRepository.findById(id).orElse(null);
    }

    public TypeUser createTypeUser(TypeUser typeUser) {
        return typeUserRepository.save(typeUser);
    }

    public TypeUser updateTypeUser(Long id, TypeUser updatedTypeUser) {
        TypeUser existingTypeUser = typeUserRepository.findById(id).orElse(null);
        if (existingTypeUser != null) {
            existingTypeUser.setDescripcion(updatedTypeUser.getDescripcion());
            return typeUserRepository.save(existingTypeUser);
        }
        return null;
    }

    public void deleteTypeUser(Long id) {
        typeUserRepository.deleteById(id);
    }
}