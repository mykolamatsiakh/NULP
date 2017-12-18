package com.vanyok.Repository;

import com.vanyok.domain.Logger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Long> {
    List<Logger> findByVlasnykLike(String like);
}
