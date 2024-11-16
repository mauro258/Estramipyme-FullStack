package com.estramipyme.estramipyme_API.Repositories;

import com.estramipyme.estramipyme_API.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
}
