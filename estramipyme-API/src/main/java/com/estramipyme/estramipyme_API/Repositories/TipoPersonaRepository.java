package com.estramipyme.estramipyme_API.Repositories;

import com.estramipyme.estramipyme_API.models.TipoPersona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPersonaRepository extends JpaRepository<TipoPersona, Long> {
}