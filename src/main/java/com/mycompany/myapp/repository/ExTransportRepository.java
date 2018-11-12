package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ExTransport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ExTransport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExTransportRepository extends JpaRepository<ExTransport, Long> {

}
