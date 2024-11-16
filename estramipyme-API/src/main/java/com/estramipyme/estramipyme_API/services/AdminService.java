package com.estramipyme.estramipyme_API.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.models.Admin;

@Service
public interface AdminService {
    List<Admin> findAll();

    Admin findById(Long id);

    Admin save(Admin admin);

    void deleteById(Long id);

}
