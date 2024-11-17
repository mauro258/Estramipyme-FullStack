package com.estramipyme.estramipyme_API.Repositories;

import com.estramipyme.estramipyme_API.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estramipyme.estramipyme_API.models.Admin;

import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    List<Admin> findByEmail(String email);
}
