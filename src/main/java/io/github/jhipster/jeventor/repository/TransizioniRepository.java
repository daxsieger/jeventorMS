package io.github.jhipster.jeventor.repository;

import io.github.jhipster.jeventor.domain.Transizioni;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Transizioni entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransizioniRepository extends JpaRepository<Transizioni, Long> {

}
