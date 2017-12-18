package com.vanyok.Repository;

import com.vanyok.domain.Vlasnyk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VlasnykRepository extends JpaRepository<Vlasnyk, Long> {
}
