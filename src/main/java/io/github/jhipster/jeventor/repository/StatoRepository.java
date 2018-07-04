package io.github.jhipster.jeventor.repository;

import io.github.jhipster.jeventor.domain.Stato;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Stato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StatoRepository extends JpaRepository<Stato, Long> {

}
