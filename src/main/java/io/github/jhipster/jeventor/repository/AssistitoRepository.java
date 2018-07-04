package io.github.jhipster.jeventor.repository;

import io.github.jhipster.jeventor.domain.Assistito;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Assistito entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssistitoRepository extends JpaRepository<Assistito, Long> {

}
