package com.estramipyme.estramipyme_API.Repositories;

import com.estramipyme.estramipyme_API.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import com.estramipyme.estramipyme_API.models.Empresas;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresasRepository extends JpaRepository<Empresas, Long> {
    List<Empresas> findByEmail(String email);
}
