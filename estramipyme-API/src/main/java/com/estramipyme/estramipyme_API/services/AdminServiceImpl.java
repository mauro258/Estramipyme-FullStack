package com.estramipyme.estramipyme_API.services;

import java.util.List;
import java.util.Optional;

import com.estramipyme.estramipyme_API.models.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.Repositories.AdminRepository;
import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.models.Admin;
import com.estramipyme.estramipyme_API.models.TypeUser;

@Service
public class AdminServiceImpl implements AdminService{
     private final AdminRepository adminRepository;
     private final TypeUserRepository typeUserRepository;

     @Autowired
     public AdminServiceImpl(AdminRepository adminRepository, TypeUserRepository typeUserRepository) {
         this.adminRepository = adminRepository;
         this.typeUserRepository = typeUserRepository;
     }

    @Override
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Override
    public Admin findById(Long id) {
        Optional<Admin> optional = adminRepository.findById(id);
        return optional.orElse(null);
    }

    public List<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

     @Override
    public Admin save(Admin admin) {
        if (admin.getTypeUser() != null && admin.getTypeUser().getId() != null) {
            Optional<TypeUser> typeUserOptional = typeUserRepository.findById(admin.getTypeUser().getId());
            if (typeUserOptional.isPresent()) {
                admin.setTypeUser(typeUserOptional.get());
            } else {
                throw new IllegalArgumentException("TypeUser con ID " + admin.getTypeUser().getId() + " no encontrado.");
            }
        }
        return adminRepository.save(admin);
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }
}
