package com.estramipyme.estramipyme_API.Repositories;

import com.estramipyme.estramipyme_API.models.Students;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Students, Integer> {
    List<Students> findByEmail(String email);
}