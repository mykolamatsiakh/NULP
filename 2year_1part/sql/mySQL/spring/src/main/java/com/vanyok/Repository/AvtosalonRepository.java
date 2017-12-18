package com.vanyok.Repository;

import com.vanyok.domain.Avtosalon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvtosalonRepository extends JpaRepository<Avtosalon, Long> {
}
