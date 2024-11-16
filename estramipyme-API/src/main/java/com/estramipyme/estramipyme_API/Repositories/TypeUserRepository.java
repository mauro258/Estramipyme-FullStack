package com.estramipyme.estramipyme_API.Repositories;

import org.springframework.stereotype.Repository;
import com.estramipyme.estramipyme_API.models.TypeUser;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TypeUserRepository extends JpaRepository<TypeUser, Long> {
}